package kr.centero.ghg.client.menu.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.centero.core.common.payload.ApiResponse;
import kr.centero.ghg.client.menu.domain.dto.MenuDto;
import kr.centero.ghg.client.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Tag(name = "GHG Menu API", description = "GHG Menu CRUD API")
@RequiredArgsConstructor

@RequestMapping("/api/ghg/v1/menu")
@RestController
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "메뉴 조회[*]", description = "GHG 역할별 메뉴를 조회한다.")
    @GetMapping
    public ResponseEntity<ApiResponse<MenuDto.MenuResponse>>
        findRoleMenus(MenuDto.MenuRequest request)
    {
        return ApiResponse.of( menuService.findRoleMenus(request) );
    }
}
