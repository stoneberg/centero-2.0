package kr.centero.ghg.client.account.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.centero.core.common.payload.ApiResponse;
import kr.centero.ghg.client.account.domain.dto.AccountDto.CreditRequest;
import kr.centero.ghg.client.account.domain.model.Credit;
import kr.centero.ghg.client.account.domain.model.Method;
import kr.centero.ghg.client.account.service.AccountService;
import lombok.RequiredArgsConstructor;

@Tag(name = "Account API", description = "Account API CRUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ghg/v1/client/account")
public class AccountController {

    private final AccountService service;

    @Operation(summary = "마이페이지 방법론 목록", description = "사용자 방법론 목록 조회")
    @GetMapping("/mtds/{id}")
    public ResponseEntity<ApiResponse<List<Method>>> findMtds(@PathVariable("id") String id) {
        return ApiResponse.of(service.findMtdList(id), "사용자 방법론 목록 조회");
    }

    @Operation(summary = "마이페이지 감축 목록", description = "사용자 감축 목록 조회")
    @PostMapping("/credits")
    public ResponseEntity<ApiResponse<List<Credit>>> findCredits(@RequestBody CreditRequest req) {
        return ApiResponse.of(service.findCreditList(req), "사용자 감축 목록 조회");
    }
}
