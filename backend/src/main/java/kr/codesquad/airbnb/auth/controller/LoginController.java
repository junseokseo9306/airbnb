package kr.codesquad.airbnb.auth.controller;

import kr.codesquad.airbnb.auth.AccessToken;
import kr.codesquad.airbnb.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/oauth/callback")
    public void login(@RequestParam String code) throws IOException {
        AccessToken accessToken = loginService.getAccessToken(code);

    }

}
