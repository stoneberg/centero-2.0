package kr.centero.common.client.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import kr.centero.common.client.user.mapstruct.UserMapstruct;
import kr.centero.common.client.user.domain.model.User;
import kr.centero.core.common.pagination.PageResponse;
import kr.centero.core.common.payload.PageDtoResponse;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {

    /**
     * 사용자 검색 조건 조회 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRequest {
        private Long userId;
        private String username;
        private String email;
        private String role;
    }

    /**
     * 사용자 검색 조건 페이징 조회 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserPageRequest {
        private Long userId;
        private String username;
        private String email;
        private String role;
        private Integer pageNo;
        private Integer pageSize;
    }

    /**
     * 사용자 수정 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserUpdateRequest {
        private Long userId;
        private String username;
        private String email;
    }

    /**
     * 사용자 조회 응답 DTO
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
        private String domain;
        private Boolean active;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class UserPageDtoResponse extends PageDtoResponse {
        private List<UserResponse> users;
        private long total;
        private int pageNo;
        private int pageSize;
        private boolean isFirst;
        private boolean hasNext;
        private boolean isLast;

        // Model Page -> DTO Page
        public static UserPageDtoResponse fromPageResponse(PageResponse<User> pageResponse) {
            return convertPageDto(pageResponse, page -> UserPageDtoResponse.builder()
                    .users(UserMapstruct.INSTANCE.toUserDto(page.getList()))
                    .total(page.getTotal())
                    .pageNo(page.getPageNo())
                    .pageSize(page.getPageSize())
                    .isFirst(page.isFirst())
                    .isLast(page.isLast())
                    .hasNext(page.hasNext())
                    .build());
        }
    }

}
