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
    private String publicEmail;
    private String privateEmail;
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    public User() {

    }

    public User(Long id, String name, String publicEmail, String privateEmail, List<Reservation> reservations) {
        this.id = id;
        this.name = name;
        this.publicEmail = publicEmail;
        this.privateEmail = privateEmail;
        this.reservations = reservations;
    }
}
