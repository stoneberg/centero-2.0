package kr.centero.common.client.methology.web;

import kr.centero.core.common.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/v1/methologies")
public class MethologyController {

    @GetMapping
    public ResponseEntity<ApiResponse> findAll() {
        return ApiResponse.ok();
    }

}
