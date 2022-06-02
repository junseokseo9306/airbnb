package kr.codesquad.airbnb.accomodation.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Report {

    @Column(name = "reviews")
    private Integer reviewCount;
    private double rating;

}
