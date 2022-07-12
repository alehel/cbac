/*
 * Created by JFormDesigner on Tue Jul 12 17:19:50 CEST 2022
 */

package io.aleksander.view.mainpanel;

import io.aleksander.model.Conversion;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * @author Aleksander Helgaker
 */
public class MainPanel extends JPanel {
  public MainPanel() {
    initComponents();
  }

  public JLabel getQueueLabel() {
    return queueLabel;
  }

  public JScrollPane getFileListScrollPane() {
    return fileListScrollPane;
  }

  public JList<Conversion> getFileList() {
    return fileList;
  }

  public JPanel getPanel1() {
    return panel1;
  }

  public JButton getRemoveBtn() {
    return removeBtn;
  }

  public JButton getClearBtn() {
    return clearBtn;
  }

  public JSeparator getSeparator1() {
    return separator1;
  }

  public JPanel getControlsPane() {
    return controlsPane;
  }

  public JLabel getSaveToLabel() {
    return saveToLabel;
  }

  public JTextField getOutputDirectoryTextField() {
    return outputDirectoryTextField;
  }

  public JButton getOutputFolderBtn() {
    return outputFolderBtn;
  }

  public JButton getConvertBtn() {
    return convertBtn;
  }

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    ResourceBundle bundle = ResourceBundle.getBundle("strings");
    queueLabel = new JLabel();
    fileListScrollPane = new JScrollPane();
    fileList = new JList<>();
    panel1 = new JPanel();
    removeBtn = new JButton();
    clearBtn = new JButton();
    separator1 = new JSeparator();
    controlsPane = new JPanel();
    saveToLabel = new JLabel();
    outputDirectoryTextField = new JTextField();
    outputFolderBtn = new JButton();
    convertBtn = new JButton();

    //======== this ========
    setMinimumSize(new Dimension(640, 480));
    setPreferredSize(new Dimension(640, 480));
    setLayout(new GridBagLayout());
    ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0};
    ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0};
    ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
    ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

    //---- queueLabel ----
    queueLabel.setText(bundle.getString("MainPanel.queueLabel.text"));
    queueLabel.setFont(queueLabel.getFont().deriveFont(queueLabel.getFont().getStyle() | Font.BOLD));
    add(queueLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
      GridBagConstraints.CENTER, GridBagConstraints.BOTH,
      new Insets(5, 5, 5, 5), 0, 0));

    //======== fileListScrollPane ========
    {
      fileListScrollPane.setViewportView(fileList);
    }
    add(fileListScrollPane, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
      GridBagConstraints.CENTER, GridBagConstraints.BOTH,
      new Insets(5, 5, 10, 5), 0, 0));

    //======== panel1 ========
    {
      panel1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));

      //---- removeBtn ----
      removeBtn.setText(bundle.getString("MainPanel.removeBtn.text"));
      panel1.add(removeBtn);

      //---- clearBtn ----
      clearBtn.setText(bundle.getString("MainPanel.clearBtn.text"));
      panel1.add(clearBtn);
    }
    add(panel1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
      GridBagConstraints.CENTER, GridBagConstraints.BOTH,
      new Insets(0, 0, 5, 0), 0, 0));
    add(separator1, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
      GridBagConstraints.CENTER, GridBagConstraints.BOTH,
      new Insets(5, 5, 10, 5), 0, 0));

    //======== controlsPane ========
    {
      controlsPane.setLayout(new GridBagLayout());
      ((GridBagLayout)controlsPane.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
      ((GridBagLayout)controlsPane.getLayout()).rowHeights = new int[] {0, 0, 0};
      ((GridBagLayout)controlsPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
      ((GridBagLayout)controlsPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

      //---- saveToLabel ----
      saveToLabel.setText(bundle.getString("MainPanel.saveToLabel.text"));
      saveToLabel.setFont(saveToLabel.getFont().deriveFont(saveToLabel.getFont().getStyle() | Font.BOLD));
      controlsPane.add(saveToLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 0, 5, 5), 0, 0));

      //---- outputDirectoryTextField ----
      outputDirectoryTextField.setHorizontalAlignment(SwingConstants.RIGHT);
      outputDirectoryTextField.setEditable(false);
      outputDirectoryTextField.setPreferredSize(null);
      controlsPane.add(outputDirectoryTextField, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 0, 5, 5), 0, 0));

      //---- outputFolderBtn ----
      outputFolderBtn.setText(bundle.getString("MainPanel.outputFolderBtn.text"));
      controlsPane.add(outputFolderBtn, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 0, 5, 0), 0, 0));

      //---- convertBtn ----
      convertBtn.setText(bundle.getString("MainPanel.convertBtn.text"));
      controlsPane.add(convertBtn, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 0), 0, 0));
    }
    add(controlsPane, new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0,
      GridBagConstraints.CENTER, GridBagConstraints.BOTH,
      new Insets(5, 5, 5, 5), 0, 0));
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JLabel queueLabel;
  private JScrollPane fileListScrollPane;
  private JList<Conversion> fileList;
  private JPanel panel1;
  private JButton removeBtn;
  private JButton clearBtn;
  private JSeparator separator1;
  private JPanel controlsPane;
  private JLabel saveToLabel;
  private JTextField outputDirectoryTextField;
  private JButton outputFolderBtn;
  private JButton convertBtn;
  // JFormDesigner - End of variables declaration  //GEN-END:variables
}
