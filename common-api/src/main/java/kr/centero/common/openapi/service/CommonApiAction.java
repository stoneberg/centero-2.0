package kr.centero.common.openapi.service;

import kr.centero.common.client.lang.domain.dto.LangDto;
import kr.centero.common.client.menu.domain.dto.MenuDto;
import kr.centero.core.common.payload.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


public interface CommonApiAction {
    @GetMapping("/menu")
    ApiResponse<MenuDto.MenuResponse> getMenu(@RequestParam MenuDto.MenuRequest request);


    @GetMapping("/lang")
    ApiResponse<LangDto.LangResponse> getLang(@RequestParam LangDto.LangRequest request);





}
