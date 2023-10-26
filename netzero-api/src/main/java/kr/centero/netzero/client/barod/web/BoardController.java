package kr.centero.netzero.client.barod.web;

import io.swagger.v3.oas.annotations.Operation;
import kr.centero.core.common.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/netzero/v1/boards")
public class BoardController {

    @GetMapping
    public ResponseEntity<ApiResponse> findBoards() {
        return ApiResponse.ok("findUsers");
    }

}
