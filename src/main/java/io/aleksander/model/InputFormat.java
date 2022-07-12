package io.aleksander.model;

public enum InputFormat {
    PDF(false);

    InputFormat(boolean supportsPassthrough) {
        this.supportsPassthrough = supportsPassthrough;
    }

    private boolean supportsPassthrough;

    public boolean supportsPassthrough() {
        return supportsPassthrough;
    }
}
