package kr.centero.ghg.api.user.domain.dto;

import kr.centero.ghg.api.user.domain.model.User;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserCreateRequest {
        private String name;
        private String email;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserCreateResponse {
        private String id;
        private String name;
        private String email;

        public static UserCreateResponse from(User user) {
            return UserCreateResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponse {
        private String id;
        private String name;
        private String email;

        public UserResponse(User user) {
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
        }
    }

}
