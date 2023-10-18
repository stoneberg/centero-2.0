package kr.centero.core.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.core.common.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * LogoutSuccessHandler:
 * Send a response to the client after successful logout.
 * when the user logs out.
 */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        ApiResponse logoutResponse = new ApiResponse();
        logoutResponse.setStatus(HttpStatus.OK.value());
        logoutResponse.setCode(HttpStatus.OK.name());
        logoutResponse.setMessage("LOGOUT SUCCESS");
        logoutResponse.setTimestamp(LocalDateTime.now().toString());
        logoutResponse.setData(null);
        this.sendLogoutResponse(response, logoutResponse);
        // it's not necessary to call SecurityContextHolder.clearContext() because it's already called by LogoutFilter
    }

    private void sendLogoutResponse(HttpServletResponse response, ApiResponse logoutResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(Objects.requireNonNull(objectMapper.writeValueAsString(logoutResponse)));
    }
}
