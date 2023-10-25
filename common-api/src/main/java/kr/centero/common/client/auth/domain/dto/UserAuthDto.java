package kr.centero.common.client.auth.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
        @NotBlank
        private String username;
        @Schema(title = "비밀번호", example = "pwd1234!")
        @NotBlank
        private String password;
        @Schema(title = "이메일", example = "somebody@centero.com")
        @NotBlank
        @Email
        private String email;
        @Schema(title = "domain", example = "CENTERO")
        @NotBlank
        private String domain;
        @Schema(title = "사용자 권한", example = "CENTERO_USER")
        @NotBlank
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
        @Schema(title = "사용자 아이디", example = "Lee")
        @NotBlank
        private String username;
        @Schema(title = "비밀번호", example = "pwd1")
        @NotBlank
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
        private List<String> roles;
    }
}
