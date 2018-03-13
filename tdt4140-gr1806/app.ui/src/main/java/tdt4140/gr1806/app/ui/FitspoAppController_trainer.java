package tdt4140.gr1806.app.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tdt4140.gr1806.app.core.Trainer;
/**
 * FitspoAppController Class use the Trainer Class to represent an updated list of customers and their total steps.
 * Controlling the FitspoApp_trainer.fxml
 * @author Magnus
 * Modified by Tore
 * @version 1.0
 * @see tdt4140.gr1806.app.core.Trainer.java
 * @see tdt4140.gr1806.app.ui.FitspoApp.fxml
 */
public class FitspoAppController_trainer implements Initializable {
	@FXML private ScrollPane container;
	@FXML private VBox content;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		container.setFitToWidth(true);
		// personalInfo(Trainer.getCustomers());
	}
}