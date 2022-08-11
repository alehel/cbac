package io.aleksander.cbac.converter.archive;

import io.aleksander.cbac.model.OutputFileFormat;

public class ArchiveCreatorSimpleFactory {
    public static ArchiveCreator forOutputFileFormat(OutputFileFormat outputFileFormat) {
        return new CbzCreator(); // Only 1 supported output file format for now.
    }
}
