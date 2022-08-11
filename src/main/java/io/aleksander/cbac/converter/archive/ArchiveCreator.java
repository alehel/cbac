package io.aleksander.cbac.converter.archive;

import java.io.File;

public interface ArchiveCreator {
    void createArchive(File imageFolder, String archiveName);
}
