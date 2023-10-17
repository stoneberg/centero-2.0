package kr.centero.common.common.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class ApplicationException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus httpStatus;

    public ApplicationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApplicationException(ApplicationErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.httpStatus = httpStatus;
    }

    public ApplicationException(ApplicationErrorCode errorCode, String message, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode.getCode();
        this.httpStatus = httpStatus;
    }

}
