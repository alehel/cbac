package io.aleksander.model;

public enum OutputImageFormat {
    JPEG("jpg");

    private String extension;

    OutputImageFormat(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
