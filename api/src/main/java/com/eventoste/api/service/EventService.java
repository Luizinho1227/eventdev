package com.eventoste.api.service;

import com.eventoste.api.domain.Event.Event;
import com.eventoste.api.domain.Event.EventRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class EventService {
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
        return "";
    }
}
