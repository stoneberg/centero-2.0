package kr.centero.ghg.client.terms.service;

import kr.centero.core.common.payload.ApiResponse;
import kr.centero.ghg.client.terms.domain.dto.TermsDto;
import kr.centero.ghg.client.terms.domain.model.Terms;
import kr.centero.ghg.client.terms.mapper.TermsMapper;
import kr.centero.ghg.client.terms.mapstruct.TermsMapstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class TermsService {

    private final TermsMapper termsMapper;

    /**
     * 약관 조회
     */
    public TermsDto.TermsResponse findTermsByCond(TermsDto.TermsRequest request)
    {
        List<Terms> termsList = termsMapper.findTermsByCond(request);

        List<TermsDto.Terms> termsDto = TermsMapstruct.INSTANCE.toTermsDto(termsList);

        for (TermsDto.Terms terms : termsDto) {
            List<Integer> version = new ArrayList<>();
            String[] versions = terms.getVersionList().split(",");
            for (String ver : versions) {
                version.add(Integer.parseInt(ver));
            }
            terms.setList(version);
        }
        TermsDto.TermsResponse response = new TermsDto.TermsResponse();
        response.setTerms(termsDto);

        return response;
    }

    /**
     * 사용자 최신 약관 동의 여부 조회
     */
    public TermsDto.TermsResponse findTermsByAccounts(String accountId)
    {
        List<Terms> termsList = termsMapper.findTermsByAccounts(accountId);

        List<TermsDto.Terms> termsDto = TermsMapstruct.INSTANCE.toTermsDto(termsList);

        TermsDto.TermsResponse response = new TermsDto.TermsResponse();
        response.setTerms(termsDto);

        return response;
    }

    /**
     * 약관 생성
     */
    public ResponseEntity<ApiResponse<Object>> saveTerms(TermsDto.TermsSaveRequest request) {
        termsMapper.saveTerms(request);

        return ApiResponse.ok();
    }

    /**
     * 약관 개정
     */
    public ResponseEntity<ApiResponse<Object>> revisionTerms(TermsDto.TermsRevisionRequest request) {
        LocalDateTime openedAt = LocalDateTime.parse(request.getOpenedAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if(openedAt.isAfter(LocalDateTime.now())) {
            termsMapper.revisionTerms(request);
        } else {
            return ApiResponse.bad();
        }

        return ApiResponse.ok();
    }
    
    /**
     * 약관 수정
     */
    public ResponseEntity<ApiResponse<Object>> modifyTerms(TermsDto.TermsModifyRequest request) {
        termsMapper.modifyTerms(request);

        return ApiResponse.ok();
    }
}
