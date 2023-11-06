package kr.centero.common.client.auth.service;

import kr.centero.common.client.auth.mapper.UserAuthMapper;
import kr.centero.core.common.exception.ApiErrorCode;
import kr.centero.core.common.exception.ApiException;
import kr.centero.core.common.model.CenteroUserDetails;
import kr.centero.core.common.model.CenteroUserRole;
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
        List<CenteroUserRole> centeroUserRoles = userAuthMapper.findUserByUsername(username);

        if (CollectionUtils.isEmpty(centeroUserRoles)) {
            throw new ApiException(ApiErrorCode.TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED);
        }

        return new CenteroUserDetails(centeroUserRoles);
    }
}
