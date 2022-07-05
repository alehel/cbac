package io.aleksander.converter.bufferedimage;

import io.aleksander.model.InputFormat;
import io.aleksander.utils.FileUtil;

public class FileToImageConverterSimpleFactory {
    public static FileToImageConverter forInputFormat(InputFormat inputFormat) {
        return new PdfToImageConverter(new FileUtil()); // Only 1 supported input format for now.
    }
}
