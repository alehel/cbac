package io.aleksander.cbac.view.actions;

import io.aleksander.cbac.converter.Converter;
import io.aleksander.cbac.model.Conversion;
import io.aleksander.cbac.model.ConversionStatus;
import io.aleksander.cbac.model.queue.ConversionQueue;

import javax.swing.JList;
import javax.swing.SwingWorker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;

import static io.aleksander.cbac.model.ConversionStatus.PENDING;

public class ConvertAction implements ActionListener {
  private final ConversionQueue queue;
  private final JList<?> fileList;
  private final Consumer<Integer> updateProgressInPercent;

  public ConvertAction(ConversionQueue queue, JList<?> fileList, Consumer<Integer> updateProgressInPercent) {
    this.queue = queue;
    this.fileList = fileList;
    this.updateProgressInPercent = updateProgressInPercent;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    queue.setConversionInProgress(true);

    // the queue might contain previously completed conversions.
    List<Conversion> conversions =
        queue.getConversions().stream()
            .filter(conversion -> conversion.getStatus() == PENDING)
            .toList();

    SwingWorker<Boolean, Integer> converterThread =
        new SwingWorker<>() {
          @Override
          protected Boolean doInBackground() throws Exception {
            for (int i = 0; i < conversions.size(); i++) {
              Conversion conversionJob = conversions.get(i);
              conversionJob.setStatus(ConversionStatus.WORKING);
              publish(calculateProgress(i, conversions.size()));
              Converter converter = new Converter();
              converter.convertFileToArchive(queue.getOutputDirectory(), conversionJob, queue.getConversionSettings());
              conversionJob.setStatus(ConversionStatus.DONE);
              publish(calculateProgress(i, conversions.size()));
            }

            publish(100);
            return null;
          }

          @Override
          protected void done() {
            queue.setConversionInProgress(false);
          }

          @Override
          protected void process(List<Integer> chunks) {
            fileList.updateUI();

            if (chunks.size() > 0) {
              updateProgressInPercent.accept(chunks.get(chunks.size() - 1));
            }
          }
        };

    converterThread.execute();
  }

  private int calculateProgress(float currentConversion, float toaltConversions) {
    float prog = currentConversion / toaltConversions;
    float total = prog * 100;
    return (int) total;
  }
}
