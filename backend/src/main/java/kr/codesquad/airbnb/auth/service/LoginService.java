package kr.codesquad.airbnb.auth.service;

import java.net.URI;
import kr.codesquad.airbnb.auth.AccessToken;
import kr.codesquad.airbnb.auth.GitHubUserDto;
import kr.codesquad.airbnb.reservation.domain.User;
import kr.codesquad.airbnb.reservation.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    private static final String GITHUB_AUTHORIZATION_SERVER_URL = "https://github.com/login/oauth/access_token";
    private static final String GITHUB_RESOURCE_SERVER_API_URL = "https://api.github.com/user";

    public AccessToken getAccessToken(String code) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json");
        headers.setAll(header);

        MultiValueMap<String, String> requestPayloads = new LinkedMultiValueMap<>();
        Map<String, String> requestPayload = new HashMap<>();
        requestPayload.put("client_id", "{CLIENT_ID}");
        requestPayload.put("client_secret", "${CLIENT_SECRET}");
        requestPayload.put("code", code);
        requestPayloads.setAll(requestPayload);

        HttpEntity<?> request = new HttpEntity<>(requestPayloads, headers);
        ResponseEntity<?> response = new RestTemplate().postForEntity(
            GITHUB_AUTHORIZATION_SERVER_URL, request, AccessToken.class);
        return (AccessToken) response.getBody();
    }

    public GitHubUserDto getUserInfo(AccessToken accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "token " + accessToken.getAccessToken());

        HttpEntity<?> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<GitHubUserDto> response = new RestTemplate().exchange(
            GITHUB_RESOURCE_SERVER_API_URL,
            HttpMethod.GET,
            request,
            GitHubUserDto.class
        );

        return response.getBody();
    }

    public void saveUserInfo(GitHubUserDto userInfo) {
        String userId = userInfo.getUserId();
        String name = userInfo.getName();

        if (!isDuplicate(userId)) {
            User user = new User(null, userId, name, null);
            userRepository.save(user);
        }
    }

    private boolean isDuplicate(String userId) {
        return userRepository.findByUserId(userId).isPresent();
    }

}
