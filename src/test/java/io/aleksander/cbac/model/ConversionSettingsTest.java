package io.aleksander.cbac.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConversionSettingsTest {

  @Test
  void conversionSettings_constructorCalled_defaultValuesSet() {
    ConversionSettings conversionSettings = new ConversionSettings(InputFormat.PDF);
    assertEquals(InputFormat.PDF, conversionSettings.getInputFormat());
    assertEquals(OutputImageFormat.JPEG, conversionSettings.getOutputImageFormat());
    assertEquals(OutputFileFormat.CBZ, conversionSettings.getOutputFileFormat());
    assertEquals(300, conversionSettings.getDpi());
  }
}
