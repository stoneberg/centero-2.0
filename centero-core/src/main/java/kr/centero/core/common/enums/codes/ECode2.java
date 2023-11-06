package kr.centero.core.common.enums.codes;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 공통코드 2 레벨
 */
@AllArgsConstructor
@Getter
public enum ECode2 {

    /**
     * 메뉴관리
     */
    SD01("SD01", "메뉴관리"),

    /**
     * 권한관리
     */
    SD02("SD02", "권한관리"),

    /**
     * 서비스관리
     */
    SD03("SD03", "서비스관리"),

    /**
     * 프로그램관리
     */
    SD04("SD04", "프로그램관리"),


    /**
     * 수수료관리
     */
    SD05("SD05", "수수료관리"),

    /**
     * 로케일
     */
    SD06("SD06", "로케일"),

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
    public static ECode2 fromCode(String code) {
        ECode2[] values = ECode2.values();
        for (ECode2 value : values) {
            if (value.getCode().equals(code))
                return value;
        }
        return null;
    }

}
