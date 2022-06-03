package kr.codesquad.airbnb.accomodation.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Host {

    @Column(name = "host_name")
    private String name;
    @Column(name = "host_image")
    private String image;
    @Column(name = "superhost_yn")
    private boolean isSuperHost;

}
