package kr.codesquad.airbnb.accomodation.web;

import kr.codesquad.airbnb.accomodation.domain.AccommodationRepository;
import kr.codesquad.airbnb.accomodation.dto.AccommodationSearchRequest;
import kr.codesquad.airbnb.accomodation.dto.AccommodationsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private AccommodationRepository accommodationRepository;

    public AccommodationsResponse findAll(
        AccommodationSearchRequest accommodationSearchRequest) {

        return null;
    }
}
