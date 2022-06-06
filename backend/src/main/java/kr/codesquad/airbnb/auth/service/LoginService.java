package kr.codesquad.airbnb.auth.service;

import kr.codesquad.airbnb.auth.AccessToken;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    private static final String CLIENT_ID = "7b4b1d838e988360292f";
    private static final String SCOPE = "user/emails";
    private static final String GITHUB_AUTHORIZATION_SERVER_URL = "http://github.com/login/oauth/access_token";
    private static final String GITHUB_RESOURCE_SERVER_EMAIL_API_URL = "http://api.github.com/" + SCOPE;
    private static final String GITHUB_EMAIL_API_ACCEPT_HEADER = "application/vnd.github.v3+json";

    public AccessToken getAccessToken(String code) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json");
        headers.setAll(header);

        MultiValueMap<String, String> requestPayloads = new LinkedMultiValueMap<>();
        Map<String, String> requestPayload = new HashMap<>();
        requestPayload.put("client_id", CLIENT_ID);
        requestPayload.put("client_secret", "a15c50d545a9d7589427ff754e5e4193679b7039");
        requestPayload.put("code", code);
        log.debug("code {}", code);
        requestPayloads.setAll(requestPayload);


        HttpEntity<?> request = new HttpEntity<>(requestPayloads, headers);
        ResponseEntity<?> response = new RestTemplate().postForEntity(
                GITHUB_AUTHORIZATION_SERVER_URL, request, AccessToken.class);
        return (AccessToken) response.getBody();
    }

    public List<String> getUserEmails(AccessToken accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", GITHUB_EMAIL_API_ACCEPT_HEADER);
        httpHeaders.set("Authorization", accessToken.getAuthorizationValue());
        log.debug("AccessToken {}", accessToken);

        HttpEntity<?> request = new HttpEntity<>(httpHeaders);
        ResponseEntity<Object[]> response = new RestTemplate().exchange(
                GITHUB_RESOURCE_SERVER_EMAIL_API_URL,
                HttpMethod.GET,
                request,
                Object[].class
        );

        Object[] userEmails = response.getBody();
        log.debug("userEmails {}", userEmails);

        String publicEmail = (String) ((LinkedHashMap) userEmails[0]).get("email");
        String privateEmail = (String) ((LinkedHashMap) userEmails[1]).get("email");

        return List.of(publicEmail, privateEmail);
    }

    public void saveUserEmail(List<String> userEmails) {
        String publicEmail = userEmails.get(0);
        String privateEmail = userEmails.get(1);

        if (!isDuplicate(publicEmail)) {
            User user = new User(null, null, publicEmail, privateEmail, null);
            userRepository.save(user);
        }
    }

    private boolean isDuplicate(String publicEmail) {
        return userRepository.findByEmail(publicEmail).isPresent();
    }
}
