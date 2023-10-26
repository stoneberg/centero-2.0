package kr.centero.common.config;

import kr.centero.common.common.security.*;
import kr.centero.common.common.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String COMMON_AUTH_ENTRY_POINTS = "/api/common/v1/auth/**";
    private static final String COMMON_LOGOUT_URL = "/api/common/v1/user/signout";
    private final HttpRequestEndpointChecker httpRequestEndpointChecker;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomLogoutHandler customLogoutHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().
                requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                .requestMatchers(new AntPathRequestMatcher( "/v3/api-docs/**"))
                .requestMatchers(new AntPathRequestMatcher( "/swagger-ui/**"))
                .requestMatchers(new AntPathRequestMatcher( "/swagger-resources/**"));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {

        http.cors(withDefaults());

        http.httpBasic(AbstractHttpConfigurer::disable);

        http.csrf(AbstractHttpConfigurer::disable);

        http.headers(headersConfigurer ->
                headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        http.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(new MvcRequestMatcher(introspector, COMMON_AUTH_ENTRY_POINTS)).permitAll()
                        .requestMatchers(new MvcRequestMatcher(introspector, "/api/common/v1/members/**")).permitAll()
//                        .requestMatchers(new MvcRequestMatcher(introspector, "/api/common/v1/methodologies/find-all")).hasRole(ERole.ADMIN.name())
//                        .requestMatchers(new MvcRequestMatcher(introspector, "/api/common/v1/methodologies/find-one/**")).hasRole(ERole.USER.name())
//                        .requestMatchers(new MvcRequestMatcher(introspector, "/api/common/v1/methodologies/find-all"))
//                            .hasAnyRole(ERole.CENTERO_ADMIN.name(), ERole.NETZERO_ADMIN.name())
//                        .requestMatchers(new MvcRequestMatcher(introspector, "/api/common/v1/methodologies/find-one/**"))
//                            .hasAnyRole(ERole.CENTERO_USER.name(), ERole..NETZERO_USER.name())
                        .anyRequest().authenticated()
        );

        http.exceptionHandling(exceptionHandlingConfigurer ->
                exceptionHandlingConfigurer
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint(httpRequestEndpointChecker))
                        .accessDeniedHandler(new CustomAccessDeniedHandler(httpRequestEndpointChecker)));

        http.sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS));

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.logout(logoutConfig -> logoutConfig
                .logoutUrl(COMMON_LOGOUT_URL)
                .addLogoutHandler(customLogoutHandler)
                .logoutSuccessHandler(customLogoutSuccessHandler));

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://127.0.0.1:3000"));
        configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "Accept-Language"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
