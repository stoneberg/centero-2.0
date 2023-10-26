package kr.centero.common.client.user.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.common.client.user.domain.dto.UserDto;
import kr.centero.common.client.user.service.UserService;
import kr.centero.common.common.security.CustomLogoutHandler;
import kr.centero.core.common.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Centero User API", description = "Centero User CRUD API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/v1/users")
public class UserController {

    private final CustomLogoutHandler customLogoutHandler;
    private final UserService userService;


    // 사용자 목록 조회
    @Operation(summary = "Centero User 조회", description = "전체 Centero User 정보를 조회한다.")
    @GetMapping
    public ResponseEntity<ApiResponse> findUsers() {
        return ApiResponse.ok(userService.findUsers());
    }

    // A.사용자 상세 조건 조회 (request param: username, email, role) : Mapping Query String Parameters to ModelAttributes
    @Operation(summary = "Centero User 조회", description = "Centero User 정보를 조회한다. [GetMapping]")
    @GetMapping("/query")
    public ResponseEntity<ApiResponse> findUserByCondGetMapping(UserDto.UserRequest userRequest) {
        return ApiResponse.ok(userService.findUserByCond(userRequest));
    }

    // B.사용자 상세 조건 조회 (request param: username, email, role) : Mapping requestBody auto mapping
    @Operation(summary = "Centero User 조회", description = "Centero User 정보를 조회한다. [PostMapping]")
    @PostMapping("/query")
    public ResponseEntity<ApiResponse> findUserByCondPostMapping(@RequestBody UserDto.UserRequest userRequest) {
        return ApiResponse.ok(userService.findUserByCond(userRequest));
    }

    // 사용자 상세 조회 페이징 (request param: username, email, role) :  Mapping Query String Parameters to ModelAttributes
    @Operation(summary = "Centero User 조회", description = "Centero User page 를 조회한다. [GetMapping]")
    @GetMapping("/pages")
    public ResponseEntity<ApiResponse> findUserByPageGetMapping(UserDto.UserPageRequest userPageRequest) {
        return ApiResponse.ok(userService.findPagesByCond(userPageRequest));
    }

    // 사용자 상세 조회 페이징 (request param: username, email, role) : Mapping requestBody auto mapping
    @Operation(summary = "Centero User 조회", description = "Centero User page 를 조회한다. [PostMapping]")
    @PostMapping("/pages")
    public ResponseEntity<ApiResponse> findUserByPagePostMapping(@RequestBody UserDto.UserPageRequest userPageRequest) {
        log.info("PAG#1===============>{}", userPageRequest);
        return ApiResponse.ok(userService.findPagesByCond(userPageRequest));
    }

    // 사용자 수정
    @Operation(summary = "Centero User 수정", description = "Centero User 정보를 수정한다.")
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long userId, @RequestBody UserDto.UserUpdateRequest userUpdateRequest) {
        // if use path variable as query condition
        // userDetailRequest.setUserId(userId);
        userService.updateUser(userUpdateRequest);
        return ApiResponse.ok();
    }

    // 사용자 삭제
    @Operation(summary = "Centero User 삭제", description = "Centero User 정보를 삭제한다.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ApiResponse.ok();
    }

    // 사용자 등록은 UserAuthController.signup 참조


    // ghg, netzero 에 로그아웃 요청 처리
    @GetMapping("/logout")
    public ResponseEntity<HttpStatus> logout(HttpServletRequest request, HttpServletResponse response) {
        customLogoutHandler.deleteJwtLoginSession(request, response);
        return ResponseEntity.ok().build();
    }

}
