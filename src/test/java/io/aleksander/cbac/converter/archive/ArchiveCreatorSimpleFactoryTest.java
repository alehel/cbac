package io.aleksander.cbac.converter.archive;

import io.aleksander.cbac.model.OutputFileFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ArchiveCreatorSimpleFactoryTest {

  @Test
  void forOutputFileFormat_formatIsCbz_returnsInstanceOfCbzCreator() {
    ArchiveCreator archiveCreator = ArchiveCreatorSimpleFactory.forOutputFileFormat(OutputFileFormat.CBZ);
    assertTrue(archiveCreator instanceof CbzCreator);
  }
}