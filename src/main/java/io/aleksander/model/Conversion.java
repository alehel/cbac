package io.aleksander.model;

import java.io.File;

public class Conversion {
    private final File file;
    private ConversionStatus conversionStatus = ConversionStatus.PENDING;

    public Conversion(File file) {
        this.file = file;
    }

    public String getFileName() {
        return file.getName();
    }

    public File getFile() {
        return file;
    }

    public ConversionStatus getStatus() {
        return conversionStatus;
    }

    public void setStatus(ConversionStatus conversionStatus) {
        this.conversionStatus = conversionStatus;
    }
}
