package kr.centero.netzero.openapi.service;

import kr.centero.core.common.payload.ApiResponse;
import kr.centero.netzero.client.user.domain.dto.UserDto;
import kr.centero.netzero.config.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "common-server", url = "${api-server.common-url}", configuration = OpenFeignConfig.class)
public interface UserInfoFeignClient {
    @GetMapping("/api/common/v1/openapi/users")
    ApiResponse<List<UserDto.UserResponse>> findUsers();
}
