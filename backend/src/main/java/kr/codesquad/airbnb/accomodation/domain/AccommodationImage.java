package kr.codesquad.airbnb.accomodation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "accommodation_images")
public class AccommodationImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id")
    private Accommodation accommodation;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_seq")
    private Integer imageSeq;

}
