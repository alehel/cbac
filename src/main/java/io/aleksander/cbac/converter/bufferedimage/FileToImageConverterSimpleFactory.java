package io.aleksander.cbac.converter.bufferedimage;

import io.aleksander.cbac.model.InputFormat;
import io.aleksander.cbac.utils.FileUtil;

public class FileToImageConverterSimpleFactory {
    public static FileToImageConverter forInputFormat(InputFormat inputFormat) {
        return new PdfToImageConverter(new FileUtil()); // Only 1 supported input format for now.
    }
}
