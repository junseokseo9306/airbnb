package kr.codesquad.airbnb.accomodation.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccommodationResponse {

    private final Long id;
    private final String accomName;
    private final BigDecimal price;
    private final BigDecimal totalPrice;
    private final String rating;
    private final int reviews;
    private final boolean isWish;
    private final boolean isSuperHost;
    private final String thumbnail;

}
