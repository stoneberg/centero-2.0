package kr.centero.ghg.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.centero.core.common.exception.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.web.util.UrlPathHelper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * AuthenticationEntryPoint:
 * Protect resources from unauthorized user who try to access without authentication.
 */
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {
    private final HttpRequestEndpointChecker endpointChecker;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String path = new UrlPathHelper().getPathWithinApplication(request);
        if (endpointChecker.isNotExistingEndpoint(request)) {
            ErrorResponse errorResponse = this.getErrorResponse(path, HttpStatus.NOT_FOUND);
            this.send404Error(response, errorResponse);
        } else {
            // super.commence(request, response, authException);
            ErrorResponse errorResponse = this.getErrorResponse(path, HttpStatus.UNAUTHORIZED);
            this.send401Error(response, errorResponse);
        }
    }

    private ErrorResponse getErrorResponse(String path, HttpStatus httpStatus) {
        return ErrorResponse.builder()
                .status(httpStatus.value())
                .code(httpStatus.name())
                .message(httpStatus.getReasonPhrase())
                .path("uri=" + path)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    private void send401Error(HttpServletResponse response, ErrorResponse errorResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(Objects.requireNonNull(objectMapper.writeValueAsString(errorResponse)));
    }

    private void send404Error(HttpServletResponse response, ErrorResponse errorResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.getWriter().write(Objects.requireNonNull(objectMapper.writeValueAsString(errorResponse)));
    }

}
