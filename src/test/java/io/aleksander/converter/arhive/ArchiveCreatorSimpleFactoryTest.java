package io.aleksander.converter.arhive;

import io.aleksander.model.OutputFileFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArchiveCreatorSimpleFactoryTest {

  @Test
  void forOutputFileFormat_formatIsCbz_returnsInstanceOfCbzCreator() {
    ArchiveCreator archiveCreator = ArchiveCreatorSimpleFactory.forOutputFileFormat(OutputFileFormat.CBZ);
    assertTrue(archiveCreator instanceof CbzCreator);
  }
}