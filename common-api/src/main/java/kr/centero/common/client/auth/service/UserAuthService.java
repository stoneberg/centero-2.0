package kr.centero.common.client.auth.service;

import jakarta.servlet.http.HttpServletResponse;
import kr.centero.common.client.auth.domain.dto.UserAuthDto;
import kr.centero.common.client.auth.domain.enums.ERole;
import kr.centero.common.client.auth.domain.model.CenteroUserDetails;
import kr.centero.common.client.auth.domain.model.CenteroUserToken;
import kr.centero.common.client.auth.domain.model.SignupUser;
import kr.centero.common.client.auth.mapper.RoleMapper;
import kr.centero.common.client.auth.mapper.UserAuthMapper;
import kr.centero.common.client.auth.mapper.UserRoleMapper;
import kr.centero.common.client.auth.mapper.UserTokenMapper;
import kr.centero.common.client.auth.mapstruct.UserAuthMapstruct;
import kr.centero.common.common.security.jwt.JwtTokenProvider;
import kr.centero.core.common.exception.BusinessErrorCode;
import kr.centero.core.common.exception.BusinessException;
import kr.centero.core.common.util.CookieUtil;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final UserTokenMapper userTokenMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleMapper userRoleMapper;
    private final UserAuthMapper userAuthMapper;
    private final RoleMapper roleMapper;
    private final CookieUtil cookieUtil;

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

        CenteroUserDetails userDetails = (CenteroUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .map(role -> role.replace(ROLE_PREFIX, "")).toList();

        String access = jwtTokenProvider.generateToken(username, roles);
        String refresh = jwtTokenProvider.generateRefreshToken(username);

        // 1.delete the previously issued user's tokens
        userTokenMapper.deleteByUsername(username);

        // 2.save issued tonkens
        String authorities = StringUtils.join(roles, ",");
        this.registerAccessToken(access, refresh, username, authorities);
        cookieUtil.writeAccessCookie(access, response);

        // 3.return jwt response
        return UserAuthDto.SigninResponse.builder()
                .username(username)
                .accessToken(access)
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
        SignupUser signupUser = UserAuthMapstruct.INSTANCE.toUserModel(signupRequest);

        // check if user already exists
        if (Boolean.TRUE.equals(userAuthMapper.existsUser(signupUser))) {
            throw new BusinessException(BusinessErrorCode.USER_ALREADY_EXISTS);
        }

        signupUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userAuthMapper.save(signupUser);

        // if role is empty, set default "USER" role to user
        String baseRole = StringUtils.isBlank(signupRequest.getRole()) ? ERole.CENTERO_USER.name() : signupRequest.getRole();
        Long roleId = roleMapper.findByRoleName(baseRole);
        Long userId = signupUser.getUserId();
        String username = signupUser.getUsername();
        userRoleMapper.save(userId, roleId);

        // after user registration, issue access, refresh token
        CenteroUserDetails userDetails = (CenteroUserDetails) userDetailService.loadUserByUsername(username);
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .map(role -> role.replace(ROLE_PREFIX, "")).toList();
        String access = jwtTokenProvider.generateToken(username, roles);
        String refresh = jwtTokenProvider.generateRefreshToken(username);

        // save access token
        String userRole = StringUtils.join(roles, ",");
        this.registerAccessToken(access, refresh, username, userRole);
        cookieUtil.writeAccessCookie(access, response);

        // return jwt response
        return UserAuthDto.SigninResponse.builder()
                .username(username)
                .accessToken(access)
                .roles(roles)
                .build();
    }

    /**
     * access token 등록
     *
     * @param access   access token
     * @param refresh  refresh token
     * @param username username
     * @param roles    roles (comma separated string)
     */
    public void registerAccessToken(String access, String refresh, String username, String roles) {
        CenteroUserToken accessToken = CenteroUserToken.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .username(username)
                .roles(roles)
                .issuedAt(LocalDateTime.now())
                .build();

        userTokenMapper.save(accessToken);
    }

}
