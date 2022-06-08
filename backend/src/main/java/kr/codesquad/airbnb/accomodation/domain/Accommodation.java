package kr.codesquad.airbnb.accomodation.domain;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import kr.codesquad.airbnb.accomodation.dto.AccommodationResponse;
import kr.codesquad.airbnb.reservation.domain.Reservation;
import kr.codesquad.airbnb.reservation.domain.User;
import kr.codesquad.airbnb.reservation.dto.ReservationRequest;
import lombok.Getter;

@Entity(name = "accommodation")
@Getter
public class Accommodation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accommodation_id")
    private Long accommodationId;
    @Embedded
    private Host host;
    @Embedded
    private AccommodationInfo accommodationInfo;
    @Embedded
    private Report report;
    @Embedded
    private Amenity amenity;

    @OneToMany(mappedBy = "accommodation")
    private List<AccommodationImage> accommodationImages;

    public AccommodationResponse convertToAccommodationResponse() {
        Collections.sort(accommodationImages);

        return AccommodationResponse.builder()
            .id(getAccommodationId())
            .accomName(accommodationInfo.getName())
            .price(accommodationInfo.getPrice())
            .totalPrice(null) //todo: 계산로직 정리 후 진행
            .rating(String.valueOf(report.getRating()))
            .reviews(report.getReviewCount())
            .isWish(false) //todo: User도메인 개발 후 진행
            .isSuperHost(host.isSuperHost())
            .thumbnail(accommodationImages.get(0).getImageUrl())
            .build();
    }

    public Reservation createReservation(User user, ReservationRequest reservationRequest) {
        return Reservation.builder()
            .accommodation(this)
            .user(user)
            .checkIn(LocalDate.parse(reservationRequest.getCheckIn()))
            .checkOut(LocalDate.parse(reservationRequest.getCheckOut()))
            .totalPrice(reservationRequest.getTotalPrice())
            .guests(reservationRequest.getGuests())
            .build();
    }

}
