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
 * Controlling the FitspoApp.fxml
 * @author Magnus
 * Modified by Tore
 * @version 1.0
 * @see tdt4140.gr1806.app.core.Trainer.java
 * @see tdt4140.gr1806.app.ui.FitspoApp.fxml
 */
public class FitspoAppController_trainer implements Initializable {
	@FXML private ScrollPane container;
	@FXML private VBox content;
	
	/**
	 * uses the Trainer Class method getCustomer which returns a ArrayList<ArrayList<String>> of customers and puts it into
	 * the ScrollPane from FitspoApp.fxml. Sets differents ID for styling purposes.
	 * @param customers
	 * @see tdt4140.gr1806.app.core.Trainer.java
	 */
	private void personalInfo(ArrayList<ArrayList<String>> customers) {
		HBox person = new HBox();
		person.setId("personbox" + i % 2);
		person.setPrefWidth(container.getPrefWidth());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		container.setFitToWidth(true);
		// personalInfo(Trainer.getCustomers());
	}
}