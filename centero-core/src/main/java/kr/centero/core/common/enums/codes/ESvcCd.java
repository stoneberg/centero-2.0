package kr.centero.core.common.enums.codes;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 서비스 코드
 */
@AllArgsConstructor
@Getter
public enum ESvcCd {
    /**
     * ghg 서비스
     */
    GHG("ghg", "GHG 서비스"),
    NETZERO("netzero", "NETZERO 서비스"),

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
     * code 값으로 부터 ESvcCd 생성
     * @param code
     * @return
     */
    public static ESvcCd fromCode(String code) {
        ESvcCd[] values = ESvcCd.values();
        for (ESvcCd value : values) {
            if (value.getCode().equals(code))
                return value;
        }
        return null;
    }

}
