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
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/oauth/callback")
    public void login(HttpServletResponse response,
                      @RequestParam String code, HttpSession httpSession) throws IOException {
        AccessToken accessToken = loginService.getAccessToken(code);
        List<String> userEmails = loginService.getUserEmails(accessToken);
        loginService.saveUserEmail(userEmails);

        String email = userEmails.get(0);
        httpSession.setAttribute("email", email);
    }

//    @GetMapping("")
//    public ResponseEntity<ResponseMessage> getEmail(HttpSession httpSession) {
//        String email = (String) httpSession.getAttribute("email");
//
//        if (email == null) {
//            ResponseMessage message = new ResponseMessage(HttpStatus.UNAUTHORIZED, "로그인이 되어있지 않습니다.");
//            return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
//        }
//
//        ResponseMessage message = new ResponseMessage(HttpStatus.OK, "로그인이 정상적으로 처리되었습니다.", email);
//        return new ResponseEntity<>(message, HttpStatus.OK);
//    }
}
