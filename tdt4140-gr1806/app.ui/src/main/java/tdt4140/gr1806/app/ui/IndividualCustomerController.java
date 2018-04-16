package tdt4140.gr1806.app.ui;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import tdt4140.gr1806.app.core.Customer;
import tdt4140.gr1806.app.core.CustomerRepository;
import tdt4140.gr1806.app.core.Goal;

/**
 * FitspoAppController Class uses the CustomerRepository Class to represent an updated list of customers and their total steps.
 * Controlling the IndividualCustomer.fxml
 * @author Magnus
 * Modified by Tore and Aasmund
 * @version 1.0
 * @see tdt4140.gr1806.app.core.Trainer.java
 * @see tdt4140.gr1806.app.ui.FitspoApp.fxml
 */
public class IndividualCustomerController {
	FitspoAppController mainController;
	
	@FXML private ScrollPane container;
	@FXML private VBox content;
	@FXML private Label userName;
	@FXML private DatePicker from;
	@FXML private DatePicker to;
	@FXML private Button deleteButton;
	ArrayList<String[]> data = new ArrayList<>();
	private CustomerRepository customerRepository = new CustomerRepository();

	private Customer cus;

	private void loadCustomerData(Customer selectedPerson, Goal goal) {
		cus = selectedPerson;

		userName.setText(selectedPerson.getName());
		
		data.add(new String[]{"Telephone", selectedPerson.getTelephone()});
		data.add(new String[]{"Birthdate", selectedPerson.getBirthDate()});
		data.add(new String[]{"Gender", selectedPerson.getGender()});
		data.add(new String[]{"Height", Integer.toString(selectedPerson.getHeight())});
		data.add(new String[]{"Weight", Double.toString(selectedPerson.getWeight())});
		data.add(new String[]{"Steps", Integer.toString(this.customerRepository.getTotalSteps(selectedPerson))});
		data.add(new String[]{"Registration Date", selectedPerson.getDateRegistered()});
		
		// If goals: Show them in view:
		if (goal != null) {
			data.add(new String[]{"Goal steps", Integer.toString(goal.getGoal())});
			data.add(new String[]{"Goal deadline", goal.getDeadLineEnd()});
			data.add(new String[]{"Steps left", String.valueOf((goal.getGoal()) - this.customerRepository.getTotalSteps(selectedPerson))});			
		}
		for (int i = 0; i < data.size(); i++) {
			HBox dataRow = new HBox();
			dataRow.setId("datarow" + i % 2);
			if(i == 5) { //Easy access to change steps in updateCustomerSteps
				dataRow.setId("steps");
			}
			dataRow.setPrefWidth(container.getPrefWidth());
			Label name = new Label(data.get(i)[0]);
			name.setId("personboxLabel");
			
			Label steps = new Label(data.get(i)[1]);
			steps.setId("personboxStepsLabel");
			
			dataRow.getChildren().addAll(name,steps);
			content.getChildren().add(dataRow);
		}
	}
	
	/**
	 * Uses CustomerRepository.getTotalStepsInDateRange to find steps in date set by the user if both from and to are set.
	 * Changes the HBox which specifies the steps registred on user.
	 * @param event
	 * @throws IOException
	 */
	
	@FXML public void updateCustomerSteps(ActionEvent event) throws IOException {
		// From and To is Datepicker-objects in the view
		// This gets the sql.Date from them
		Date fromDate = Date.valueOf(from.getValue().toString());
		Date toDate = Date.valueOf(to.getValue().toString());
		
		if(fromDate != null && toDate != null) {
			int steps = customerRepository.getTotalStepsInDateRange(cus, fromDate, toDate);
			HBox dataRow = new HBox();
			dataRow.setId("1");
			dataRow.setPrefWidth(container.getPrefWidth());
			Label name = new Label("Steps");
			name.setId("personboxLabel");
			Label step = new Label(Integer.toString(steps));
			step.setId("personboxStepsLabel");
			dataRow.getChildren().addAll(name, step);
			content.getChildren().set(5, dataRow);
		}
	}
	
	/**
	 * method for handling the event of click on delete button. Opens a popup window and deletes customer if yes button is clicked. 
	 * Returns to main view.
	 *
	 * @param event 
	 * @throws Exception
	 */
	
	@FXML
	public void onButtonClick(ActionEvent event) throws Exception {
		PopupWindow popup = new PopupWindow();
		boolean confirm = popup.display();
		
		if (confirm) {
			customerRepository.deleteCustomer(cus);
			Parent root = FXMLLoader.load(getClass().getResource("FitspoApp.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		}
	}


	public void init(Customer target, Goal goal) {
		container.setFitToWidth(true);
		loadCustomerData(target, goal);
	}
}