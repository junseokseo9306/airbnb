package kr.codesquad.airbnb.accomodation.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;

@Builder
public class AccommodationResponse {

    private final Long id;
    private final String hostName;
    private final String hostImage;
    private final boolean isSuperHost;
    private final String accommodationName;
    private final String description;
    private final Integer price;
    private final String address;
    private final String checkInTime;
    private final String checkOutTime;
    private final int occupancy;
    private final String accommodationType;
    private final int cleaningFee;
    private final int bedCount;
    private final int bathroomCount;
    private final int reviewCount;
    private final String rating;
    private final boolean wifi;
    private final boolean hair_dryer;
    private final List<String> accommodationImages;


}
