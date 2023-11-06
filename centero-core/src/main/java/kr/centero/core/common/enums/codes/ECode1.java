package kr.centero.core.common.enums.codes;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 공통코드 1 레벨
 */
@AllArgsConstructor
@Getter
public enum ECode1 {

    /**
     * 시스템관리
     */
    SD("SD", "시스템관리"),

    /**
     * 업무공통코드
     */
    BZ("BZ", "업무공통코드"),
    ;
    private String code;
    private String label;

    /**
     * enum 값과 문자열이 동일하면 true return
     * @param value
     * @return
     */
    public boolean isEquals(String value) {
        return this.getCode().equals(value);
    }

    /**
     * code 값으로 부터 Code1 생성
     * @param code
     * @return
     */
    public static ECode1 fromCode(String code) {
        ECode1[] values = ECode1.values();
        for (ECode1 value : values) {
            if (value.getCode().equals(code))
                return value;
        }
        return null;
    }

}
