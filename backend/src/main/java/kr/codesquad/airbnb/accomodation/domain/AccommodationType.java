package kr.codesquad.airbnb.accomodation.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AccommodationType {

    ONE_ROOM("원룸"),
    TWO_ROOM("투룸"),
    THREE_ROOM("쓰리룸");

    private final String name;

}
