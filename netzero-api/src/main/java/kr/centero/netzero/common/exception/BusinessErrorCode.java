package kr.centero.netzero.common.exception;

import lombok.Getter;

@Getter
public enum BusinessErrorCode {

    /**
     * 업무별 비즈니스 코드를 구분하여 설정
     * 추후 업무코드는 협의에 따라 설정하고, DB에서 관리할 수 있도록 개선
     */

    // 회원 가입 처리(BIZ_ERR_1000 ~ BIZ_ERR_1999)
    USER_ALREADY_EXISTS("BIZ_ERR_1010", "이미 등록된 사용자입니다."),
    USER_NOT_FOUND("BIZ_ERR_1020", "사용자 정보가 존재하지 않습니다."),

    // 조회 데이터 관련(BIZ_ERR_2000 ~ BIZ_ERR_2999)
    DATA_NOT_FOUND("BIZ_ERR_2000", "요청한 데이터가 존재하지 않습니다."),
    DATA_ALREADY_EXIST("BIZ_ERR_2010", "이미 등록된 데이터입니다."),
    DATA_NOT_MATCH("BIZ_ERR_2020", "요청한 데이터가 일치하지 않습니다."),
    DATA_NOT_MODIFIABLE("BIZ_ERR_2030", "수정할 수 없는 데이터입니다."),
    DATA_NOT_DELETABLE("BIZ_ERR_2040", "삭제할 수 없는 데이터입니다.");

    private final String code;
    private final String message;

    BusinessErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
