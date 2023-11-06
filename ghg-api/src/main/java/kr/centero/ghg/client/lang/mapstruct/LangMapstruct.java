package kr.centero.ghg.client.lang.mapstruct;


import kr.centero.ghg.client.lang.domain.dto.LangDto;
import kr.centero.ghg.client.lang.domain.model.Lang;
import kr.centero.ghg.config.MapStructMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = MapStructMapperConfig.class)
public interface LangMapstruct {

    LangMapstruct INSTANCE = Mappers.getMapper(LangMapstruct.class);


    LangDto.Lang toMenuDtoLang(Lang menu);


    List<LangDto.Lang> toLangDto(List<Lang> langs);
}
