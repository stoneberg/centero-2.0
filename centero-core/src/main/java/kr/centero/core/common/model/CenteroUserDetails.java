package kr.centero.core.common.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 사용자 로그인 권한 인증 객체 (SpringSecurity)
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

    public CenteroUserDetails(List<CenteroUserRole> centeroUserRoles) {
        authorities = new ArrayList<>();

        centeroUserRoles.stream().map(centeroUserRole -> new SimpleGrantedAuthority(ROLE_PREFIX + centeroUserRole.getRoleName().name()))
                .forEach(authorities::add);

        this.userId = centeroUserRoles.stream()
                .map(CenteroUserRole::getUserId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("userId is null"));

        this.username = centeroUserRoles.stream()
                .map(CenteroUserRole::getUsername)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("username is null"));

        this.password = centeroUserRoles.stream()
                .map(CenteroUserRole::getPassword)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("password is null"));

        this.email = centeroUserRoles.stream()
                .map(CenteroUserRole::getEmail)
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
