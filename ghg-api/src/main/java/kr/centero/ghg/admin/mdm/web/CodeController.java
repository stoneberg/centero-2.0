package kr.centero.ghg.admin.mdm.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.centero.core.common.payload.ApiResponse;
import kr.centero.ghg.admin.mdm.service.CodeService;
import kr.centero.ghg.admin.mdm.domain.dto.CodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Code API", description = "Code API CRUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ghg/v1/admin/codes")
public class CodeController {

    private final CodeService service;

    // 코드정보 상세 조건 조회 (request param: searchText, useYn, period) : Mapping
    @Operation(summary = "코드 조회", description = "코드 정보를 조회한다.")
    @PostMapping
    public ResponseEntity<ApiResponse> findAll(@RequestBody CodeDto.CodeRequest codeRequest) {
        return ApiResponse.ok(service.findAll(codeRequest));
    }

    @Operation(summary = "트리구성용 코드 조회", description = "트리구성용 코드 정보를 조회한다.")
    @GetMapping("/trees")
    public ResponseEntity<ApiResponse> findAllCodesForTree() {
        return ApiResponse.ok(service.findCodeForTree());
    }

    @Operation(summary = "코드목록 저장", description = "변경된 코드목록 정보를 저장한다.")
    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveCodeList(@RequestBody CodeDto.CodeListRequest request) {
        service.saveCodeList(request);
        return ApiResponse.ok();
    }
}
