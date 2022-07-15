package io.aleksander.cbac.utils;

import io.aleksander.cbac.model.OutputFileFormat;
import io.aleksander.cbac.model.OutputImageFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
   * Takes the name of an input file and returns a copy where the extension has been changed for the
   * one specified.
   *
   * @param inputFile
   * @param outputFileFormat
   * @return a file name using the specified extension.
   */
  public String createOutputFileNameWithExtension(
      File inputFile, OutputFileFormat outputFileFormat) {
    String fileNameWithoutExtension =
        inputFile.getName().substring(0, inputFile.getName().lastIndexOf('.'));
    return fileNameWithoutExtension + "." + outputFileFormat.getExtension();
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
