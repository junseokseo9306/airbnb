package kr.codesquad.airbnb.accomodation.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AccommodationInfo {

    private String name;
    private String description;
    private BigDecimal price;
    private String address;
    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;
    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;
    private Integer occupancy;
    @Column(name = "type")
    private AccommodationType accommodationType;
    @Column(name = "cleaning_fee")
    private BigDecimal cleaningFee;
    @Column(name = "bed_count")
    private Integer bedCount;
    @Column(name = "bathroom_count")
    private Integer bathroomCount;

}
