package kr.centero.common.client.methology.web;

import jakarta.validation.Valid;
import kr.centero.common.client.methology.domain.dto.MethologyDto;
import kr.centero.core.common.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Test for Uri permission of SpringSecurity
 * "/find-all: ROLE_ADMIN"
 * "/find-one: ROLE_USER"
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/v1/methologies")
public class MethologyController {

    @PreAuthorize("hasRole({'ROLE_ADMIN', 'ROLE_USER'})")
    @GetMapping("/find-all")
    public ResponseEntity<ApiResponse> findAll() {
        return ApiResponse.ok("fetch all user info");
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/find-one/{id}")
    public ResponseEntity<ApiResponse> findOne(@PathVariable("id") Long id) {
        return ApiResponse.ok("fetch one user info  " + id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/model-attribute-valid")
    public ResponseEntity<ApiResponse> modelAttributeValid(@Valid MethologyDto modelDto) {
        log.info("modelAttributeValid======================={}", modelDto);
        return ApiResponse.ok("test model attribute valid");
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/method-argument-valid")
    public ResponseEntity<ApiResponse> methodArgumentValid(@Valid @RequestBody MethologyDto modelDto) {
        log.info("methodArgumentValid======================={}", modelDto);
        return ApiResponse.ok("test method argument valid");
    }

}
