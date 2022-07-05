package io.aleksander.model;

public enum OutputFileFormat {
  CBZ("cbz");

  private final String extension;

  OutputFileFormat(String extension) {
    this.extension = extension;
  }

  public String getExtension() {
      return extension;
  }
}
