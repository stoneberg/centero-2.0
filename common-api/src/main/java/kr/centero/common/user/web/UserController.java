package kr.centero.common.user.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.centero.common.common.payload.ApiResponse;
import kr.centero.common.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User API", description = "User API CRUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/v1/users")
public class UserController {

    private final UserService userService;

    // 사용자 목록 조회
    @Operation(summary = "User 조회", description = "전체 User 정보를 조회한다.")
    @GetMapping("")
    public ResponseEntity<ApiResponse> fetchAll() {
        return ApiResponse.ok(userService.findAll());
    }


    // 사용자 조회


    // 사용자 상세 조회


    // 사용자 수정
}
