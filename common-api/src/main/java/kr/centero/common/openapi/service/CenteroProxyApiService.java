package kr.centero.common.openapi.service;

import kr.centero.common.client.lang.domain.dto.LangDto;
import kr.centero.common.client.menu.domain.dto.MenuDto;
import kr.centero.core.common.enums.codes.ESvcCd;
import kr.centero.core.common.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * request 객체의 svcCd 코드에 따라서
 * ghg 또는 netzero 와 통신하여 결과를 반환한다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CenteroProxyApiService {

    private final GhgFeignClient ghgFeignClient;
    private final NetzeroFeignClient netzeroFeignClient;

    /**
     * GET /menu
     * @param request
     * @return
     */
    public ApiResponse<MenuDto.MenuResponse> getMenu(@RequestParam MenuDto.MenuRequest request)
    {
        ApiResponse<MenuDto.MenuResponse> response = getTargetFeginClient(request.getSvcCd()).getMenu(request);
        return response;
    }

    /**
     * GET /lang
     * @param request
     * @return
     */

    public ApiResponse<LangDto.LangResponse> getLang(@RequestParam LangDto.LangRequest request)
    {
        ApiResponse<LangDto.LangResponse> response = getTargetFeginClient(request.getSvcCd()).getLang(request);
        return response;
    }



    /**
     * svcCd에 해당하는 FeginClient 반환
     * @param svcCd
     * @return
     */
    private CommonApiAction getTargetFeginClient(String svcCd)
    {
        if ( ESvcCd.GHG.isEquals(svcCd) )
            return ghgFeignClient;

        return netzeroFeignClient;
    }
}
