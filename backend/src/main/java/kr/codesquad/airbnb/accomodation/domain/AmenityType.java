package kr.codesquad.airbnb.accomodation.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AmenityType {

    WIFT("와이파이"),
    HAIR_DRYER("헤어 드라이어");

    private final String name;

}
