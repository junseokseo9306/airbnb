package kr.codesquad.airbnb.reservation.domain;

import kr.codesquad.airbnb.accomodation.domain.Accommodation;
import kr.codesquad.airbnb.reservation.dto.ReservationResponse;
import org.springframework.data.util.Lazy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "reservations")
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id")
    private Accommodation accommodation;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id")
    private User user;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private BigDecimal totalPrice;
    private Integer guests;
    @Column(name = "delete_yn")
    private boolean delete;

    public ReservationResponse createReservationResponse() {

    }


}
