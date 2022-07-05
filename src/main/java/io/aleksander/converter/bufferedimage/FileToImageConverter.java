package io.aleksander.converter.bufferedimage;

import io.aleksander.model.OutputImageFormat;

import java.io.File;
import java.io.IOException;

/**
 * Converts a File to a List of BufferedImage objects, where each BufferedImage object contains a
 * page from the File.
 */
public interface FileToImageConverter {

  /**
   * Convert a part of a File into a List of BufferedImage objects.
   *
   * @param file File to convert.
   * @param startPage the first page to be included in the List of BufferedImage objects. Use -1 to
   *     start at the beginning.
   * @param endPage the last page to be included in the List of BufferedImage objects. Use -1 to
   *     specify last page.
   * @return List of BufferedImage sorted in reading order (startPage is element 0, etc).
   */
  void convertToImageFilesAndWriteToDisk(
      File file, int startPage, int endPage, File destinationFolder, OutputImageFormat outputImage)
      throws IOException;

  void convertToImageFilesAndWriteToDisk(
      File file, File destinationFolder, OutputImageFormat outputImage) throws IOException;
}
