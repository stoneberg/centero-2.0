package kr.centero.ghg.auth.domain.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 사용자 로그인 권한 인증 객체
 */
@Getter
@ToString
public class CenteroUserDetails implements UserDetails {
    private static final String ROLE_PREFIX = "ROLE_";
    private final List<GrantedAuthority> authorities;
    private final long userId;
    private final String username;
    private final String password;
    private final String email;

    public CenteroUserDetails(List<UserRole> userRoles) {
        authorities = new ArrayList<>();

        userRoles.stream().map(userRole -> new SimpleGrantedAuthority(ROLE_PREFIX + userRole.getRoleName().name()))
                .forEach(authorities::add);

        this.userId = userRoles.stream()
                .map(UserRole::getUserId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("userId is null"));

        this.username = userRoles.stream()
                .map(UserRole::getUsername)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("username is null"));

        this.password = userRoles.stream()
                .map(UserRole::getPassword)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("password is null"));

        this.email = userRoles.stream()
                .map(UserRole::getEmail)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("email is null"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
