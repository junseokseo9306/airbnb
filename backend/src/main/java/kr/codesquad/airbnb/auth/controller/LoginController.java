package kr.codesquad.airbnb.auth.controller;

import kr.codesquad.airbnb.auth.AccessToken;
import kr.codesquad.airbnb.auth.GitHubUserDto;
import kr.codesquad.airbnb.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void login(HttpServletResponse response,
                      @RequestParam String code, HttpSession httpSession) throws IOException {
        AccessToken accessToken = loginService.getAccessToken(code);
        GitHubUserDto userInfo = loginService.getUserInfo(accessToken);
        loginService.saveUserInfo(userInfo);

        String userId = userInfo.getUserId();
        httpSession.setAttribute("userId", userId);

    }

    @GetMapping("")
    public ResponseEntity<String> getUserId(HttpSession httpSession) {
        String userId = (String) httpSession.getAttribute("userId");

        if (userId == null) {
//            ResponseMessage message = new ResponseMessage(HttpStatus.UNAUTHORIZED, "로그인이 되어있지 않습니다.");
            return new ResponseEntity<>("로그인이 되어있지 않습니다.", HttpStatus.UNAUTHORIZED);
        }

//        ResponseMessage message = new ResponseMessage(HttpStatus.OK, "로그인이 정상적으로 처리되었습니다.", email);
        return new ResponseEntity<>("로그인이 정상적으로 처리되었습니다.", HttpStatus.OK);
    }
}
