package kr.centero.ghg.admin.mdm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.centero.ghg.admin.mdm.domain.dto.CodeDto;
import kr.centero.ghg.admin.mdm.domain.model.Code;

@Mapper
public interface CodeMapper {
    List<Code> findAll(CodeDto.CodeRequest params);

    void save(List<Code> code);

    List<Code> findCodeForTree();
}
