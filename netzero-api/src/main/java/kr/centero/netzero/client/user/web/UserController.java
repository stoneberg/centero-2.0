package kr.centero.netzero.client.user.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.core.common.payload.ApiResponse;
import kr.centero.core.common.util.CookieUtil;
import kr.centero.netzero.client.user.domain.dto.UserDto;
import kr.centero.netzero.client.user.service.UserService;
import kr.centero.netzero.openapi.service.UserAuthFeignClient;
import kr.centero.netzero.openapi.service.UserInfoFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "Netzero User API", description = "Netzero 사용자 CRUD API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/netzero/v1/users")
public class UserController {

    private final UserAuthFeignClient userAuthFeignClient;
    private final UserService userService;
    private final CookieUtil cookieUtil;
    private final UserInfoFeignClient userInfoFeignClient;


    // 사용자 목록 조회 (netzero -> common, server to server)
    @Operation(summary = "Netzero User 조회", description = "전체 Netzero User 정보를 조회한다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto.UserResponse>>> findUsers() {
        ApiResponse<List<UserDto.UserResponse>> users = userInfoFeignClient.findUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // A.사용자 상세 조건 조회 (request param: username, email, role) : Mapping Query String Parameters to ModelAttributes
    @Operation(summary = "Netzero User 조회", description = "Netzero User 정보를 조회한다. [GetMapping]")
    @GetMapping("/query")
    public ResponseEntity<ApiResponse<List<UserDto.UserResponse>>> findUserByCondGetMapping(UserDto.UserRequest userRequest) {
        log.info("userRequest: {}", userRequest);
        log.info("userRequest:getCenteroUserId {}", userRequest.getCenteroUserId());
        log.info("userRequest:getCenteroUserRoles {}", userRequest.getCenteroUserRoles());
        log.info("userRequest:getLocale {}", userRequest.getLocale());
        return ApiResponse.of(userService.findUserByCond(userRequest));
    }

    // B.사용자 상세 조건 조회 (request param: username, email, role) : Mapping requestBody auto mapping
    @Operation(summary = "Netzero User 조회", description = "Netzero User 정보를 조회한다. [PostMapping]")
    @PostMapping("/query")
    public ResponseEntity<ApiResponse<List<UserDto.UserResponse>>> findUserByCondPostMapping(@RequestBody UserDto.UserRequest userRequest) {
        return ApiResponse.of(userService.findUserByCond(userRequest));
    }

    // 사용자 상세 조회 페이징 (request param: username, email, role) :  Mapping Query String Parameters to ModelAttributes
    @Operation(summary = "Netzero User 조회", description = "Netzero User page 를 조회한다. [GetMapping]")
    @GetMapping("/pages")
    public ResponseEntity<ApiResponse<UserDto.UserPageDtoResponse>> findUserByPageGetMapping(UserDto.UserPageRequest userPageRequest) {
        return ApiResponse.of(userService.findPagesByCond(userPageRequest));
    }

    // 사용자 상세 조회 페이징 (request param: username, email, role) : Mapping requestBody auto mapping
    @Operation(summary = "Netzero User 조회", description = "Netzero User page 를 조회한다. [PostMapping]")
    @PostMapping("/pages")
    public ResponseEntity<ApiResponse<UserDto.UserPageDtoResponse>> findUserByPagePostMapping(@RequestBody UserDto.UserPageRequest userPageRequest) {
        return ApiResponse.of(userService.findPagesByCond(userPageRequest));
    }

    // 사용자 수정
    @Operation(summary = "Netzero User 수정", description = "Netzero User 정보를 수정한다.")
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> updateUser(@RequestBody UserDto.UserUpdateRequest userUpdateRequest) {
        userService.updateUser(userUpdateRequest);
        return ApiResponse.ok();
    }


    // ghg, netzero 에서 온 로그아웃 요청 처리
    @GetMapping("/signout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpServletResponse response) {
        ApiResponse<Void> apiResponse = userAuthFeignClient.logout();
        if (Boolean.TRUE.equals(apiResponse.getSuccess())) {
            cookieUtil.deleteAccessCookie(response);
        }
        return ResponseEntity.ok(apiResponse);
    }


}
