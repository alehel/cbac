/*
 * Created by JFormDesigner on Mon Jul 04 17:52:23 CEST 2022
 */

package io.aleksander.cbac.view.mainpanel.conversionlist;

import java.awt.*;
import javax.swing.border.*;
import io.aleksander.cbac.model.Conversion;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.util.ResourceBundle;

/**
 * @author Aleksander Helgaker
 */
public class ConversionListRenderer extends JPanel implements ListCellRenderer<Conversion> {
    public ConversionListRenderer() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        ResourceBundle bundle = ResourceBundle.getBundle("strings");
        nameLabel = new JLabel();
        statusLabel = new JLabel();

        //======== this ========
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

        //---- nameLabel ----
        nameLabel.setText(bundle.getString("ConversionJobRenderer.nameLabel.text"));
        add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
          GridBagConstraints.CENTER, GridBagConstraints.BOTH,
          new Insets(0, 0, 0, 10), 0, 0));

        //---- statusLabel ----
        statusLabel.setText(bundle.getString("ConversionJobRenderer.statusLabel.text"));
        add(statusLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
          GridBagConstraints.CENTER, GridBagConstraints.BOTH,
          new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

  @Override
  public Component getListCellRendererComponent(
      JList<? extends Conversion> list,
      Conversion value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {
    nameLabel.setText(value.getFileName());
    statusLabel.setText(value.getStatus().getDisplayString());

    Color background;

    if (isSelected) {
      background = new Color(225, 234, 247);
    } else {
      background = Color.WHITE;
    }

      setBackground(background);
    return this;
  }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel nameLabel;
    private JLabel statusLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
