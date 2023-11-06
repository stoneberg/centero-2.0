package kr.centero.ghg.client.terms.mapper;

import kr.centero.core.common.pagination.PageMapper;
import kr.centero.ghg.client.terms.domain.dto.TermsDto;
import kr.centero.ghg.client.terms.domain.model.Terms;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TermsMapper extends PageMapper {
    List<Terms> findTermsByCond(TermsDto.TermsRequest request);

    List<Terms> findTermsByAccounts(String accountId);

    void saveTerms(TermsDto.TermsSaveRequest request);

    void revisionTerms(TermsDto.TermsRevisionRequest request);

    void modifyTerms(TermsDto.TermsModifyRequest request);
}
