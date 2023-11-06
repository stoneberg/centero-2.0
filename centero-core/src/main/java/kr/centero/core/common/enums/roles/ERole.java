package kr.centero.core.common.enums.roles;

public enum ERole {
    CENTERO_ADMIN,
    NETZERO_ADMIN,
    GHG_ADMIN,
    CENTERO_USER,
    NETZERO_USER,
    GHG_USER;

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
