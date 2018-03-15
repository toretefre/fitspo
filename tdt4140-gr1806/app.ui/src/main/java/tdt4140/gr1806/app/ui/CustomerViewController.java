package tdt4140.gr1806.app.ui;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tdt4140.gr1806.app.core.Trainer;

public class CustomerViewController {
	
	FitspoAppController fitspo;
	
	@FXML ScrollPane customerlist;
	@FXML VBox content;
	
	/**
	 * uses the Trainer Class method getCustomer which returns a ArrayList<ArrayList<String>> of customers and puts it into
	 * the ScrollPane from FitspoApp.fxml. Sets differents ID for styling purposes.
	 * @param customers
	 * @see tdt4140.gr1806.app.core.Trainer.java
	 * OBS: Alle metoder og felt skal være merket med @FXML
	 */
	public void homeLanding(ArrayList<ArrayList<String>> customers) {
		for (int i=0; i<customers.size(); i++) {
			HBox person = new HBox();
			person.setId("personbox" + i % 2);
			person.setPrefWidth(customerlist.getPrefWidth());
			
			Label name = new Label(customers.get(i).get(0));
			name.setId("personboxLabel");
			
			Label skritt = new Label(customers.get(i).get(1));
			skritt.setId("personboxSkrittLabel");
			
			person.getChildren().addAll(name,skritt);
			content.getChildren().add(person);
		}	
	}

	public void init(FitspoAppController fitspoAppController) {
		fitspo = fitspoAppController;
		homeLanding(Trainer.getCustomers());
	}

}
