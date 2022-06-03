package kr.codesquad.airbnb.reservation.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ReservationResponse {

    private final Long id;
    private final List<String> accommodationImage;
    private final String address;
    private final String name;
    private final LocalDateTime checkIn;
    private final LocalDateTime checkOut;
    private final String host;
    private final String accommodationType;
    private final Integer guests;
    private final BigDecimal totalPrice;

}
