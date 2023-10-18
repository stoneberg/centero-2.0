package kr.centero.ghg.admin.mdm.service;

import kr.centero.ghg.admin.mdm.domain.dto.CodeDto;
import kr.centero.ghg.admin.mdm.domain.model.Code;
import kr.centero.ghg.admin.mdm.mapper.CodeMapper;
import kr.centero.ghg.admin.mdm.mapstruct.CodeMapstruct;
import kr.centero.ghg.common.util.UserUtils;
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
        String authenticatedUserName = UserUtils.getAuthenticatedUserName();
        log.info("authenticatedUserName: {}", authenticatedUserName);
        return codeMapper.findAll(codeRequest).stream()
                .map(CodeMapstruct.INSTANCE::toCodeDto).toList();
    }

}
