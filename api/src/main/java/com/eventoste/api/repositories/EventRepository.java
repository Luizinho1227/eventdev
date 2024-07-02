package com.eventoste.api.repositories;

import com.eventoste.api.domain.Event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

}
