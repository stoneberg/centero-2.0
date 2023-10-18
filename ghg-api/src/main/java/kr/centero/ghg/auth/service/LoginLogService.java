package kr.centero.ghg.auth.service;

import kr.centero.ghg.auth.domain.model.LoginLog;
import kr.centero.ghg.auth.mapper.LoginLogMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginLogService {
    private final LoginLogMapper loginLogMapper;

    // 로그인 로그를 추가
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void insertLoginLog(LoginLog loginLog) {
        loginLogMapper.insertLoginLog(loginLog);
    }

    // 로그인 실패 횟수를 조회
    public Integer findRecentFailureCount(@Param("username") String username) {
        return loginLogMapper.findRecentFailureCount(username);
    }

}
