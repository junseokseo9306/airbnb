package kr.codesquad.airbnb.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GitHubUserDto {

    @JsonProperty("login")
    private String userId;
    private String name;

}
