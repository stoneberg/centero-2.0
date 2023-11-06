package kr.centero.ghg.client.terms.mapstruct;


import kr.centero.ghg.client.terms.domain.dto.TermsDto;
import kr.centero.ghg.client.terms.domain.model.Terms;
import kr.centero.ghg.config.MapStructMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = MapStructMapperConfig.class)
public interface TermsMapstruct {

    TermsMapstruct INSTANCE = Mappers.getMapper(TermsMapstruct.class);

    List<TermsDto.Terms> toTermsDto(List<Terms> terms);
}
