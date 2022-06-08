package kr.codesquad.airbnb.reservation.domain;

import kr.codesquad.airbnb.accomodation.domain.Accommodation;

import javax.persistence.*;

@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Accommodation accommodation;

}
