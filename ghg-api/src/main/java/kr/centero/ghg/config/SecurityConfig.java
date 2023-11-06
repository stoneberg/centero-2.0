package kr.centero.ghg.config;

import kr.centero.core.common.enums.roles.ERole;
import kr.centero.ghg.common.security.CustomAccessDeniedHandler;
import kr.centero.ghg.common.security.CustomAuthenticationEntryPoint;
import kr.centero.ghg.common.security.HttpRequestEndpointChecker;
import kr.centero.ghg.common.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
        private static final String GHG_API_ENTRY_POINTS = "/api/ghg/v1/**";
        private final HttpRequestEndpointChecker httpRequestEndpointChecker;
        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
                return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**"))
                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**"))
                                .requestMatchers(new AntPathRequestMatcher("/swagger-resources/**"));
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector)
                        throws Exception {

                http.cors(withDefaults());

                http.httpBasic(AbstractHttpConfigurer::disable);

                http.csrf(AbstractHttpConfigurer::disable);

                http.headers(
                                headersConfigurer -> headersConfigurer
                                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

                http.authorizeHttpRequests(auth -> auth
                                .requestMatchers(new MvcRequestMatcher(introspector, "/api/**"))
                                .permitAll()
                                .requestMatchers(new MvcRequestMatcher(introspector, GHG_API_ENTRY_POINTS))
                                .hasAnyRole(ERole.GHG_ADMIN.name(), ERole.GHG_USER.name())
                                .anyRequest().authenticated());

                http.exceptionHandling(exceptionHandlingConfigurer -> exceptionHandlingConfigurer
                                .authenticationEntryPoint(
                                                new CustomAuthenticationEntryPoint(httpRequestEndpointChecker))
                                .accessDeniedHandler(new CustomAccessDeniedHandler(httpRequestEndpointChecker)));

                http.sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS));

                http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        @Bean
        public AuthenticationManager authManager(HttpSecurity http) throws Exception {
                AuthenticationManagerBuilder authenticationManagerBuilder = http
                                .getSharedObject(AuthenticationManagerBuilder.class);
                authenticationManagerBuilder.getDefaultUserDetailsService();
                return authenticationManagerBuilder.build();
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(
                        Arrays.asList("http://ghg.centero.kr:3000", "http://common-api.centero.kr:8080", "http://ghg.centero.kr:6006",
                                "http://netzero-api.centero.co.kr:8082"));
                configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
                configuration.setAllowedHeaders(
                                List.of("Authorization", "Cache-Control", "Content-Type", "Accept-Language"));
                configuration.setAllowCredentials(true);
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }
}
