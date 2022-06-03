package kr.codesquad.airbnb.reservation.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Result {
    SUCCESS("success"),
    ADD("added"),
    DELETED("deleted");

    private final String message;

    public static String message(String name, Result result) {
        return result.message;
    }

}
