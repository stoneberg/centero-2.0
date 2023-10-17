package kr.centero.common.auth.service;

import jakarta.servlet.http.HttpServletResponse;
import kr.centero.common.auth.domain.dto.UserAuthDto;
import kr.centero.common.auth.domain.enums.ERole;
import kr.centero.common.auth.domain.model.CenteroUserDetails;
import kr.centero.common.auth.domain.model.UserToken;
import kr.centero.common.auth.mapper.RoleMapper;
import kr.centero.common.auth.mapper.UserRoleMapper;
import kr.centero.common.auth.mapper.UserTokenMapper;
import kr.centero.common.auth.mapstruct.UserAuthMapstruct;
import kr.centero.common.common.exception.BusinessErrorCode;
import kr.centero.common.common.exception.BusinessException;
import kr.centero.common.common.jwt.JwtTokenProvider;
import kr.centero.common.common.util.CookieUtil;
import kr.centero.common.user.domain.model.User;
import kr.centero.common.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserAuthService {
    private static final String ROLE_PREFIX = "ROLE_";
    private final AuthenticationManager authenticationManager;
    private final CenteroUserDetailsService userDetailService;
    private final UserTokenMapper userTokenMapper;
    private final UserRoleMapper userRoleMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 로그인: access, refresh 토큰 발급
     *
     * @param signinRequest 로그인 사용자 정보
     * @param response      HttpServletResponse
     * @return jwt response
     */
    @Transactional
    public UserAuthDto.SigninResponse issueUserToken(UserAuthDto.SigninRequest signinRequest, HttpServletResponse response) {
        String username = signinRequest.getUsername();
        String password = signinRequest.getPassword();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

/*        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception ex) {
            throw new ApplicationException(ApplicationErrorCode.BAD_CREDENTIALS, HttpStatus.UNAUTHORIZED);
        }*/

        CenteroUserDetails userDetails = (CenteroUserDetails) authentication.getPrincipal();
        String access = jwtTokenProvider.generateToken(userDetails);
        String refresh = jwtTokenProvider.generateRefreshToken(userDetails);

        // ROLE_ADMIN, ROLE_USER 에서 ROLE_ 제거
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .map(role -> role.replace(ROLE_PREFIX, "")).toList();

        // 1.delete the previously issued user's tokens
        userTokenMapper.deleteByUsername(username);

        // 2.save accessToken
        this.registerAccessToken(access, username);

        // 3.create refresh token cookie
        CookieUtil.createCookie(CookieUtil.REFRESH_TOKEN_COOKIE, refresh, "1d", response);

        // 4.return jwt response
        return UserAuthDto.SigninResponse.builder()
                .username(username)
                .accessToken(access)
                .refreshToken(refresh)
                .roles(roles)
                .build();
    }

    /**
     * refresh token 처리 -> access 토큰 재발급
     *
     * @param refreshToken refresh token
     * @return new access token
     */
    @Transactional
    public UserAuthDto.SigninResponse issueNewAccessToken(String refreshToken, HttpServletResponse httpServletResponse) {
        String username = jwtTokenProvider.extractUsername(refreshToken);
        CenteroUserDetails userDetails = (CenteroUserDetails) userDetailService.loadUserByUsername(username);
        String newAccessToken = jwtTokenProvider.generateToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .map(role -> role.replace(ROLE_PREFIX, "")).toList();

        // 1.delete the previously issued accessToken
        userTokenMapper.deleteByUsername(username);

        // 2.save new accessToken
        this.registerAccessToken(newAccessToken, username);

        // 3.create refresh token cookie
        CookieUtil.createCookie(CookieUtil.REFRESH_TOKEN_COOKIE, refreshToken, "1d", httpServletResponse);

        // 4.return jwt response
        return UserAuthDto.SigninResponse.builder()
                .username(username)
                .accessToken(newAccessToken)
                .refreshToken(refreshToken) // refresh token 재사용: refresh token 만료 시 재로그인 필요
                .roles(roles)
                .build();
    }

    /**
     * 사용자 등록: access, refresh 토큰 발급
     *
     * @param signupRequest 등록 사용자 정보
     * @param response      HttpServletResponse
     * @return jwt response
     */
    @Transactional
    public UserAuthDto.SigninResponse registerUser(UserAuthDto.SignupRequest signupRequest, HttpServletResponse response) {
        User user = UserAuthMapstruct.INSTANCE.toUserModel(signupRequest);

        // 등록된 사용자인지 확인
        if (Boolean.TRUE.equals(userMapper.existsUser(user))) {
            throw new BusinessException(BusinessErrorCode.USER_ALREADY_EXISTS);
        }

        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userMapper.save(user);

        // no role set default role to user
        String role = StringUtils.isBlank(signupRequest.getRole()) ? ERole.USER.name() : signupRequest.getRole();
        Long roleId = roleMapper.findByRoleName(role);
        Long userId = user.getUserId();
        String username = user.getUsername();
        userRoleMapper.save(userId, roleId);

        // after user registration, issue access, refresh token
        CenteroUserDetails userDetails = (CenteroUserDetails) userDetailService.loadUserByUsername(username);
        String access = jwtTokenProvider.generateToken(userDetails);
        String refresh = jwtTokenProvider.generateRefreshToken(userDetails);

        // 1.save access token
        this.registerAccessToken(access, username);

        // 2.create refresh token cookie
        CookieUtil.createCookie(CookieUtil.REFRESH_TOKEN_COOKIE, refresh, "1d", response);

        // 3.return jwt response
        return UserAuthDto.SigninResponse.builder()
                .username(username)
                .accessToken(access)
                .refreshToken(refresh)
                .roles(List.of(role))
                .build();
    }

    /**
     * access token 등록
     *
     * @param access   access token
     * @param username username
     */
    public void registerAccessToken(String access, String username) {
        UserToken accessToken = UserToken.builder()
                .token(access)
                .username(username)
                .issuedAt(LocalDateTime.now())
                .build();

        userTokenMapper.save(accessToken);
    }

}
