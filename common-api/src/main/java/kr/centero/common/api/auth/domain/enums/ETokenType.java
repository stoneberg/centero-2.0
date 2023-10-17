package kr.centero.common.api.auth.domain.enums;

public enum ETokenType {
    ACCESS_TOKEN,
    REFRESH_TOKEN;

    public static ETokenType fromValue(String value) {
        for (ETokenType tokenType : ETokenType.values()) {
            if (tokenType.name().equalsIgnoreCase(value)) {
                return tokenType;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }

}
