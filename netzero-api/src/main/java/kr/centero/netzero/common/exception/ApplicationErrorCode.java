package kr.centero.netzero.common.exception;

import lombok.Getter;

@Getter
public enum ApplicationErrorCode {

    /**
     * 비즈니스 로직과 관련없는 시스템 에러 코드 설정
     */
    TOKEN_NOT_VALID("GLB_ERR_9000", "토큰이 유효하지 않습니다."),
    TOKEN_EXPIRED("GLB_ERR_9010", "토큰이 만료되었습니다."),
    BAD_CREDENTIALS("GLB_ERR_9020", "인증정보가 일치하지 않습니다."),
    BAD_REQUEST("GLB_ERR_9030", "잘못된 요청입니다."),
    UNAUTHORIZED_REQUEST("GLB_ERR_9040", "인증되지 않은 사용자입니다."),
    FORBIDDEN_ACCESS("GLB_ERR_9050", "접근 권한이 없습니다."),
    NOT_FOUND("GLB_ERR_9060", "요청한 리소스가 존재하지 않습니다."),
    METHOD_NOT_ALLOWED("GLB_ERR_9070", "지원하지 않는 HTTP Method 입니다."),
    IP_BLACKLISTED("GLB_ERR_9080", "IP 블랙리스트에 등록된 IP입니다."),
    LOGIN_FAILURE("GLB_ERR_9090", "로그인에 실패하였습니다."),
    LOGIN_FAILURE_EXCEED_MAX_COUNT("GLB_ERR_9100", "로그인 실패 횟수가 초과하였습니다."),
    INTERNAL_SERVER_ERROR("GLB_ERR_9900", "서버에 오류가 발생하였습니다.");

    private final String code;
    private final String message;

    ApplicationErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
