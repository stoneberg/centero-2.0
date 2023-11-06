package kr.centero.ghg.client.menu.service;

import kr.centero.ghg.client.menu.domain.dto.MenuDto;
import kr.centero.ghg.client.menu.domain.model.Menu;
import kr.centero.ghg.client.menu.mapper.MenuMapper;
import kr.centero.ghg.client.menu.mapstruct.MenuMapstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MenuService {

    private final MenuMapper menuMapper;

    /**
     * 역할(Role) 메뉴 목록 조회
     * @param request
     * @return
     */
    public MenuDto.MenuResponse findRoleMenus(MenuDto.MenuRequest request)
    {
        List<Menu> menuList = menuMapper.findMenuByCond(request);

        List<MenuDto.Menu> menuDtoMenus = MenuMapstruct.INSTANCE.toMenuDto(menuList);

        MenuDto.MenuResponse response = new MenuDto.MenuResponse();
        response.setRole(request.getRole());
        response.setMenus(menuDtoMenus);

        return response;
    }


}
