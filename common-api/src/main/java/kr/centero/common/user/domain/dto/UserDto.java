package kr.centero.common.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import kr.centero.common.common.mybatis.pagination.PageResponse;
import kr.centero.common.common.payload.PageDtoResponse;
import kr.centero.common.user.domain.model.User;
import kr.centero.common.user.mapstruct.UserMapstruct;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {

    /**
     * 사용자 정보 상세 조회 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDetailRequest {
        private Long userId;
        private String username;
        private String email;
        private String role;
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