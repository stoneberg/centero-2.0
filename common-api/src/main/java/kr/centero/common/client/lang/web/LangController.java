package kr.centero.common.client.lang.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.centero.common.client.lang.domain.dto.LangDto;
import kr.centero.common.openapi.service.CenteroProxyApiService;
import kr.centero.core.common.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "GHG Lang API", description = "GHG Lang CRUD API")
@RequiredArgsConstructor

@RequestMapping("/api/common/v1/lang")
@RestController
public class LangController {

    private final CenteroProxyApiService centeroApiService;


    @Operation(summary = "언어팩 조회[*]", description = "특정 local의 모든 언어팩 목록을 조회한다.")
    @GetMapping("")
    public ResponseEntity<ApiResponse<LangDto.LangResponse>> findLang(LangDto.LangRequest request)
    {
        ApiResponse<LangDto.LangResponse> response = centeroApiService.getLang(request);
        LangDto.LangResponse data = response.getData();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
