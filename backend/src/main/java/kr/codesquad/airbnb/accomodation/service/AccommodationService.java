package kr.codesquad.airbnb.accomodation.service;

import java.util.List;
import java.util.stream.Collectors;
import kr.codesquad.airbnb.accomodation.domain.Accommodation;
import kr.codesquad.airbnb.accomodation.domain.AccommodationRepository;
import kr.codesquad.airbnb.accomodation.dto.AccommodationResponse;
import kr.codesquad.airbnb.accomodation.dto.AccommodationSearchRequest;
import kr.codesquad.airbnb.accomodation.dto.AccommodationsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    public AccommodationsResponse findAll(AccommodationSearchRequest accommodationSearchRequest) {
        List<AccommodationResponse> accommodations = accommodationRepository.findAll()
            .stream()
            .map(Accommodation::convertToAccommodationResponse)
            .collect(Collectors.toList());

        return new AccommodationsResponse(accommodations);
    }
}
