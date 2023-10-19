package kr.centero.ghg.client.auth.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuthDto {

    /**
     * 가입 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "사용자 등록 정보", description = "사용자 등록 정보 DTO")
    public static class SignupRequest {
        @Schema(title = "사용자 아이디", example = "Cornelius")
        private String username;
        @Schema(title = "비밀번호", example = "pwd1234!")
        private String password;
        @Schema(title = "이메일", example = "somebody@centero.com")
        private String email;
        @Schema(title = "사용자 권한", example = "USER")
        private String role;
    }

    /**
     * 로그인 인증 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "사용자 인증 정보", description = "사용자 인증 정보 DTO")
    public static class SigninRequest {
        @Schema(title = "사용자 아이디", example = "Cornelius")
        private String username;
        @Schema(title = "비밀번호", example = "pwd1234!")
        private String password;
    }

    /**
     * 로그인 인증 응답 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SigninResponse {
        private String username;
        private String accessToken;
        @JsonIgnore
        private String refreshToken;
        private List<String> roles;
    }
}
