package kr.centero.common.auth.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.common.auth.domain.dto.UserAuthDto;
import kr.centero.common.auth.service.UserAuthService;
import kr.centero.common.common.payload.ApiResponse;
import kr.centero.common.common.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Auth API", description = "User Auth API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/v1/auth")
public class UserAuthController {

    private final UserAuthService userTokenService;

    // 사용자 회원 가입 처리 -> 사용자 등록 후, access, refresh 토큰 발급(가입 시 자동 로그인 상태)
    @Operation(summary = "사용자 회원 가입", description = "사용자 회원 가입을 처리하고 토큰을 발급한다.")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody UserAuthDto.SignupRequest signupRequest, HttpServletResponse response) {
        UserAuthDto.SigninResponse signinResponse = userTokenService.registerUser(signupRequest, response);
        return ApiResponse.ok(signinResponse);
    }

    // 로그인 처리 -> access, refresh 토큰 발급
    @Operation(summary = "사용자 로그인", description = "사용자 로그인을 처리하고 토큰을 발급한다.")
    @PostMapping("/signin")
    public ResponseEntity<ApiResponse> signin(@RequestBody UserAuthDto.SigninRequest signinRequest, HttpServletResponse response) {
        UserAuthDto.SigninResponse signinResponse = userTokenService.issueUserToken(signinRequest, response);
        return ApiResponse.ok(signinResponse);
    }

    // refresh token 처리 -> access 토큰 재발급, refresh 토큰 재사용
    @Operation(summary = "사용자 토큰 재발급", description = "사용자 만료된 토큰을 재발급한다.")
    @GetMapping("/refresh")
    public ResponseEntity<ApiResponse> refresh(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = CookieUtil.getCookieValueByName(request, CookieUtil.REFRESH_TOKEN_COOKIE);
        UserAuthDto.SigninResponse signinResponse = userTokenService.issueNewAccessToken(refreshToken, response);
        return ApiResponse.ok(signinResponse);
    }

    // 로그아웃(/api/common/v1/auth/signout) -> SecurityConfig 에 정의된 CustomLogoutHandler를 통해 처리
}