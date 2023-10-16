package kr.centero.core.domains.enums;

public enum ELevel {
    VIP,
    REGULAR,
    NEW;

    public static ELevel fromValue(String value) {
        for (ELevel level : ELevel.values()) {
            if (level.name().equalsIgnoreCase(value)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}
