package io.aleksander.model;

public class ConversionSettings {
  private int dpi;
  private InputFormat inputFormat;
  private OutputImageFormat outputImageFormat;
  private OutputFileFormat outputFileFormat;

  public ConversionSettings(InputFormat inputFormat) {
    this.inputFormat = inputFormat;
    this.outputImageFormat = OutputImageFormat.JPEG;
    this.outputFileFormat = OutputFileFormat.CBZ;
    this.dpi = 300;
  }

    public OutputFileFormat getOutputFileFormat() {
        return outputFileFormat;
    }

    public void setOutputFileFormat(OutputFileFormat outputFileFormat) {
        this.outputFileFormat = outputFileFormat;
    }

    public OutputImageFormat getOutputImageFormat() {
        return outputImageFormat;
    }

    public void setOutputImageFormat(OutputImageFormat outputImageFormat) {
        this.outputImageFormat = outputImageFormat;
    }

    public InputFormat getInputFormat() {
        return inputFormat;
    }

    public void setInputFormat(InputFormat inputFormat) {
        this.inputFormat = inputFormat;
    }

  public int getDpi() {
    return dpi;
  }

  public void setDpi(int dpi) {
    this.dpi = dpi;
  }
}
