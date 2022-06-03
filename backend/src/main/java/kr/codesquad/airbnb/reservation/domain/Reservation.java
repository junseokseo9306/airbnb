package kr.codesquad.airbnb.reservation.domain;

import kr.codesquad.airbnb.accomodation.domain.Accommodation;
import kr.codesquad.airbnb.accomodation.domain.AccommodationImage;
import kr.codesquad.airbnb.reservation.dto.ReservationResponse;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;


@Entity(name = "reservations")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id")
    private Accommodation accommodation;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id")
    private User user;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal totalPrice;
    private Integer guests;

    public ReservationResponse createReservationResponse() {
        return ReservationResponse.builder()
                .id(getId())
                .accommodationImage(accommodation.getAccommodationImages()
                        .stream().map(AccommodationImage::getImageUrl).collect(Collectors.toList()))
                .address(accommodation.getAccommodationInfo().getAddress())
                .name(accommodation.getAccommodationInfo().getName())
                .checkIn(LocalDateTime.of(checkIn, accommodation.getAccommodationInfo().getCheckInTime()))
                .checkOut(LocalDateTime.of(checkOut, accommodation.getAccommodationInfo().getCheckOutTime()))
                .host(accommodation.getHost().getName())
                .totalPrice(getTotalPrice())
                .build();
    }
}
