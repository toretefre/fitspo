package tdt4140.gr1806.app.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tdt4140.gr1806.app.core.Customer;
import tdt4140.gr1806.app.core.CustomerRepository;
import tdt4140.gr1806.app.core.Goal;

public class CustomerViewController {
	
	FitspoAppController fitspo;
	@FXML FitspoAppController_trainer oneCust;
	@FXML ScrollPane customerlist;
	@FXML VBox content;
	private CustomerRepository customerRepository = new CustomerRepository();
	
	/**
	 * uses the CustomerRepository Class method findAllCustomers which returns a ArrayList<Customer> and puts it into
	 * the ScrollPane from FitspoApp.fxml. Sets differents ID for styling purposes.
	 * @param customers
	 * @see tdt4140.gr1806.app.core.Trainer.java
	 * OBS: Alle metoder og felt skal være merket med @FXML
	 */
	public void homeLanding(ArrayList<Customer> customers) {
		
		for (int i=0; i<customers.size(); i++) {
			Customer currentCust = customers.get(i);
			HBox person = new HBox();
			person.setId("personbox" + i % 2);
			person.setPrefWidth(customerlist.getPrefWidth());
			person.setOnMouseClicked((event) -> {
				Parent root;
				
				try {
					// Essential line for getting goal into personview:
					Goal goal = customerRepository.createGoalFromCustomerId(currentCust.getId());
					FXMLLoader loader = new FXMLLoader(getClass().getResource("FitspoApp_trainer.fxml"));
					root = loader.load();
					FitspoAppController_trainer controller = (FitspoAppController_trainer)loader.getController();
					controller.init(currentCust, goal);
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					stage.setScene(new Scene(root));
					stage.show();	
				} catch (Exception e) {
					e.printStackTrace();
				} 
			});
			Label name = new Label(currentCust.getName());
			name.setId("personboxLabel");
			
			Label steps = new Label(Integer.toString(this.customerRepository.getTotalSteps(currentCust)));
			steps.setId("personboxSkrittLabel");
			
			person.getChildren().addAll(name, steps);
			content.getChildren().add(person);
		}	
	}
	
	@FXML 
	public void CustomerStage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("FitspoApp_trainer.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}

	public void init(FitspoAppController fitspoAppController) {
		this.fitspo = fitspoAppController;
		//customerlist.setFitToHeight(true);
		customerlist.setFitToWidth(true);
		homeLanding(this.customerRepository.findAllCustomers());
	}
}
