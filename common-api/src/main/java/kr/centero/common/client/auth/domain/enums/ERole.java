package kr.centero.common.client.auth.domain.enums;

public enum ERole {
    CENTERO_ADMIN,
    NETZERO_ADMIN,
    CENTERO_USER,
    NETZERO_USER;

    public String getRoleName() {
        return "ROLE_" + name();
    }

    public static ERole fromValue(String value) {
        for (ERole eRole : ERole.values()) {
            if (eRole.name().equalsIgnoreCase(value)) {
                return eRole;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }

}
