package com.eventoste.api.domain.address;

import com.eventoste.api.domain.Event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name= "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue
    private UUID id;

    private String city;
    private String uf;

    @ManyToOne
    @JoinColumn(name= "address_id")
    private Event event;

}
