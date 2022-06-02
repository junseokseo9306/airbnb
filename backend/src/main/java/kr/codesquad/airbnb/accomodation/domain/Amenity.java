package kr.codesquad.airbnb.accomodation.domain;

import javax.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Amenity {

    private boolean wifi;
    private boolean hairDryer;

}
