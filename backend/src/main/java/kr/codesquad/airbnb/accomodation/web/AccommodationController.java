package kr.codesquad.airbnb.accomodation.web;

import kr.codesquad.airbnb.accomodation.dto.AccommodationSearchRequest;
import kr.codesquad.airbnb.accomodation.dto.AccommodationsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping("/accommodations")
    public ResponseEntity<AccommodationsResponse> getAccommodations(
        AccommodationSearchRequest accommodationSearchRequest){
        AccommodationsResponse accommodationResponse = accommodationService.findAll(accommodationSearchRequest);

        return new ResponseEntity(accommodationResponse, HttpStatus.OK);


    }

}
