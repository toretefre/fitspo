package tdt4140.gr1806.app.ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.fabric.xmlrpc.base.Data;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tdt4140.gr1806.app.core.Customer;
import tdt4140.gr1806.app.core.CustomerRepository;
/**
 * FitspoAppController Class uses the CustomerRepository Class to represent an updated list of customers and their total steps.
 * Controlling the FitspoApp_trainer.fxml
 * @author Magnus
 * Modified by Tore
 * @version 1.0
 * @see tdt4140.gr1806.app.core.Trainer.java
 * @see tdt4140.gr1806.app.ui.FitspoApp.fxml
 */
public class FitspoAppController_trainer {
	FitspoAppController mainController;
	
	@FXML private ScrollPane container;
	@FXML private VBox content;
	@FXML private Label userName;
	@FXML private DatePicker from;
	@FXML private DatePicker to;
	ArrayList<String[]> data = new ArrayList<>();
	private CustomerRepository customerRepository = new CustomerRepository();
	private Customer cus;
	
	private void loadCustomerData(Customer selectedPerson) {
		cus = selectedPerson;
		userName.setText(selectedPerson.getName());
		
		data.add(new String[]{"Telephone", selectedPerson.getTelephone()});
		data.add(new String[]{"Birthdate", selectedPerson.getBirthDate()});
		data.add(new String[]{"Gender", selectedPerson.getGender()});
		data.add(new String[]{"Height", Integer.toString(selectedPerson.getHeight())});
		data.add(new String[]{"Weight", Double.toString(selectedPerson.getWeight())});
		data.add(new String[]{"Steps", Integer.toString(this.customerRepository.getTotalSteps(selectedPerson))});
		data.add(new String[]{"Registration Date", selectedPerson.getDateRegistered()});
		
		for (int i = 0; i < data.size(); i++) {
			HBox dataRow = new HBox();
			dataRow.setId("datarow" + i % 2);
			if(i == 5) { //Easy access to change steps in updateCustomerSteps
				dataRow.setId("steps");
			}
			dataRow.setPrefWidth(container.getPrefWidth());
			Label name = new Label(data.get(i)[0]);
			name.setId("personboxLabel");
			
			Label skritt = new Label(data.get(i)[1]);
			skritt.setId("personboxSkrittLabel");
			
			dataRow.getChildren().addAll(name,skritt);
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
		System.out.println("Working");
		String[] update = new String[] {"Steps", "44"};
		LocalDate fromDate = from.getValue();
		LocalDate toDate = to.getValue();
		
		if(fromDate != null && toDate != null) {
			int steps = CustomerRepository.getTotalStepsInDateRange(cus, fromDate, toDate);
			//data.set(5, new String[] {"Steps", Integer.toString(steps)} );
			HBox dataRow = new HBox();
			dataRow.setId("1");
			dataRow.setPrefWidth(container.getPrefWidth());
			Label name = new Label("Steps");
			name.setId("personboxLabel");
			Label step = new Label(Integer.toString(steps));
			step.setId("personboxSkrittLabel");
			dataRow.getChildren().addAll(name, step);
			content.getChildren().set(5, dataRow);
		}
		
		
	}
	
	@FXML public void something(ActionEvent event) throws Exception {
		System.out.println("Something cool happend here");
	}
	
	public void init(Customer target) {
		System.out.println("Init called in fitspoappcontroller_trainer");
		container.setFitToWidth(true);
		loadCustomerData(target);
	}
}