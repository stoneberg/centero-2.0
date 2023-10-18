package kr.centero.common.user.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.centero.common.common.payload.ApiResponse;
import kr.centero.common.user.domain.dto.UserDto;
import kr.centero.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User API", description = "User API CRUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/v1/users")
public class UserController {

    private final UserService userService;

    // 사용자 목록 조회
    @Operation(summary = "User 조회", description = "전체 User 정보를 조회한다.")
    @GetMapping
    public ResponseEntity<ApiResponse> findUsers() {
        return ApiResponse.ok(userService.findUsers());
    }

    // 사용자 조회 (request param: username, email, role) : Mapping Query String Parameters to ModelAttributes
    @Operation(summary = "User 조회", description = "User 정보를 조회한다. [GetMapping]")
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> findUserDetailByGetMapping(@PathVariable Long userId, UserDto.UserDetailRequest userDetailRequest) {
        return ApiResponse.ok(userService.findUserByCond(userId, userDetailRequest));
    }

    // 사용자 상세 조회 (request param: username, email, role) : Mapping requestBody auto mapping
    @Operation(summary = "User 조회", description = "User 정보를 조회한다. [PostMapping]")
    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse> findUserDetailByPostMapping(@PathVariable Long userId, @RequestBody UserDto.UserDetailRequest userDetailRequest) {
        return ApiResponse.ok(userService.findUserByCond(userId, userDetailRequest));
    }

    // 사용자 상세 조회 페이징 (request param: username, email, role) : Mapping requestBody auto mapping
    @Operation(summary = "User 조회", description = "User 정보를 조회한다. [PostMapping]")
    @PostMapping("/{userId}/pages")
    public ResponseEntity<ApiResponse> findUserByPage(@PathVariable Long userId, @RequestBody UserDto.UserDetailRequest userDetailRequest) {
        return ApiResponse.ok(userService.findPagesByCond(userId, userDetailRequest));
    }

    // 사용자 수정
    @Operation(summary = "User 수정", description = "User 정보를 수정한다.")
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long userId, @RequestBody UserDto.UserDetailRequest userDetailRequest) {
        userService.updateUser(userId, userDetailRequest);
        return ApiResponse.ok();
    }

}
