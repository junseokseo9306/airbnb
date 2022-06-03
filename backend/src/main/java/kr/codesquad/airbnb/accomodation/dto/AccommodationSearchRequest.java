package kr.codesquad.airbnb.accomodation.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@AllArgsConstructor
public class AccommodationSearchRequest {

    private final String locaiton;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDateTime checkIn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDateTime checkOut;
    private final Integer guests;
    private final Integer minPrice;
    private final Integer maxPrice;

}
