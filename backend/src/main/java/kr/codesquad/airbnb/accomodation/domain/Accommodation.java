package kr.codesquad.airbnb.accomodation.domain;

import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "accommodations")
public class Accommodation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

}
