package kr.centero.ghg.client.menu.mapper;

import kr.centero.core.common.pagination.PageMapper;
import kr.centero.ghg.client.menu.domain.dto.MenuDto;
import kr.centero.ghg.client.menu.domain.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends PageMapper {



    List<Menu> findMenuByCond(MenuDto.MenuRequest request);


}
