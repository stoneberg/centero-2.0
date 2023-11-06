package kr.centero.common.openapi.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.common.client.auth.domain.entity.RedisTokenEntity;
import kr.centero.common.client.auth.service.RefreshTokenService;
import kr.centero.common.client.auth.service.UserTokenRedisService;
import kr.centero.common.common.security.CustomLogoutHandler;
import kr.centero.core.common.model.CenteroUserToken;
import kr.centero.core.common.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/v1/openapi")
public class OpenApiAuthController {
    public static final String REQUEST_FROM_OPENAPI = "OPENAPI";
    private final UserTokenRedisService userTokenRedisService;
    private final RefreshTokenService refreshTokenService;
    private final CustomLogoutHandler customLogoutHandler;


    /**
     * Handle GHG, NETZERO request for the saved login token info in redis
     *
     * @param accessToken access token
     * @return access token info
     */
    @GetMapping("/auth/access-token")
    public ResponseEntity<ApiResponse<RedisTokenEntity>> checkAccessToken(@RequestParam String accessToken) {
        RedisTokenEntity loginTokenInfo = userTokenRedisService.findByAccessToken(accessToken);
        log.info("[OpenApiAuthController]loginTokenInfo=====>{}", loginTokenInfo);
        return ApiResponse.of(loginTokenInfo);
    }

    /**
     * Handle GHG, NETZERO request for refresh
     * issue new access token
     *
     * @param centeroUserToken current user token
     * @param response         http response
     * @return new access token
     */
    @PostMapping("/auth/refresh")
    public ResponseEntity<ApiResponse<String>> refresh(@RequestBody CenteroUserToken centeroUserToken, HttpServletResponse response) {
        String newAccessToken = refreshTokenService.issueNewUserToken(centeroUserToken, response, REQUEST_FROM_OPENAPI);
        return ApiResponse.of(newAccessToken);
    }


    /**
     * Handle GHG, NETZERO request for logout
     *
     * @param request  http request
     * @param response http response
     * @return http status
     */
    @GetMapping("/signout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpServletRequest request, HttpServletResponse response) {
        customLogoutHandler.deleteJwtLoginSession(request, response);
        return ApiResponse.ok("logout success");
    }

}
