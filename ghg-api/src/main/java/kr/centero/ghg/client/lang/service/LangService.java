package kr.centero.ghg.client.lang.service;

import kr.centero.ghg.client.lang.domain.dto.LangDto;
import kr.centero.ghg.client.lang.domain.model.Lang;
import kr.centero.ghg.client.lang.mapper.LangMapper;
import kr.centero.ghg.client.lang.mapstruct.LangMapstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LangService {

    private final LangMapper langMapper;

    /**
     * 특정 locale의 언어팩 조회
     * @param request
     * @return
     */
    public LangDto.LangResponse findLangs(LangDto.LangRequest request)
    {
        List<Lang> langList = langMapper.findLangByCond(request);

        List<LangDto.Lang> langDtoList = LangMapstruct.INSTANCE.toLangDto(langList);

        LangDto.LangResponse response = new LangDto.LangResponse();
        response.setLocale(request.getLocale());
        response.setLastDt("yyyyMMddhhmmss");
        response.setResources(langDtoList);

        return response;
    }


}
