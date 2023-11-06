package kr.centero.ghg.admin.mdm.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.centero.core.common.payload.ApiResponse;
import kr.centero.ghg.admin.mdm.domain.dto.CodeDto;
import kr.centero.ghg.admin.mdm.service.CodeService;
import kr.centero.ghg.client.credit.service.CreditInfoService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Tag(name = "Code API", description = "Code API CRUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ghg/v1/admin/codes")
public class CodeController {

    private final CodeService service;
    private final CreditInfoService creditInfoService;

    // 코드정보 상세 조건 조회 (request param: searchText, useYn, period) : Mapping
    @Operation(summary = "코드 조회", description = "코드 정보를 조회한다.")
    @PostMapping
<<<<<<< HEAD
    public ResponseEntity<ApiResponse<List<CodeDto.CodeResponse>>> findAll(@RequestBody CodeDto.CodeRequest codeRequest) {
=======
    public ResponseEntity<ApiResponse> findAll(@RequestBody CodeDto.CodeRequest codeRequest) {
>>>>>>> 84e2969dd4ad33c8fdc17749b1f77b5a83d5067f
        return ApiResponse.of(service.findAll(codeRequest));
    }

    @Operation(summary = "트리구성용 코드 조회", description = "트리구성용 코드 정보를 조회한다.")
    @GetMapping("/trees")
<<<<<<< HEAD
    public ResponseEntity<ApiResponse<List<CodeDto.CodeResponse>>> findAllCodesForTree() {
=======
    public ResponseEntity<ApiResponse> findAllCodesForTree() {
>>>>>>> 84e2969dd4ad33c8fdc17749b1f77b5a83d5067f
        return ApiResponse.of(service.findCodeForTree());
    }

    @Operation(summary = "코드목록 저장", description = "변경된 코드목록 정보를 저장한다.")
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Void>> saveCodeList(@RequestBody CodeDto.CodeListRequest request) {
        service.saveCodeList(request);
        return ApiResponse.ok();
    }
    
}
