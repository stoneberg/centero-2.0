package kr.centero.common.client.auth.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
        @NotBlank(message = "{dto.name.blank}")
        private String username;

        @Schema(title = "비밀번호", example = "pwd1234!")
        @NotBlank(message = "{dto.name.blank}")
        private String password;

        @Schema(title = "이메일", example = "somebody@centero.com")
        @NotBlank(message = "{dto.name.blank}")
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
        @NotBlank(message = "{dto.name.blank}")
        private String username;

        @Schema(title = "비밀번호", example = "pwd1")
        @NotBlank(message = "{dto.name.blank}")
        private String password;
    }

    /**
     * 로그인 인증 응답 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "로그인 인증 응답", description = "로그인 인증 응답 DTO")
    public static class SigninResponse {
        @Schema(title = "사용자아이디", example = "somebody123")
        private String username;

        @Schema(title = "access token", example = "xxxxxxxxxxxxxxxxxxxxxxxxxx")
        private String accessToken;

        @Schema(title = "사용자 권한", example = "[CENTERO_USER, NETZERO_USER]")
        private List<String> roles;
    }
}
