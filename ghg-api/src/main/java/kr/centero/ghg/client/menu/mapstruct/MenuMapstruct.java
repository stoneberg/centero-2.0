package kr.centero.ghg.client.menu.mapstruct;


import kr.centero.ghg.client.menu.domain.dto.MenuDto;
import kr.centero.ghg.client.menu.domain.model.Menu;
import kr.centero.ghg.client.menu.mapper.MenuMapper;
import kr.centero.ghg.config.MapStructMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = MapStructMapperConfig.class)
public interface MenuMapstruct {

    MenuMapstruct INSTANCE = Mappers.getMapper(MenuMapstruct.class);


    @Mapping(source = "c", target = "rights.create")
    @Mapping(source = "r", target = "rights.read")
    @Mapping(source = "u", target = "rights.update")
    @Mapping(source = "d", target = "rights.delete")
    MenuDto.Menu toMenuDtoMenu(Menu menu);


    List<MenuDto.Menu> toMenuDto(List<Menu> menues);
}
