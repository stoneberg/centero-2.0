package kr.centero.ghg.admin.mdm.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.centero.ghg.admin.mdm.domain.model.Code;
import kr.centero.ghg.admin.mdm.domain.dto.CodeDto;

import java.util.List;

@Mapper
public interface CodeMapper {
    List<Code> findAll(CodeDto.CodeRequest params);

    void save(List<Code> code);
}
