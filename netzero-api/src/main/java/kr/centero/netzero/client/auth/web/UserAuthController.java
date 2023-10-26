package kr.centero.netzero.client.auth.web;

import kr.centero.core.common.payload.ApiResponse;
import kr.centero.netzero.client.auth.feign.UserAuthFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api/netzero/v1/user")
public class UserAuthController {
    private final UserAuthFeignClient userAuthFeignClient;

    @GetMapping("/signout")
    public ResponseEntity<ApiResponse> signout() {
        userAuthFeignClient.signout();
        return ApiResponse.ok();
    }
}
