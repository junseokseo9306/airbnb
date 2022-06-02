package kr.codesquad.airbnb.reservation.domain;

import kr.codesquad.airbnb.accomodation.domain.Accommodation;

import javax.persistence.*;

@Entity(name = "whislists")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id")
    private Accommodation accommodation;

}
