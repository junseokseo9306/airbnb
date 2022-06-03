package kr.codesquad.airbnb.reservation.controller;

import kr.codesquad.airbnb.reservation.domain.User;
import kr.codesquad.airbnb.reservation.dto.ReservationRequest;
import kr.codesquad.airbnb.reservation.dto.Result;
import kr.codesquad.airbnb.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    /*
    * 유저 정보 필요*/
    @PostMapping("/reservations")
    public Result reserve(User user, @RequestBody ReservationRequest reservationRequest) {
        reservationService.reserve(user,reservationRequest);
        return Result.SUCCESS; //예외 발생 필요
    }

    @PostMapping("/reservations/cancel/{id}")
    public Result cancel(User user, @PathVariable Long id) {
        reservationService.cancel(id);
        return Result.SUCCESS;
    }

}
