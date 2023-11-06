package kr.centero.common.client.auth.service;

import jakarta.servlet.http.HttpServletResponse;
import kr.centero.common.client.auth.domain.entity.RedisTokenEntity;
import kr.centero.common.client.auth.mapper.UserTokenMapper;
import kr.centero.core.common.jwt.JwtTokenProvider;
import kr.centero.core.common.model.CenteroUserToken;
import kr.centero.core.common.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static kr.centero.common.openapi.web.OpenApiAuthController.REQUEST_FROM_OPENAPI;


/**
 * refresh token service
 * handle refresh token issue process
 */
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
     * issue new access token with refresh token
     *
     * @param oldUserToken old user token properties
     * @param response     HttpServletResponse
     * @return new access token
     */
    @Transactional
    public String issueNewUserToken(CenteroUserToken oldUserToken, HttpServletResponse response, String from) {
        String username = oldUserToken.getUsername();
        String refreshToken = oldUserToken.getRefreshToken();
        String authorities = oldUserToken.getRoles();
        String oldAccessToken = oldUserToken.getAccessToken();

        // 1.update access token and create new cookie
        List<String> roles = List.of(authorities.split(","));
        String newAccessToken = jwtTokenProvider.generateToken(username, roles);
        this.updateAccessTokenInRedis(oldAccessToken, newAccessToken, refreshToken, username, authorities);

        // 2.create new access token cookie only common-api login
        if (!StringUtils.equals(from, REQUEST_FROM_OPENAPI)) {
            cookieUtil.writeAccessCookie(newAccessToken, response);
        }

        return newAccessToken;
    }

    /**
     * update access token in redis (delete and save)
     *
     * @param oldAccess   old access token
     * @param newAccess   new access token
     * @param refresh     refresh token
     * @param username    username
     * @param authorities authorities
     */
    @Transactional
    public void updateAccessTokenInRedis(String oldAccess, String newAccess, String refresh, String username, String authorities) {
        // delete old access token, clean up redis
        userTokenRedisService.deleteByAccessToken(oldAccess);

        // save new access token
        RedisTokenEntity accessToken = RedisTokenEntity.builder()
                .accessToken(newAccess)
                .refreshToken(refresh)
                .username(username)
                .roles(authorities)
                .issuedAt(LocalDateTime.now())
                .build();

        userTokenRedisService.save(accessToken);
    }

    /**
     * update access token in db
     *
     * @param access   access token
     * @param refresh  refresh token
     * @param username username
     */
    @Transactional
    public void updateAccessTokenInDB(String access, String refresh, String username) {
        CenteroUserToken accessToken = CenteroUserToken.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .username(username)
                .issuedAt(LocalDateTime.now())
                .build();

        userTokenMapper.update(accessToken);
    }

}
