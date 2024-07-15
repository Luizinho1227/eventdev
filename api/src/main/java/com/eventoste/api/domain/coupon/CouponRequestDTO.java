package com.eventoste.api.domain.coupon;

import com.eventoste.api.domain.Event.Event;

public record CouponRequestDTO(String code, Integer discount, Long valid) {

}
