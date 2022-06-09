package kr.codesquad.airbnb.reservation.domain;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String userId;
    @Column(length = 50)
    private String name;
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    public User() {

    }

    public User(Long id, String userId, String name, List<Reservation> reservations) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.reservations = reservations;
    }

}
