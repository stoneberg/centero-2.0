package kr.centero.core.common.enums.codes;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Yes or No 코드
 */
@AllArgsConstructor
@Getter
public enum EYn {
    /**
     * YES or True
     */
    Y("Y", "YES"),

    /**
     * NO or False
     */
    N("N", "NO"),


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
     * code 값으로 부터 ERole 생성
     * @param code
     * @return
     */
    public static EYn fromCode(String code) {
        EYn[] values = EYn.values();
        for (EYn value : values) {
            if (value.getCode().equals(code))
                return value;
        }
        return null;
    }

}
