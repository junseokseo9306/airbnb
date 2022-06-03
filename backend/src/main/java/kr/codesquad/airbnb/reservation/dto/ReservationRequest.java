package kr.codesquad.airbnb.reservation.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ReservationRequest {

    private Long accommodationId;
    private String checkIn;
    private String CheckOut;
    private BigDecimal totalPrice;
    private String name;
    private Integer guests;

}
