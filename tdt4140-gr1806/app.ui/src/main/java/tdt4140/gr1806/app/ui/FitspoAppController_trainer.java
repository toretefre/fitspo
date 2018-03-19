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
import tdt4140.gr1806.app.core.Customer;
import tdt4140.gr1806.app.core.Gender;
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
		
		// The reason a lot is commented out is because the Customer can be constructed without the fields
		// resulting in errors here (.toString() for example). This should be solved in Customer and not here.
		ArrayList<String[]> data = new ArrayList<>();
		data.add(new String[]{"Telephone", selectedPerson.getTelephone()});
		data.add(new String[]{"Birthdate", selectedPerson.getBirthdate().toString()});
		data.add(new String[]{"Gender", selectedPerson.getGender().toString()});
		data.add(new String[]{"Height", Integer.toString(selectedPerson.getHeight())});
		data.add(new String[]{"Weight", Integer.toString(selectedPerson.getWeight())});
		data.add(new String[]{"Steps", Integer.toString(selectedPerson.getSteps())});
		data.add(new String[]{"Registration Date", selectedPerson.getRegistrationDate().toString()});
		
		for (int i = 0; i < data.size(); i++) {
			HBox dataRow = new HBox();
			dataRow.setId("datarow" + i % 2);
			dataRow.setPrefWidth(container.getPrefWidth());
			
			Label name = new Label(data.get(i)[0]);
			name.setId("personboxLabel");
			
			Label skritt = new Label(data.get(i)[1]);
			skritt.setId("personboxSkrittLabel");
			
			dataRow.getChildren().addAll(name,skritt);
			content.getChildren().add(dataRow);
			
		}
		

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		container.setFitToWidth(true);
		Customer test = new Customer(1, "95704576", "Hans", "1997-09-02", Gender.M, 182, 80, 20000, "2018-03-03");
		loadCustomerData(test);
	}
	
	public void initialize(URL location, ResourceBundle resources, Customer target) {
		container.setFitToWidth(true);
		loadCustomerData(target);
	}
}