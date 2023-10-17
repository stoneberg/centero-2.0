package kr.centero.ghg.api.user.web;

import kr.centero.core.payload.ApiResponse;
import kr.centero.ghg.api.user.domain.dto.UserDto;
import kr.centero.ghg.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/centero/ghg/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse> registerUser(@RequestBody UserDto.UserCreateRequest userCreateRequest) {
        UserDto.UserCreateResponse user = userService.createUser(userCreateRequest);
        return ApiResponse.ok(user);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findUsers() {
        return ApiResponse.ok(userService.findUsers());
    }

}
