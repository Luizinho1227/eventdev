package com.eventoste.api.service;

import com.eventoste.api.domain.Event.Event;
import com.eventoste.api.domain.coupon.Coupon;
import com.eventoste.api.domain.coupon.CouponRequestDTO;
import com.eventoste.api.repositories.CouponRepository;
import com.eventoste.api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CouponService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CouponRepository couponRepository;

    public Coupon addCouponToEvent(UUID eventID, CouponRequestDTO couponData) {
        Event event = eventRepository.findById(eventID)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));


        Coupon coupon = new Coupon();
        coupon.setCode(couponData.code());
        coupon.setDiscount(couponData.discount());
        coupon.setValid(new Date(couponData.valid()));
        coupon.setEvent(event);

        return couponRepository.save(coupon);
    }


}
