package kr.centero.common.openapi.service;

import kr.centero.common.config.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * GHG API 서버 연동
 */
@FeignClient(name = "ghg-server",
        url = "${api-server.ghg-url}/api/ghg/v1",
        configuration = OpenFeignConfig.class)
public interface GhgFeignClient extends CommonApiAction {


}
