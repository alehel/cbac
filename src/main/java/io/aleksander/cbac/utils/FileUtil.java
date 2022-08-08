package io.aleksander.cbac.utils;

import io.aleksander.cbac.model.OutputImageFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FileUtil {
  private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

  /**
   * Create a file name for image where we take the page number, and pad it without enough 0s to
   * ensure that the file system will sort them correctly. For instance, if your input is page 3 of
   * a 120 page comic book, the return value will be 003.
   *
   * @param page the page you want a file name for.
   * @param numberOfPages the total number of pages in the comic book.
   * @return a file name padded with sufficient 0s so that the file system will sort them correctly.
   */
  public String createPaddedFileNameForPage(int page, int numberOfPages) {
    StringBuilder string = new StringBuilder(String.valueOf(page));
    int places = String.valueOf(numberOfPages).length();

    while (string.length() < places) {
      string.insert(0, "0");
    }

    return string.toString();
  }

  /**
   * Returns the file name of a File without its extension.
   *
   * @param inputFile the inputFile to query.
   * @return the file name without the extension.
   */
  public String fileNameWithoutExtension(final File inputFile) {
    Objects.requireNonNull(inputFile, "Can't determine file name for null");

    int notFound = -1;
    int indexOfExtension = inputFile.getName().lastIndexOf('.');

    if (indexOfExtension == notFound) {
      return inputFile.getName();
    }

    return inputFile.getName().substring(0, indexOfExtension);
  }

  /**
   * Writes a BufferedImage to disk.
   *
   * @param bufferedImage the image to write to disk.
   * @param destinationFolder which folder to place the file in.
   * @param fileName the file name for the image.
   * @param outputImageFormat the image file format to use.
   * @throws IOException if saving the file failed.
   */
  public void writeBufferedImageToDisk(
      BufferedImage bufferedImage,
      File destinationFolder,
      String fileName,
      OutputImageFormat outputImageFormat)
      throws IOException {
    File outputImage =
        new File(
            destinationFolder.getAbsolutePath()
                + File.separator
                + fileName
                + "."
                + OutputImageFormat.JPEG.getExtension());
    ImageIO.write(bufferedImage, outputImageFormat.getExtension(), outputImage);
    logger.debug("{} written to disk.", outputImage.getAbsolutePath());
  }
}
