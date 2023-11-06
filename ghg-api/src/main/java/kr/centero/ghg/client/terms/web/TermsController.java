package kr.centero.ghg.client.terms.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.centero.core.common.payload.ApiResponse;
import kr.centero.ghg.client.terms.domain.dto.TermsDto;
import kr.centero.ghg.client.terms.service.TermsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "GHG Terms API", description = "GHG Terms CRUD API")
@RequiredArgsConstructor

@RequestMapping("/api/ghg/v1/terms")
@RestController
public class TermsController {

    private final TermsService termsService;

    @Operation(summary = "최신 약관 조회", description = "최신 약관 조회")
    @GetMapping
    public ResponseEntity<ApiResponse<TermsDto.TermsResponse>> findTermsByCond(@Valid TermsDto.TermsRequest request) {
        return ApiResponse.of(termsService.findTermsByCond(request));
    }

    @Operation(summary = "사용자 최신 약관 동의 여부 조회", description = "사용자 최신 약관 동의 여부 조회")
    @GetMapping("/accounts")
    public ResponseEntity<ApiResponse<TermsDto.TermsResponse>> findTermsByAccounts(@RequestParam(value = "accountId") String accountId) {
        return ApiResponse.of(termsService.findTermsByAccounts(accountId));
    }

    @Operation(summary = "신규 약관 등록", description = "신규 약관 등록")
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveTerms(@Valid TermsDto.TermsSaveRequest request) {
        return termsService.saveTerms(request);
    }

    @Operation(summary = "약관 수정", description = "약관 수정")
    @PutMapping
    public ResponseEntity<ApiResponse<Object>> modifyTerms(@Valid TermsDto.TermsModifyRequest request) {
        return termsService.modifyTerms(request);
    }

    @Operation(summary = "약관 개정", description = "약관 개정")
    @PostMapping("/revision")
    public ResponseEntity<ApiResponse<Object>> revisionTerms(@Valid TermsDto.TermsRevisionRequest request) {
        return termsService.revisionTerms(request);
    }
}
