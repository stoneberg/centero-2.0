package kr.centero.core.common.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolationException;
import kr.centero.core.common.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageUtil messageUtil;

    /**
     * Handle Custom BusinessException
     *
     * @param ex      custom business exception
     * @param request web request
     * @return error response
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex, WebRequest request) {
        log.error("[Exception]BusinessException: {}", ExceptionUtils.getMessage(ex));
        log.error("[Exception]BusinessException: {}", ExceptionUtils.getStackTrace(ex));
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(ex.getErrorCode())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now().toString())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle Custom ApplicationException
     *
     * @param ex      custom application exception
     * @param request web request
     * @return error response
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(ApiException ex, WebRequest request) {
        log.error("[Exception]ApplicationException: {}", ExceptionUtils.getMessage(ex));
        log.error("[Exception]ApplicationException: {}", ExceptionUtils.getStackTrace(ex));
        HttpStatus httpStatus = ex.getHttpStatus();
        String customMessage = this.handleLoginError(ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(httpStatus.value())
                .code(ex.getErrorCode())
                .message(customMessage)
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now().toString())
                .build();

        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    private String handleLoginError(ApiException ex) {
        String customMessage;
        if (ex.getErrorCode().equals(ApiErrorCode.LOGIN_FAILURE.getCode())) {
            customMessage = "Failed the " + ex.getMessage() + "th attempt to log in";
        } else {
            customMessage = ex.getMessage();
        }
        return customMessage;
    }

    // @TODO: handle other exceptions
//    HttpRequestMethodNotSupportedException .class,
//    HttpMediaTypeNotSupportedException .class,
//    HttpMediaTypeNotAcceptableException .class,
//    MissingPathVariableException .class,
//    MissingServletRequestParameterException .class,
//    MissingServletRequestPartException .class,
//    ServletRequestBindingException .class,
//    MethodArgumentNotValidException.class,
//    NoHandlerFoundException .class,
//    AsyncRequestTimeoutException .class,
//    ErrorResponseException .class,
//    ConversionNotSupportedException .class,
//    TypeMismatchException.class,
//    HttpMessageNotReadableException .class,
//    HttpMessageNotWritableException .class,
//    BindException.class

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        log.error("[Exception]MethodArgumentNotValidException: {}", ExceptionUtils.getMessage(ex));
        log.error("[Exception]MethodArgumentNotValidException: {}", ExceptionUtils.getStackTrace(ex));
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> { // Accept-Language 값에 따라 자동으로 메시지를 선택한다.
                    String message = fieldError.getDefaultMessage();
                    return message != null ? message : "Unknown error";
                })
                .findFirst()
                .orElse(ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(ApiErrorCode.BAD_REQUEST.getCode())
                .message(errorMessage)
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now().toString())
                .build();

//        Locale locale = getLocaleFromHeader(request);
//        String message = this.getMessage("database.error", new Object[]{LocalDateTime.now().toString(), "SK Corp Ltd."}, locale);
//        log.info("[ZET]message: {}", message);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException ex, WebRequest request) {
        log.error("[Exception]BindException: {}", ExceptionUtils.getMessage(ex));
        log.error("[Exception]BindException: {}", ExceptionUtils.getStackTrace(ex));
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> { // Accept-Language 값에 따라 자동으로 메시지를 선택한다.
                    String message = fieldError.getDefaultMessage();
                    return message != null ? message : "Unknown error";
                })
                .findFirst()
                .orElse(ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(ApiErrorCode.BAD_REQUEST.getCode())
                .message(errorMessage)
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now().toString())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        log.error("[Exception]HttpRequestMethodNotSupportedException: {}", ExceptionUtils.getMessage(ex));
        log.error("[Exception]HttpRequestMethodNotSupportedException: {}", ExceptionUtils.getStackTrace(ex));
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(ApiErrorCode.BAD_REQUEST.getCode())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now().toString())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        log.error("[Exception]ConstraintViolationException: {}", ExceptionUtils.getMessage(ex));
        log.error("[Exception]ConstraintViolationException: {}", ExceptionUtils.getStackTrace(ex));
        String errorMsg = ex.getConstraintViolations().stream()
                .map(violation -> violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
                        + violation.getMessage()).findFirst().orElse(ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(ApiErrorCode.BAD_REQUEST.getCode())
                .message(errorMsg)
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now().toString())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SignatureException.class, MalformedJwtException.class, UnsupportedJwtException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleSignatureException(Exception ex, WebRequest request) {
        log.error("[Exception]JwtException: {}", ExceptionUtils.getMessage(ex));
        log.error("[Exception]JwtException: {}", ExceptionUtils.getStackTrace(ex));
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .code(ApiErrorCode.TOKEN_NOT_VALID.getCode())
                .message(ApiErrorCode.TOKEN_NOT_VALID.getMessage())
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now().toString())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        log.error("[Exception]ExpiredJwtException: {}", ExceptionUtils.getMessage(ex));
        log.error("[Exception]ExpiredJwtException: {}", ExceptionUtils.getStackTrace(ex));
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .code(ApiErrorCode.TOKEN_EXPIRED.getCode())
                .message(ApiErrorCode.TOKEN_EXPIRED.getMessage())
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now().toString())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        log.error("[Exception]AccessDeniedException: {}", ExceptionUtils.getMessage(ex));
        log.error("[Exception]AccessDeniedException: {}", ExceptionUtils.getStackTrace(ex));
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .code(ApiErrorCode.FORBIDDEN_ACCESS.getCode())
                .message(ApiErrorCode.FORBIDDEN_ACCESS.getMessage())
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now().toString())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * Handle all other exceptions
     *
     * @param ex      exception
     * @param request web request
     * @return error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        log.error("[Exception]message: {}", ExceptionUtils.getMessage(ex));
        log.error("[Exception]message: {}", ExceptionUtils.getStackTrace(ex));
        String message = messageUtil.getMessage("server.error");

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code(ApiErrorCode.BAD_REQUEST.getCode())
                .message(message)
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now().toString())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
