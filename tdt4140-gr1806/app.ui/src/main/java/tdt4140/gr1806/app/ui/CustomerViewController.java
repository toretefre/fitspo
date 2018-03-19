package tdt4140.gr1806.app.ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tdt4140.gr1806.app.core.Customer;
import tdt4140.gr1806.app.core.Trainer;

public class CustomerViewController {
	
	FitspoAppController fitspo;
	@FXML FitspoAppController_trainer oneCust;
	@FXML ScrollPane customerlist;
	@FXML VBox content;
	
	/**
	 * uses the Trainer Class method getCustomer which returns a ArrayList<Customer> and puts it into
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
				Parent root = null;
				try {
					 FXMLLoader loader = new FXMLLoader(getClass().getResource("FitspoApp_trainer.fxml"));
					 root = loader.load();
					 FitspoAppController_trainer controller = (FitspoAppController_trainer)loader.getController();
					 controller.init(currentCust);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            Stage stage = new Stage();
	            stage.setTitle("My New Stage Title");
	            stage.setScene(new Scene(root, 450, 800));
	            stage.show();
			});
			
			Label name = new Label(currentCust.getName());
			name.setId("personboxLabel");
			
			Label skritt = new Label(Integer.toString(currentCust.getSteps()));
			skritt.setId("personboxSkrittLabel");
			
			person.getChildren().addAll(name,skritt);
			content.getChildren().add(person);
		}	
	}

	public void init(FitspoAppController fitspoAppController) {
		System.out.println("Init called in customerview");
		System.out.println(oneCust);
		this.fitspo = fitspoAppController;
		//customerlist.setFitToHeight(true);
		customerlist.setFitToWidth(true);
		homeLanding(Trainer.getCustomers());
	}

}
