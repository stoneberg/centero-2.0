package kr.centero.common.client.menu.web;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import kr.centero.common.client.menu.domain.dto.MenuDto;
import kr.centero.common.openapi.service.CenteroProxyApiService;
import kr.centero.core.common.interceptor.timezone.LocaleTimeZoneContextHolder;
import kr.centero.core.common.payload.ApiResponse;
import kr.centero.core.common.resolver.CenteroLocaleContext;
import kr.centero.core.common.serializer.TimeZoneSerializer;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.TimeZone;

@Slf4j
@Tag(name = "Common Menu API", description = "Common Menu CRUD API")
@RequiredArgsConstructor
@RequestMapping("/api/common/v1/menu")
@RestController
public class MenuController {

    private final CenteroProxyApiService centeroApiService;


    @Operation(summary = "메뉴 조회[*]", description = "Common 역할별 메뉴를 조회한다.")
    @GetMapping("")
    public ResponseEntity<ApiResponse<MenuDto.MenuResponse>> findRoleMenus(HttpServletRequest req, @ModelAttribute MenuDto.MenuRequest request)
    {
        ApiResponse<MenuDto.MenuResponse> response = centeroApiService.getMenu(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(summary = "타임존 테스트", description = "타임존 테스트")
    @GetMapping(value = "/test")
    public ResponseEntity<TestResponse> test(HttpServletRequest req)
    {
        TestResponse response = new TestResponse();
        CenteroLocaleContext context = LocaleTimeZoneContextHolder.getContext();
        TimeZone timeZone = context.getTimeZone();

        response.setCreateAt(LocalDateTime.now()); // UTC 시간
        response.setTimeZone(timeZone);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Data
    static class TestResponse
    {
        @JsonSerialize(using = TimeZoneSerializer.class)
        private LocalDateTime createAt;

        private TimeZone timeZone;
    }

}
