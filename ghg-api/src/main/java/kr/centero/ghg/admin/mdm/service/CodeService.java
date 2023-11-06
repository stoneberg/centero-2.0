package kr.centero.ghg.admin.mdm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.centero.ghg.admin.mdm.domain.dto.CodeDto;
import kr.centero.ghg.admin.mdm.domain.dto.CodeDto.CodeListRequest;
import kr.centero.ghg.admin.mdm.mapper.CodeMapper;
import kr.centero.ghg.admin.mdm.mapstruct.CodeMapstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

    public List<CodeDto.CodeResponse> findCodeForTree() {
        return codeMapper.findCodeForTree().stream()
                .map(CodeMapstruct.INSTANCE::toCodeDto).toList();
    }

    @Transactional
    public void saveCodeList(CodeListRequest request) {
        codeMapper.saveCodeList(request.getCodeList());
    }

}
