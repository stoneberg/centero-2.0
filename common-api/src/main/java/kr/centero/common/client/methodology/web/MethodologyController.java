package kr.centero.common.client.methodology.web;

import jakarta.validation.Valid;
import kr.centero.common.client.methodology.domain.dto.MethodologyDto;
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
@RequestMapping("/api/common/v1/methodologies")
public class MethodologyController {

    // @PreAuthorize("hasRole('ROLE_ADMIN')") or @PreAuthorize("hasAuthority('ROLE_ADMIN')") or @PreAuthorize("hasRole('ADMIN')")
    // @PreAuthorize("hasAuthority('ROLE_CTRADMIN')")
    // @PreAuthorize("hasAnyAuthority('ROLE_CTRADMIN', 'ROLE_NZRADMIN')")
    // @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')") or @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    // @PreAuthorize("hasAuthority('ROLE_CTRADMIN') and hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    // @PreAuthorize("hasAnyAuthority('ROLE_CTRADMIN', 'ROLE_NZRADMIN') or hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PreAuthorize("hasAnyRole({'CENTERO_ADMIN', 'CENTERO_USER'})")
    @GetMapping("/find-all")
    public ResponseEntity<ApiResponse> findAll() {
        return ApiResponse.ok("fetch all user info");
    }

    @PreAuthorize("hasRole('CENTERO_USER')")
    @GetMapping("/find-one/{id}")
    public ResponseEntity<ApiResponse> findOne(@PathVariable("id") Long id) {
        return ApiResponse.ok("fetch one user info  " + id);
    }

    @PreAuthorize("hasRole('CENTERO_USER')")
    @GetMapping("/model-attribute-valid")
    public ResponseEntity<ApiResponse> modelAttributeValid(@Valid MethodologyDto modelDto) {
        log.info("modelAttributeValid======================={}", modelDto);
        return ApiResponse.ok("test model attribute valid");
    }

    @PreAuthorize("hasRole('NETZERO_USER')")
    @PostMapping("/method-argument-valid")
    public ResponseEntity<ApiResponse> methodArgumentValid(@Valid @RequestBody MethodologyDto modelDto) {
        log.info("methodArgumentValid======================={}", modelDto);
        return ApiResponse.ok("test method argument valid");
    }

}
