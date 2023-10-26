package kr.centero.common.client.auth.service;

import jakarta.servlet.http.HttpServletResponse;
import kr.centero.common.client.auth.domain.entity.CenteroUserTokenEntity;
import kr.centero.common.client.auth.domain.model.CenteroUserToken;
import kr.centero.common.client.auth.mapper.UserTokenMapper;
import kr.centero.common.common.security.jwt.JwtTokenProvider;
import kr.centero.core.common.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RefreshTokenService {
    private final UserTokenRedisService userTokenRedisService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserTokenMapper userTokenMapper;
    private final CookieUtil cookieUtil;


    /**
     * refresh token 처리 -> access 토큰 재발급
     *
     * @param refreshToken refresh token
     */
    @Transactional
    public void issueNewUserToken(String refreshToken, String username, List<String> roles, HttpServletResponse response) {
        log.info("[REFRESH TOKEN]=======================>");
        log.info("refreshToken==========================>{}", refreshToken);
        log.info("username==============================>{}", username);
        log.info("roles=================================>{}", roles);
        // 1.update access token and create new cookie
        String newAccessToken = jwtTokenProvider.generateToken(username, roles);
        this.updateAccessToken(newAccessToken, refreshToken, username);
        String authorities = StringUtils.join(roles, ",");
        this.updateAccessTokenRedis(newAccessToken, refreshToken, username, authorities);

        cookieUtil.writeAccessCookie(newAccessToken, response);
    }

    /**
     * access token 갱신
     *
     * @param access   access token
     * @param username username
     */
    public void updateAccessToken(String access, String refresh, String username) {
        log.info("2.[ZET]updateAccessToken===================================>");
        CenteroUserToken accessToken = CenteroUserToken.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .username(username)
                .issuedAt(LocalDateTime.now())
                .build();

        userTokenMapper.update(accessToken);
    }

    /**
     * access token 갱신
     *
     * @param access  access token
     * @param refresh refresh token
     * @param username username
     * @param authorities authorities
     */
    public void updateAccessTokenRedis(String access, String refresh, String username, String authorities) {
        log.info("2.[ZET]updateAccessTokenRedis===============================>");
        CenteroUserTokenEntity accessToken = CenteroUserTokenEntity.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .username(username)
                .roles(authorities)
                .issuedAt(LocalDateTime.now())
                .build();

        userTokenRedisService.update(accessToken);
    }

}
