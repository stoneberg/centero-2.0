package kr.centero.core.common.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class ApiException extends ApplicationException {
    private final String errorCode;
    private final HttpStatus httpStatus;

    public ApiException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiException(ApiErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.httpStatus = httpStatus;
    }

    public ApiException(ApiErrorCode errorCode, String message, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode.getCode();
        this.httpStatus = httpStatus;
    }

    public ApiException(String message, Throwable cause, ApiErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode.getCode();
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
