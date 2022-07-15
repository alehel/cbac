package io.aleksander.cbac.model.queue;

import io.aleksander.cbac.model.Conversion;

@FunctionalInterface
public interface ConversionRemovedListener {
    void notifyConversionRemoved(Conversion conversion);
}
