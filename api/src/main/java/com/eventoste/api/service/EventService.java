package com.eventoste.api.service;

import com.amazonaws.services.s3.AmazonS3;
import com.eventoste.api.domain.Event.Event;
import com.eventoste.api.domain.Event.EventRequestDTO;
import com.eventoste.api.repositories.EventRepository;
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
    private EventRepository repository;
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
        newEvent.setRemote(data.remote());

        repository.save(newEvent);

        return  newEvent;
    }

    private String uploadImg(MultipartFile multipartFile) {
        String filename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try{
            File file = this.convertMultpartToFile(multipartFile);
            s3Client.putObject(bucketName, filename, file);
            file.delete();
            return s3Client.getUrl(bucketName, filename).toString();
        } catch (Exception e){
            System.out.println("Erro ao subir o arquivo");
            return "";
        }
    }
    private File convertMultpartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
        }
    }

