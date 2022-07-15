package io.aleksander.cbac.view;

import io.aleksander.cbac.view.mainpanel.MainPanelController;

import javax.swing.JFrame;
import java.util.ResourceBundle;

public class ApplicationFrame extends JFrame {

  public ApplicationFrame() {
    ResourceBundle bundle = ResourceBundle.getBundle("strings");
    setTitle(bundle.getString("Application.title"));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    MainPanelController mainPanelController = new MainPanelController();
    add(mainPanelController.getView());
    setMinimumSize(mainPanelController.getView().getMinimumSize());
    // center the frame.
    setLocationRelativeTo(null);
  }
}
