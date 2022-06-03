package kr.codesquad.airbnb.accomodation.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import kr.codesquad.airbnb.accomodation.dto.AccommodationResponse;
import lombok.Getter;

@Entity(name = "accommodation")
@Getter
public class Accommodation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accommodation_id")
    private Long accommodationId;
    @Embedded
    private Host host;
    @Embedded
    private AccommodationInfo accommodationInfo;
    @Embedded
    private Report report;
    @Embedded
    private Amenity amenity;

    @OneToMany(mappedBy = "accommodation")
    private List<AccommodationImage> accommodationImages;

    public AccommodationResponse convertToAccommodationResponse() {
        Collections.sort(accommodationImages);

        return AccommodationResponse.builder()
            .id(getAccommodationId())
            .hostName(host.getName())
            .hostImage(host.getImage())
            .isSuperHost(host.isSuperHost())
            .accommodationName(accommodationInfo.getName())
            .description(accommodationInfo.getDescription())
            .price(accommodationInfo.getPrice())
            .address(accommodationInfo.getAddress())
            .checkInTime(accommodationInfo.getCheckInTime())
            .checkOutTime(accommodationInfo.getCheckOutTime())
            .occupancy(accommodationInfo.getOccupancy())
            .accommodationType(accommodationInfo.getAccommodationType().getName())
            .cleaningFee(accommodationInfo.getCleaningFee())
            .bedCount(accommodationInfo.getBedCount())
            .bathroomCount(accommodationInfo.getBathroomCount())
            .reviewCount(report.getReviewCount())
            .rating(String.valueOf(report.getRating()))
            .wifi(amenity.isWifi())
            .hair_dryer(amenity.isHairDryer())
            .accommodationImages(accommodationImages.stream().map(AccommodationImage::getImageUrl).collect(
                Collectors.toList()))
            .build();
    }

}
