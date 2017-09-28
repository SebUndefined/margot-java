package de.onetwotree.margaux.Enum;

/**
 * Created by SebUndefined on 28/09/17.
 */
public enum AlertStatus {
    OPEN("Open"),
    SOLVED("Solved");

    private final String displayName;

    AlertStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
