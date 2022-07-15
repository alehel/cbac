package io.aleksander.cbac.converter;

import io.aleksander.cbac.converter.arhive.ArchiveCreator;
import io.aleksander.cbac.converter.arhive.ArchiveCreatorSimpleFactory;
import io.aleksander.cbac.converter.bufferedimage.FileToImageConverter;
import io.aleksander.cbac.converter.bufferedimage.FileToImageConverterSimpleFactory;
import io.aleksander.cbac.model.Conversion;
import io.aleksander.cbac.model.ConversionSettings;
import io.aleksander.cbac.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.UUID;
import java.util.stream.Stream;

public class Converter {
  private static final Logger logger = LoggerFactory.getLogger(Converter.class);

  public void convertFileToArchive(
      File outputDirectory, Conversion conversion, ConversionSettings settings) throws IOException {
    // create a temporary directory for storing images while they are being processed. Using UUID as
    // folder name so
    // as to easily avoid duplicates.
    File workingDirectory = Files.createTempDirectory(UUID.randomUUID().toString()).toFile();
    logger.info(
        "Created temporary directory at {} for image processing.",
        workingDirectory.getAbsolutePath());

    // Convert pages to images and store in temporary folder.
    FileToImageConverter fileToImageConverter =
        FileToImageConverterSimpleFactory.forInputFormat(settings.getInputFormat());
    fileToImageConverter.convertToImageFilesAndWriteToDisk(
        conversion.getFile(), workingDirectory, settings.getOutputImageFormat());

    // Bundle the images into the chosen format.
    ArchiveCreator archiveCreator =
        ArchiveCreatorSimpleFactory.forOutputFileFormat(settings.getOutputFileFormat());

    String comicArchiveFileName =
        (new FileUtil())
            .createOutputFileNameWithExtension(
                conversion.getFile(), settings.getOutputFileFormat());
    comicArchiveFileName =
        outputDirectory.getAbsolutePath() + File.separator + comicArchiveFileName;

    archiveCreator.createArchive(workingDirectory, comicArchiveFileName);

    logger.info("Cleaning up working directory");
    try (Stream<Path> paths = Files.walk(workingDirectory.toPath())) {
      paths.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
    }
  }
}
