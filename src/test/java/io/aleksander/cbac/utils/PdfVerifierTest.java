package io.aleksander.cbac.utils;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PdfVerifierTest {

  private final PdfVerifier pdfVerifier = new PdfVerifier();
  private final String testResourcesPath =
      "src" + File.separator + "test" + File.separator + "resources" + File.separator;

  @Test
  void testFileIsPdf_fileIsReadablePdf_returnsTrue() {
    File file = new File(testResourcesPath + "valid.pdf");
    assertTrue(pdfVerifier.fileIsPdf(file));
  }

  @Test
  void testFileIsPdf_fileIsEncryptedPdf_returnsFalse() {
    File file = new File(testResourcesPath + "encrypted.pdf");
    assertFalse(pdfVerifier.fileIsPdf(file));
  }

  @Test
  void testFileIsPdf_fileIsTxt_returnsFalse() {
    File file = new File(testResourcesPath + "not_valid.txt");
    assertFalse(pdfVerifier.fileIsPdf(file));
  }

  @Test
  void testFileIsPdf_fileDoesNotExist_returnsFalse() {
    File file = new File("fake.txt");
    assertFalse(pdfVerifier.fileIsPdf(file));
  }

  @Test
  void testFileIsPdf_fileIsFolder_returnsFalse() {
    File folder = new File(testResourcesPath + "emptyfolder");
    assertFalse(pdfVerifier.fileIsPdf(folder));
  }
}
