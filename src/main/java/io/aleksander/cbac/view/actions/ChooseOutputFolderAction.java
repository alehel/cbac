package io.aleksander.cbac.view.actions;

import io.aleksander.cbac.model.queue.ConversionQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JFileChooser;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ChooseOutputFolderAction implements ActionListener {
  private final ConversionQueue queue;
  private final JFileChooser fileChooser;
  private final Component parent;

  private static final Logger logger = LoggerFactory.getLogger(ChooseOutputFolderAction.class);

  public ChooseOutputFolderAction(
      JFileChooser fileChooser, ConversionQueue queue, Component parent) {
    this.fileChooser = fileChooser;
    this.queue = queue;
    this.parent = parent;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int returnValue = fileChooser.showSaveDialog(parent);

    if (returnValue != JFileChooser.APPROVE_OPTION) {
      return;
    }

    File selectedDirectory = fileChooser.getSelectedFile();

    if (!selectedDirectory.exists()) {
      logger.warn("User specified directory, but unable to find {}", selectedDirectory.getName());
      return;
    }

    if (!selectedDirectory.canRead() || !selectedDirectory.canWrite()) {
      logger.warn("Missing read/write permission for directory {}", selectedDirectory.getName());
      return;
    }

    queue.setOutputDirectory(selectedDirectory);
  }
}
