package io.aleksander.view.actions;

import io.aleksander.converter.Converter;
import io.aleksander.model.ConversionStatus;
import io.aleksander.model.Conversion;
import io.aleksander.model.queue.ConversionQueue;

import javax.swing.JList;
import javax.swing.SwingWorker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConvertAction implements ActionListener {
  private final ConversionQueue queue;
  private final JList<?> fileList;

  public ConvertAction(ConversionQueue queue, JList<?> fileList) {
    this.queue = queue;
    this.fileList = fileList;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    queue.setConversionInProgress(true);

    // Use a copy of the queue elements in case user adds new ones mid-conversion, which would lead to concurrent
    // modification. New elements won't be converted untill user starts a new round.
    List<Conversion> conversions = List.copyOf(queue.getConversions());

    SwingWorker<Boolean, Integer> converterThread =
        new SwingWorker<>() {
          @Override
          protected Boolean doInBackground() throws Exception {
            for (Conversion conversionJob : conversions) {
              // the queue might contain previously completed conversions.
              if (conversionJob.getStatus() != ConversionStatus.PENDING) {
                continue;
              }

              conversionJob.setStatus(ConversionStatus.WORKING);
              publish();
              Converter converter = new Converter();
              converter.convertFileToArchive(queue.getOutputDirectory(), conversionJob, queue.getConversionSettings());
              conversionJob.setStatus(ConversionStatus.DONE);
              publish();
            }
            return null;
          }

          @Override
          protected void done() {
            queue.setConversionInProgress(false);
          }

          @Override
          protected void process(List<Integer> chunks) {
            fileList.updateUI();
          }
        };

    converterThread.execute();
  }
}
