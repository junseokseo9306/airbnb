package kr.codesquad.airbnb.reservation.domain;

import kr.codesquad.airbnb.accomodation.domain.Accommodation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Reservation {

    private Long id;
    private Accommodation accommodation;
    private User user;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private BigDecimal totalPrice;
    private Integer guests;
    private boolean delete;


}
