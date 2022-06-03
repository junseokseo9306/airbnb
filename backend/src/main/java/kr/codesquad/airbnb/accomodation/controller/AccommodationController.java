package kr.codesquad.airbnb.accomodation.controller;

import kr.codesquad.airbnb.accomodation.dto.AccommodationSearchRequest;
import kr.codesquad.airbnb.accomodation.dto.AccommodationsResponse;
import kr.codesquad.airbnb.accomodation.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping("/accommodations")
    public AccommodationsResponse getAccommodations(
        AccommodationSearchRequest accommodationSearchRequest){
        return accommodationService.findAll(accommodationSearchRequest);
    }

}
