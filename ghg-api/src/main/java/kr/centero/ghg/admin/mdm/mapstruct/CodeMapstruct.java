package kr.centero.ghg.admin.mdm.mapstruct;

import kr.centero.ghg.config.MapStructMapperConfig;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import kr.centero.ghg.admin.mdm.domain.dto.CodeDto;
import kr.centero.ghg.admin.mdm.domain.model.Code;

@Mapper(config = MapStructMapperConfig.class)
public interface CodeMapstruct {
    CodeMapstruct INSTANCE = Mappers.getMapper(CodeMapstruct.class);

    CodeDto.CodeResponse toCodeDto(Code code);

    List<CodeDto.CodeResponse> toCodeDto(List<Code> codes);
}
