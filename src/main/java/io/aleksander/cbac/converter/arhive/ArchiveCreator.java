package io.aleksander.cbac.converter.arhive;

import java.io.File;

public interface ArchiveCreator {
    void createArchive(File imageFolder, String archiveName);
}
