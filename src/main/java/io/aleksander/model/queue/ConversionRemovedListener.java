package io.aleksander.model.queue;

import io.aleksander.model.Conversion;

@FunctionalInterface
public interface ConversionRemovedListener {
    void notifyConversionRemoved(Conversion conversion);
}
