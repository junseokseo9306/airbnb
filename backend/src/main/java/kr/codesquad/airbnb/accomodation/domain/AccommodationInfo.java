package kr.codesquad.airbnb.accomodation.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;

@Embeddable
@Getter
public class AccommodationInfo {

    private String name;
    private String description;
    private BigDecimal price;
    private String address;
    private String checkInTime;
    private String checkOutTime;
    private Integer occupancy;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccommodationType accommodationType;
    private BigDecimal cleaningFee;
    private Integer bedCount;
    private Integer bathroomCount;

}
