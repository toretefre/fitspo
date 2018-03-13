package tdt4140.gr1806.app.ui;

import java.awt.Label;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tdt4140.gr1806.app.core.Customer;
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
	@FXML private Label userName;

	private void loadCustomerData(Customer selectedPerson) {
		userName.setText(selectedPerson.getName());
		
		for (int i=0; i<7; i++) {
			HBox dataRow = new HBox();
			dataRow.setId("datarow" + i % 2);
			dataRow.setPrefWidth(container.getPrefWidth());
		}
		
		selectedPerson.getTelephone();
		selectedPerson.getBirthdate();
		selectedPerson.getGender();
		selectedPerson.getHeight();
		selectedPerson.getWeight();
		selectedPerson.getSteps();
		selectedPerson.getRegistrationDate();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		container.setFitToWidth(true);
		loadCustomerData(Customer);
	}
}