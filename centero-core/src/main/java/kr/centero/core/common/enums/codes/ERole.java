package kr.centero.core.common.enums.codes;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 사용자 권한 코드
 */
@AllArgsConstructor
@Getter
public enum ERole {
    /**
     * 로그인 전 사용자 역할
     */
    Anonymous("AnonymousRole", "NETZERO 서비스"),

    /**
     * 일반 사용자 역할
     */
    General("GeneralRole", "NETZERO 서비스"),

    /**
     * 검증기관 역할
     */
    Auditor("AuditorRole", "검증기관 "),

    /**
     * 인증기관 역할
     */
    GHGProgram("GHGProgramRole", "인증기관 GHGProgramRole"),

    /**
     * 세무 담당자 역할
     */
    TaxAdmin("TaxAdminRole", "세무 담당자"),
    
    /**
     * 인반 관리자 역할
     */
    Admin("AdminRole", "인반 관리자"),

    /**
     * 통합 관리자 역할
     */
    SystemAdmin("SystemAdminRole", "통합 관리자"),

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
    public static ERole fromCode(String code) {
        ERole[] values = ERole.values();
        for (ERole value : values) {
            if (value.getCode().equals(code))
                return value;
        }
        return null;
    }

}
