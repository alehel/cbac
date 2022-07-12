package io.aleksander.model.queue;

import io.aleksander.model.Conversion;

@FunctionalInterface
public interface NewConversionListener {
    void notifyNewConversionJob(Conversion file);
}
