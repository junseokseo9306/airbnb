package kr.codesquad.airbnb.accomodation.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Report {

    @Column(name = "reviews")
    private Integer reviewCount;
    private double rating;

}
