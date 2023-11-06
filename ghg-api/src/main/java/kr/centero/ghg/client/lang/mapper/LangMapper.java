package kr.centero.ghg.client.lang.mapper;

import kr.centero.core.common.pagination.PageMapper;
import kr.centero.ghg.client.lang.domain.model.Lang;
import org.apache.ibatis.annotations.Mapper;
import kr.centero.ghg.client.lang.domain.dto.LangDto;
import java.util.List;

@Mapper
public interface LangMapper extends PageMapper {


    List<Lang> findLangByCond(LangDto.LangRequest request);


}
