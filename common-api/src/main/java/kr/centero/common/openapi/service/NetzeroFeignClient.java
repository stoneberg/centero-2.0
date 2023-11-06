package kr.centero.common.openapi.service;

import kr.centero.common.config.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Netzero API 서버 연동
 */
@FeignClient(name = "netzero-server",
        url = "${api-server.netzero-url}/api/netzero/v1",
        configuration = OpenFeignConfig.class)
public interface NetzeroFeignClient extends CommonApiAction {

}
