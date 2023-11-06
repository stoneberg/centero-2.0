package kr.centero.ghg.client.credit.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.centero.core.common.payload.ApiResponse;
import kr.centero.ghg.client.credit.domain.dto.CreditDto;
import kr.centero.ghg.client.credit.service.CreditInfoService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Tag(name = "Code API", description = "Code API CRUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ghg/v1/client/credit")
public class CreditController {

    private final CreditInfoService creditInfoService;

    // 대시보드 조회 (request param: ghgProgram,, langCd) : Mapping
    @Operation(summary = "대시보드 조회", description = "대시보드 Credit 정보를 조회한다.")
    @PostMapping("/creditList")
    public ResponseEntity<ApiResponse<List<CreditDto.CreditListResponse>>> findCreditList(@RequestBody CreditDto.CreditListRequest creditListRequest) {
        return ApiResponse.of(creditInfoService.findCreditList(creditListRequest));
    }
    
    // 감축이력 조회 (request param: ghgProgram,, langCd) : Mapping
    @Operation(summary = "감축이력 조회", description = "Credit의 감축이력 조회한다.")
    @PostMapping("/reductionHistory")
    public ResponseEntity<ApiResponse<List<CreditDto.ReductionHistoryResponse>>> findReductionHistory(@RequestBody CreditDto.ReductionHistoryRequest reductionHistoryRequest) {
        return ApiResponse.of(creditInfoService.findReductionHistory(reductionHistoryRequest));
    }
    
    // 프로젝트 정보 조회 (request param: ghgProgram,, langCd) : Mapping
    @Operation(summary = "프로젝트 정보", description = "Credit의 프로젝트 정보 조회한다.")
    @PostMapping("/prjInfo")
    public ResponseEntity<ApiResponse<List<CreditDto.PJTInfoResponse>>> findPJTInfo(@RequestBody CreditDto.PJTInfoRequest PJTInfoRequest) {
        return ApiResponse.of(creditInfoService.findPJTInfo(PJTInfoRequest));
    }
    
    // 거래이력 조회 (request param: ghgProgram,, langCd) : Mapping
    @Operation(summary = "거래이력 조회", description = "거래이력 조회한다.")
    @PostMapping("/tradingHistory")
    public ResponseEntity<ApiResponse<List<CreditDto.TradingHistoryResponse>>> findTradingHistory(@RequestBody CreditDto.TradingHistoryRequest tradingRequest) {
        return ApiResponse.of(creditInfoService.findTradingHistory(tradingRequest));
    }
    
    // Credit 정보 조회 (request param: ghgProgram,, langCd) : Mapping
    @Operation(summary = "Credit 정보 조회", description = "Credit 정보 조회한다.")
    @PostMapping("/creditInfo")
    public ResponseEntity<ApiResponse<List<CreditDto.CreditInfoResponse>>> findCreditInfo(@RequestBody CreditDto.CreditInfoRequest creditInforequest) {
        return ApiResponse.of(creditInfoService.findCreditInfo(creditInforequest));
    }
    
}
