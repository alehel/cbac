package io.aleksander.converter.bufferedimage;

import io.aleksander.model.OutputImageFormat;
import io.aleksander.utils.FileUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class PdfToImageConverter implements FileToImageConverter {
  private static final Logger logger = LoggerFactory.getLogger(PdfToImageConverter.class);
  private final FileUtil fileUtil;

  public PdfToImageConverter(FileUtil fileUtil) {
    this.fileUtil = fileUtil;
  }

  @Override
  public void convertToImageFilesAndWriteToDisk(
      File file, File destinationFolder, OutputImageFormat outputImage) throws IOException {
    convertToImageFilesAndWriteToDisk(file, -1, -1, destinationFolder, outputImage);
  }

  @Override
  public void convertToImageFilesAndWriteToDisk(
      File pdf,
      int startPage,
      int endPage,
      File destinationFolder,
      OutputImageFormat outputImageFormat)
      throws IOException {
    logger.info("Creating images of {}.", pdf.getName());

    try (PDDocument document = PDDocument.load(pdf)) {
      PDFRenderer pdfRenderer = new PDFRenderer(document);

      // Determine the range
      if (startPage < 0) {
        startPage = 0;
      }

      if (endPage < 0) {
        endPage = document.getNumberOfPages();
      }

      int numberOfPagesToConvert = endPage - startPage;

      for (int i = startPage; i < numberOfPagesToConvert; i++) {
        BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);

        fileUtil.writeBufferedImageToDisk(
            bufferedImage,
            destinationFolder,
            fileUtil.createPaddedFileNameForPage(i, numberOfPagesToConvert),
            outputImageFormat);
      }
    }
  }
}
