package io.aleksander.cbac.model.queue;

import io.aleksander.cbac.model.Conversion;
import io.aleksander.cbac.model.ConversionSettings;
import io.aleksander.cbac.model.InputFormat;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConversionQueue {
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  private final List<Conversion> queue = new ArrayList<>();
  private final List<NewConversionListener> newConversionListeners = new ArrayList<>();
  private final List<ConversionRemovedListener> conversionRemovedListeners = new ArrayList<>();
  private final List<ConversionQueueClearedListener> conversionQueueClearedListeners =
      new ArrayList<>();
  private final ConversionSettings conversionSettings = new ConversionSettings(InputFormat.PDF);
  private File outputDirectory;
  private final AtomicBoolean conversionInProgress = new AtomicBoolean(false);
  public static final String OUTPUT_DIRECTORY_PROPERTY = "OUTPUT_DIRECTORY";
  public static final String CONVERSION_IN_PROGRESS = "CONVERSION_IN_PROGRESS";

  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    pcs.addPropertyChangeListener(propertyName, listener);
  }

  public void clear() {
    queue.clear();
    conversionQueueClearedListeners.forEach(
        ConversionQueueClearedListener::notifyConversionQueueClearedListener);
  }

  public void addConversion(Conversion conversion) {
    queue.add(conversion);
    newConversionListeners.forEach(listener -> listener.notifyNewConversionJob(conversion));
  }

  public void removeConversion(Conversion conversion) {
    queue.remove(conversion);
    conversionRemovedListeners.forEach(listener -> listener.notifyConversionRemoved(conversion));
  }

  public void addNewElementListener(NewConversionListener listener) {
    newConversionListeners.add(listener);
  }

  public void addConversionRemovedListener(ConversionRemovedListener listener) {
    conversionRemovedListeners.add(listener);
  }

  public void addConversionQueueClearedListeners(ConversionQueueClearedListener listener) {
    conversionQueueClearedListeners.add(listener);
  }

  public List<Conversion> getConversions() {
    return queue;
  }

  public void setOutputDirectory(File newValue) {
    File oldValue = this.outputDirectory;
    this.outputDirectory = newValue;
    pcs.firePropertyChange(OUTPUT_DIRECTORY_PROPERTY, oldValue, newValue);
  }

  public File getOutputDirectory() {
    return outputDirectory;
  }

    public ConversionSettings getConversionSettings() {
        return conversionSettings;
    }

  public boolean getConversionInProgress() {
    return conversionInProgress.get();
  }

  public void setConversionInProgress(boolean newValue) {
    boolean oldValue = conversionInProgress.get();
    conversionInProgress.set(newValue);
    pcs.firePropertyChange(CONVERSION_IN_PROGRESS, oldValue, newValue);
  }
}
