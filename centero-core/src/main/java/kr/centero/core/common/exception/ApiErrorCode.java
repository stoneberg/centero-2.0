package kr.centero.core.common.exception;

import lombok.Getter;

@Getter
public enum ApiErrorCode {

    /**
     * Application(System) Error Codes, This error codes are not related to business logic.
     */
    TOKEN_NOT_VALID("SYS_ERR_9010", "토큰이 유효하지 않습니다."),
    TOKEN_EXPIRED("SYS_ERR_9011", "토큰이 만료되었습니다."),
    REFRESH_TOKEN_EXPIRED("SYS_ERR_9012", "토큰이 만료되었습니다. 다시 로그인 해주세요."),
    BAD_CREDENTIALS("SYS_ERR_9020", "인증정보가 일치하지 않습니다."),
    UNAUTHORIZED_REQUEST("SYS_ERR_9021", "인증되지 않은 사용자입니다."),
    FORBIDDEN_ACCESS("SYS_ERR_9022", "접근 권한이 없습니다."),
    LOGIN_FAILURE("SYS_ERR_9023", "로그인에 실패하였습니다."),
    LOGIN_FAILURE_EXCEED_MAX_COUNT("SYS_ERR_9024", "로그인 실패 횟수가 초과하였습니다."),
    ACCOUNT_INACTIVE("SYS_ERR_9025", "비활성화된 계정입니다."),
    ACCOUNT_LOCKED("SYS_ERR_9026", "잠긴 계정입니다."),
    IP_BLACKLISTED("SYS_ERR_9027", "IP 블랙리스트에 등록된 IP입니다."),
    BAD_REQUEST("SYS_ERR_9030", "잘못된 요청입니다."),
    NOT_FOUND("SYS_ERR_9031", "요청한 리소스가 존재하지 않습니다."),
    METHOD_NOT_ALLOWED("SYS_ERR_9032", "지원하지 않는 HTTP Method 입니다."),
    PASSWORD_ENCODE_FAILURE("SYS_ERR_9033", "비밀번호 암호화에 실패하였습니다."),
    TIMEZONE_CONVERSION_FAILURE("SYS_ERR_9034", "시간 변환에 실패하였습니다."),
    INTERNAL_SERVER_ERROR("SYS_ERR_9999", "서버에 오류가 발생하였습니다.");

    private final String code;
    private final String message;

    ApiErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
