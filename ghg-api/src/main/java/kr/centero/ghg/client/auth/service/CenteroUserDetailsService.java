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
            throw new ApplicationException(ApplicationErrorCode.BAD_CREDENTIALS, HttpStatus.UNAUTHORIZED);
        }

        return new CenteroUserDetails(userRoles);
    }
}
