package kr.centero.core.common.enums.codes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ELocale {

    /**
     * 영어
     */
    EN_US("en_US", "영어"),
    /**
     * 한국어
     */
    KO_KR("ko_KR", "한국어"),
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
     * code 값으로 부터 ELocale 생성
     * @param code
     * @return
     */
    public static ELocale fromCode(String code) {
        ELocale[] values = ELocale.values();
        for (ELocale value : values) {
            if (value.getCode().equals(code))
                return value;
        }
        return null;
    }
}
