package kr.centero.netzero.openapi.service;

import kr.centero.core.common.model.CenteroUserToken;
import kr.centero.core.common.model.RedisToken;
import kr.centero.core.common.payload.ApiResponse;
import kr.centero.netzero.config.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "common-server", url = "${api-server.common-url}", configuration = OpenFeignConfig.class)
public interface UserAuthFeignClient {
    @GetMapping("/api/common/v1/openapi/auth/access-token")
    ApiResponse<RedisToken> findByAccessToken(@RequestParam String accessToken);

    @PostMapping("/api/common/v1/openapi/auth/refresh")
    ApiResponse<String> refresh(@RequestBody CenteroUserToken centeroUserToken);

    @GetMapping("/api/common/v1/openapi/signout")
    ApiResponse<Void> logout();
}
