package kr.centero.common.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    /**
     * 사용자 목록 조회 응답 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponse {
        private Long userId;
        private String username;
        @JsonIgnore
        private String password;
        private String email;
        private String role;
    }

}
