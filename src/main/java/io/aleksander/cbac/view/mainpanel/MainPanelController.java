package io.aleksander.cbac.view.mainpanel;

import io.aleksander.cbac.model.Conversion;
import io.aleksander.cbac.model.queue.ConversionQueue;
import io.aleksander.cbac.view.mainpanel.conversionlist.FileDropListener;
import io.aleksander.cbac.view.actions.ChooseOutputFolderAction;
import io.aleksander.cbac.view.actions.ConvertAction;
import io.aleksander.cbac.view.mainpanel.conversionlist.ConversionListRenderer;
import io.aleksander.cbac.view.mainpanel.conversionlist.ListDataAdapter;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import java.awt.Component;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;

public class MainPanelController {
  private final MainPanel view;
  private final ConversionQueue model;
  private final DefaultListModel<Conversion> fileListModel = new DefaultListModel<>();
  private final JFileChooser fileChooser = new JFileChooser();

  public MainPanelController() {
    model = new ConversionQueue();
    view = new MainPanel();
    configureComponents();
  }

  private void configureComponents() {
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    setButtonStates();
    view.getConvertBtn().addActionListener(new ConvertAction(model, view.getFileList()));
    configureOutputFolderButton();
    configureFileList();

    model.addPropertyChangeListener(ConversionQueue.OUTPUT_DIRECTORY_PROPERTY, e -> {
        view.getOutputDirectoryTextField().setText(model.getOutputDirectory().getAbsolutePath());
        setButtonStates();
    });

    model.addPropertyChangeListener(ConversionQueue.CONVERSION_IN_PROGRESS, e-> setButtonStates());
  }

  private void configureFileList() {
    view.getFileList().setModel(fileListModel);
    model.addNewElementListener(fileListModel::addElement);
    fileListModel.addListDataListener(new ListDataAdapter(e -> setButtonStates()));

    FileDropListener fileDropListener =
        new FileDropListener(model);

    new DropTarget(
        view.getFileList(), DnDConstants.ACTION_COPY, fileDropListener, true, null);

    view.getFileList().setCellRenderer(new ConversionListRenderer());

    view.getClearBtn().addActionListener(e -> model.clear());

    view.getRemoveBtn().addActionListener(e -> {
      Conversion selectedValue = view.getFileList().getSelectedValue();
      model.removeConversion(selectedValue);
    });

    model.addConversionRemovedListener(fileListModel::removeElement);

    model.addConversionQueueClearedListeners(() -> {
      fileListModel.clear();
      setButtonStates();
    });
  }

  private void setButtonStates() {
    boolean filesInQueue = view.getFileList().getModel().getSize() > 0;
    boolean outputDirectorySet = model.getOutputDirectory() != null;
    boolean conversionInProgress = model.getConversionInProgress();

    view.getConvertBtn().setEnabled(filesInQueue && outputDirectorySet && !conversionInProgress);
    view.getOutputFolderBtn().setEnabled(!conversionInProgress);
    view.getClearBtn().setEnabled(filesInQueue);
    view.getRemoveBtn().setEnabled(filesInQueue);
  }

  private void configureOutputFolderButton() {
    view.getOutputFolderBtn().addActionListener(new ChooseOutputFolderAction(fileChooser, model, this.getView()));
  }

  public Component getView() {
    return view;
  }
}
