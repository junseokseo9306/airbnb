package kr.codesquad.airbnb.reservation.service;

import java.util.List;
import java.util.NoSuchElementException;
import kr.codesquad.airbnb.accomodation.domain.Accommodation;
import kr.codesquad.airbnb.accomodation.domain.AccommodationRepository;
import kr.codesquad.airbnb.reservation.domain.Reservation;
import kr.codesquad.airbnb.reservation.domain.ReservationRepository;
import kr.codesquad.airbnb.reservation.domain.User;
import kr.codesquad.airbnb.reservation.dto.ReservationRequest;
import kr.codesquad.airbnb.reservation.dto.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final AccommodationRepository accommodationRepository;

    //todo: 유저 정보 설정후 진행
    public List<ReservationResponse> getReservationList(User user) {
        return null;
    }

    public ReservationResponse reserve(User user, ReservationRequest reservationRequest) {
        Accommodation accommodation = accommodationRepository.findById(reservationRequest.getAccommodationId())
            .orElseThrow(NoSuchElementException::new);
        Reservation reservation = accommodation.createReservation(user, reservationRequest);
        reservationRepository.save(reservation);
        return reservation.createReservationResponse();
    }

    public void cancel(Long id) {
        reservationRepository.deleteById(id);
    }

}
