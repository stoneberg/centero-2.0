package kr.centero.core.domains.enums;

public enum EPosition {
    GK, DF, MF, FW;

    public static EPosition fromValue(String value) {
        for (EPosition position : EPosition.values()) {
            if (position.name().equalsIgnoreCase(value)) {
                return position;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}
