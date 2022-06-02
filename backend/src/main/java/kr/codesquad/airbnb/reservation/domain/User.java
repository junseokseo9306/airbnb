package kr.codesquad.airbnb.reservation.domain;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

}
