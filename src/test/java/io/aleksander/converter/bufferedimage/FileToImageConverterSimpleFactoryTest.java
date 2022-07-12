package io.aleksander.converter.bufferedimage;

import io.aleksander.model.InputFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileToImageConverterSimpleFactoryTest {

  @Test
  void forInputFormat_formatIsPdf_returnsInstanceOfPdfToImageConverter() {
    FileToImageConverter fileToImageConverter = FileToImageConverterSimpleFactory.forInputFormat(InputFormat.PDF);
    assertTrue(fileToImageConverter instanceof PdfToImageConverter);
  }
}