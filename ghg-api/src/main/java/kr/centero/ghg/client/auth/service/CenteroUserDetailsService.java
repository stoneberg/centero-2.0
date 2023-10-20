package kr.centero.ghg.client.auth.service;

import kr.centero.core.common.exception.ApplicationErrorCode;
import kr.centero.core.common.exception.ApplicationException;
import kr.centero.ghg.client.auth.domain.model.CenteroUserDetails;
import kr.centero.ghg.client.auth.domain.model.UserRole;
import kr.centero.ghg.client.auth.mapper.UserAuthMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CenteroUserDetailsService implements UserDetailsService {
    private final UserAuthMapper userAuthMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserRole> userRoles = userAuthMapper.findUserByUsername(username);

        if (CollectionUtils.isEmpty(userRoles)) {
            // access token 만료시 Unauthorized[401] 처리하여 frontend에서 refresh 호출이 되도록 유도
            // refresh token 만료시 Forbidden[403] 처리하여 frontend에서 refresh 순환 재요청 처리 방지
            throw new ApplicationException(ApplicationErrorCode.TOKEN_EXPIRED, HttpStatus.FORBIDDEN);
        }

        return new CenteroUserDetails(userRoles);
    }
}
