package io.aleksander.cbac.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/** Verifies if we're able to read the content of a PDF file. */
public class PdfVerifier {

  private static final Logger logger = LoggerFactory.getLogger(PdfVerifier.class);

  /**
   * Perform a number of rudimentary checks to verify that the File object is a pdf.
   *
   * We don't perform a full-blown PDF validation due to the fact that A LOT of pdf files will actually fail such
   * a test. 
   *
   * @param file file to verify.
   * @return true if the file is considered to be a readable pdf, false if not.
   */
  public boolean fileIsPdf(File file) {
    logger.info("Verifying {} is a PDF.", file.getName());

    if (!file.exists()) {
      logger.warn("{} does not exist!", file.getName());
      return false;
    }

    if (!file.isFile()) {
      logger.warn("{} - is not a file", file.getName());
      return false;
    }

    try (PDDocument pdfDocument = PDDocument.load(file)) {
      // dirty way of trying to determine if a PDF is readable. Will trigger an exception otherwise.
      pdfDocument.getPage(0);

      logger.info("{} is a readable PDF.", file.getName());
      return true;
    } catch (IOException e) {
      logger.warn("Error reading file {}. Cause: [{}]", file.getName(), e);
      return false;
    }
  }
}
