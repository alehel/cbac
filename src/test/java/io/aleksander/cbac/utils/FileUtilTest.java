package io.aleksander.cbac.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileUtilTest {

  private final FileUtil fileUtil = new FileUtil();

  @ParameterizedTest
  @ValueSource(ints = {0, 10, 100, 1000, 10000})
  void createPaddedFileName_pageIsLastPage_returnsSameAsPageNumber(int pageNumber) {
    String paddedFileName = fileUtil.createPaddedFileNameForPage(pageNumber, pageNumber);
    assertEquals(String.valueOf(pageNumber), paddedFileName);
  }

  @ParameterizedTest
  @CsvSource({"0,10,00", "9, 10, 09", "98, 100, 098"})
  void createPaddedFileName_pageNeedsPadding_returnsPageNumberWithPadding(
      int pageNumber, int totalPages, String expectedFileName) {
    String paddedFileName = fileUtil.createPaddedFileNameForPage(pageNumber, totalPages);
    assertEquals(expectedFileName, paddedFileName);
  }

  @ParameterizedTest
  @CsvSource({"a.pdf, a", "b, b"})
  void fileNameWithoutExtension_returnsFileNameWithoutExtensionIfPresent(
      String input, String expected) {
    File inputFile = new File(input);

    String result = fileUtil.fileNameWithoutExtension(inputFile);
    assertEquals(expected, result);
  }
}
