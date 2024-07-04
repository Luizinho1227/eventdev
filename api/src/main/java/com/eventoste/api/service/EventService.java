package com.eventoste.api.service;

import com.amazonaws.services.s3.AmazonS3;
import com.eventoste.api.domain.Event.Event;
import com.eventoste.api.domain.Event.EventRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {
    @Value("${aws.bucket.name}")
    private String bucketName;
    @Autowired
    private AmazonS3 s3Client;
    public Event createEvent(EventRequestDTO data) {
        String imagUrl = null;

        if (data.image() != null) {
            imagUrl = this.uploadImg(data.image());
        }
        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImgUrl(imagUrl);

        return  newEvent;
    }

    private String uploadImg(MultipartFile multpartFile) {
        String filename = UUID.randomUUID() + "-" + multpartFile.getOriginalFilename();

        try{
            File file = this.convertMultpartToFile(multpartFile);
            s3Client.putObject(bucketName, filename, file);
            file.delete();
            return s3Client.getUrl(bucketName, filename).toString();
        } catch (Exception e){
            System.out.println("Erro ao subir o arquivo");
            return null;
        }
    }
    private File convertMultpartToFile(MultipartFile multpartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multpartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multpartFile.getBytes());
        fos.close();
        return convFile;
        }
    }

