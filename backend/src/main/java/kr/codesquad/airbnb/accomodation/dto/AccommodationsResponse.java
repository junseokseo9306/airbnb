package kr.codesquad.airbnb.accomodation.dto;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccommodationsResponse {

    private final List<AccommodationResponse> accommodations;

}
