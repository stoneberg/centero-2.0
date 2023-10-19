package kr.centero.ghg.admin.mdm.service;

import kr.centero.ghg.admin.mdm.domain.dto.CodeDto;
import kr.centero.ghg.admin.mdm.mapper.CodeMapper;
import kr.centero.ghg.admin.mdm.mapstruct.CodeMapstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CodeService {

    private final CodeMapper codeMapper;

    public List<CodeDto.CodeResponse> findAll(CodeDto.CodeRequest codeRequest) {
        return codeMapper.findAll(codeRequest).stream()
                .map(CodeMapstruct.INSTANCE::toCodeDto).toList();
    }

}
