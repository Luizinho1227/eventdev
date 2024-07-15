package com.eventoste.api.controller;

import com.eventoste.api.domain.coupon.Coupon;
import com.eventoste.api.domain.coupon.CouponRequestDTO;
import com.eventoste.api.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/event/{eventId}")
    public ResponseEntity<Coupon> addCouponToEvent(@PathVariable UUID eventId, @RequestBody CouponRequestDTO date){
        Coupon coupons = couponService.addCouponToEvent(eventId, date);
        return ResponseEntity.ok(coupons);
    }

}
