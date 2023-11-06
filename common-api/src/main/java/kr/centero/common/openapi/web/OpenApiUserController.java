package kr.centero.common.openapi.web;

import io.swagger.v3.oas.annotations.Operation;
import kr.centero.common.client.user.domain.dto.UserDto;
import kr.centero.common.client.user.service.UserService;
import kr.centero.core.common.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/v1/openapi/users")
public class OpenApiUserController {
    private final UserService userService;

    @Operation(summary = "Centero User 조회", description = "전체 Centero User 정보를 조회한다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto.UserResponse>>> findUsers() {
        return ApiResponse.of(userService.findUsers());
    }

}
