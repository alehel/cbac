package io.aleksander.cbac.converter.bufferedimage;

import io.aleksander.cbac.model.OutputImageFormat;
import io.aleksander.cbac.utils.FileUtil;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PdfToImageConverterTest {

  private static final String testResourcesPath =
      "src" + File.separator + "test" + File.separator + "resources" + File.separator;

  @Test
  void convertToImageFilesAndWriteToDisk_1PagePdfConvertedTo1Image() throws IOException {
    File file = new File(testResourcesPath + "valid.pdf");

    // mock FileUtil to avoid actually writing any files.
    FileUtil fileUtilMock = mock(FileUtil.class);
    String imageName = "0";
    when(fileUtilMock.createPaddedFileNameForPage(0, 1)).thenReturn(imageName);

    PdfToImageConverter converter = new PdfToImageConverter(fileUtilMock);
    File destinationFolder = new File(testResourcesPath);
    converter.convertToImageFilesAndWriteToDisk(file, destinationFolder, OutputImageFormat.JPEG);

    // verify that we attempted to write a BufferedImage to disk using the correct file name, path
    // and image format.
    verify(fileUtilMock, times(1))
        .writeBufferedImageToDisk(
            any(BufferedImage.class),
            eq(destinationFolder),
            eq(imageName),
            eq(OutputImageFormat.JPEG));
  }
}
