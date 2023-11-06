package kr.centero.core.common.enums.codes;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 공통코드 0 레벨
 */
@AllArgsConstructor
@Getter
public enum ECode0 {

    ROOT("root", "공통코드"),

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
     * code 값으로 부터 Code0 생성
     * @param code
     * @return
     */
    public static ECode0 fromCode(String code) {
        ECode0[] values = ECode0.values();
        for (ECode0 value : values) {
            if (value.getCode().equals(code))
                return value;
        }
        return null;
    }

}
