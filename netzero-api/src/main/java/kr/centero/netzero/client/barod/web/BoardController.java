package kr.centero.netzero.client.barod.web;

import kr.centero.core.common.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/netzero/v1/boards")
public class BoardController {

    @PreAuthorize("hasRole('NETZERO_USER')")
    @GetMapping
    public ResponseEntity<ApiResponse<Object>> findBoards() {
        return ApiResponse.ok("findUsers");
    }

}
