package io.aleksander.model;

public enum ConversionStatus {
    PENDING("Pending"),
    WORKING("Working"),
    DONE("Done");
    private final String displayString;
    ConversionStatus(String displayString) {
        this.displayString = displayString;
    }

    public String getDisplayString() {
        return displayString;
    }
}
