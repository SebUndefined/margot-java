package de.onetwotree.margaux.Enum;

/**
 * Created by SebUndefined on 28/09/17.
 */
public enum AlertLevel {
    CRIT("Critical"),
    WARN("Warning"),
    NOTICE("Notice");

    private final String displayName;

    AlertLevel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
