package io.aleksander.converter.arhive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;

public class CbzCreator implements ArchiveCreator {
  private static final Logger logger = LoggerFactory.getLogger(ArchiveCreator.class);

  @Override
  public void createArchive(File imageFolder, String archiveName) {
    logger.info("Creating cbz archive.");
    ZipUtil.pack(imageFolder, new File(archiveName + ".cbz"));
  }
}
