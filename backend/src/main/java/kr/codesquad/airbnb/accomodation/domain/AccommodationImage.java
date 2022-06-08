package kr.codesquad.airbnb.accomodation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity(name = "accommodation_image")
@Getter
public class AccommodationImage implements Comparable<AccommodationImage> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_seq")
    private Integer imageSeq;

    @Override
    public int compareTo(AccommodationImage o) {
        return imageSeq - o.getImageSeq();
    }
}
