package kr.centero.ghg.client.user.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.centero.core.common.payload.ApiResponse;
import kr.centero.ghg.client.user.domain.dto.UserDto;
import kr.centero.ghg.client.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "GHG User API", description = "GHG User CRUD API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ghg/v1/users")
public class UserController {

    private final UserService userService;

    // 사용자 목록 조회
    @Operation(summary = "GHG User 조회", description = "전체 GHG User 정보를 조회한다.")
    @GetMapping
    public ResponseEntity<ApiResponse> findUsers() {
        return ApiResponse.ok(userService.findUsers());
    }

    // A.사용자 상세 조건 조회 (request param: username, email, role) : Mapping Query String Parameters to ModelAttributes
    @Operation(summary = "GHG User 조회", description = "GHG User 정보를 조회한다. [GetMapping]")
    @GetMapping("/query")
    public ResponseEntity<ApiResponse> findUserByCondGetMapping(UserDto.UserRequest userRequest) {
        return ApiResponse.ok(userService.findUserByCond(userRequest));
    }

    // B.사용자 상세 조건 조회 (request param: username, email, role) : Mapping requestBody auto mapping
    @Operation(summary = "GHG User 조회", description = "GHG User 정보를 조회한다. [PostMapping]")
    @PostMapping("/query")
    public ResponseEntity<ApiResponse> findUserByCondPostMapping(@RequestBody UserDto.UserRequest userRequest) {
        return ApiResponse.ok(userService.findUserByCond(userRequest));
    }

    // 사용자 상세 조회 페이징 (request param: username, email, role) :  Mapping Query String Parameters to ModelAttributes
    @Operation(summary = "GHG User 조회", description = "GHG User page 를 조회한다. [GetMapping]")
    @GetMapping("/pages")
    public ResponseEntity<ApiResponse> findUserByPageGetMapping(UserDto.UserPageRequest userPageRequest) {
        return ApiResponse.ok(userService.findPagesByCond(userPageRequest));
    }

    // 사용자 상세 조회 페이징 (request param: username, email, role) : Mapping requestBody auto mapping
    @Operation(summary = "GHG User 조회", description = "GHG User page 를 조회한다. [PostMapping]")
    @PostMapping("/pages")
    public ResponseEntity<ApiResponse> findUserByPagePostMapping(@RequestBody UserDto.UserPageRequest userPageRequest) {
        log.info("PAG#1===============>{}", userPageRequest);
        return ApiResponse.ok(userService.findPagesByCond(userPageRequest));
    }

    // 사용자 수정
    @Operation(summary = "GHG User 수정", description = "GHG User 정보를 수정한다.")
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long userId, @RequestBody UserDto.UserUpdateRequest userUpdateRequest) {
        // if use path variable as query condition
        // userDetailRequest.setUserId(userId);
        userService.updateUser(userUpdateRequest);
        return ApiResponse.ok();
    }

}
