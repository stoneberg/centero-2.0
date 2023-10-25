package kr.centero.common.client.auth.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.centero.common.client.auth.domain.dto.UserAuthDto;
import kr.centero.common.client.auth.service.UserAuthService;
import kr.centero.core.common.payload.ApiResponse;
import kr.centero.core.common.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Centero User Auth API", description = "Centero User Auth API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/v1/auth")
public class UserAuthController {

    private final UserAuthService userTokenService;
    private final CookieUtil cookieUtil;

    // 사용자 회원 가입 처리 -> 사용자 등록 후, access, refresh 토큰 발급(가입 시 자동 로그인 상태)
    @Operation(summary = "Centero User 회원 가입", description = "Centero User 회원 가입을 처리하고 토큰을 발급한다.")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody @Valid UserAuthDto.SignupRequest signupRequest, HttpServletResponse response) {
        UserAuthDto.SigninResponse signinResponse = userTokenService.registerUser(signupRequest, response);
        return ApiResponse.ok(signinResponse);
    }

    // 로그인 처리 -> access, refresh 토큰 발급
    @Operation(summary = "Centero User 로그인", description = "Centero User 로그인을 처리하고 토큰을 발급한다.")
    @PostMapping("/signin")
    public ResponseEntity<ApiResponse> signin(@RequestBody @Valid UserAuthDto.SigninRequest signinRequest, HttpServletResponse response) {
        UserAuthDto.SigninResponse signinResponse = userTokenService.issueUserToken(signinRequest, response);
        return ApiResponse.ok(signinResponse);
    }

    // 로그아웃(/api/common/v1/auth/signout) -> SecurityConfig 에 정의된 CustomLogoutHandler를 통해 처리됨


    // @todo: remove this test code
    @GetMapping("/createCookie")
    public String createCookie(HttpServletResponse response) {
        // 쿠키 값으로 사용할 랜덤 UUID 생성
        String tokenValue = UUID.randomUUID().toString();
        cookieUtil.writeAccessCookie(tokenValue, response);
        return "쿠키가 생성되었습니다.";
    }

    @GetMapping("/readCookie")
    public String readCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies(); // 요청에서 모든 쿠키 가져오기
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CookieUtil.ACCESS_TOKEN_COOKIE.equals(cookie.getName())) {
                    return "쿠키 값: " + cookie.getValue();
                }
            }
        }
        return "access_token 쿠키를 찾을 수 없습니다.";
    }

}
