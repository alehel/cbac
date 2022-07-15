package io.aleksander.cbac;

import com.formdev.flatlaf.FlatLightLaf;
import io.aleksander.cbac.view.ApplicationFrame;

import javax.swing.SwingUtilities;

public class App {
  public static void main(String[] args) {
    FlatLightLaf.setup();

    SwingUtilities.invokeLater(
        () -> {
          ApplicationFrame applicationFrame = new ApplicationFrame();
          applicationFrame.setVisible(true);
        });
  }
}
