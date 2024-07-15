package com.eventoste.api.domain.Event;

import org.springframework.web.multipart.MultipartFile;


public record EventRequestDTO(String title, String description, Long date, String city, String state, Boolean remote, String eventUrl, MultipartFile image) {

};
