package kr.centero.ghg.auth.domain.enums;

public enum ERole {
    ADMIN,
    USER;

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
