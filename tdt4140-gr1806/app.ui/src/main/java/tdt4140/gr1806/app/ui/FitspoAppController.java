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

public class FitspoAppController implements Initializable {
	@FXML ScrollPane container;
	@FXML VBox content;
	
	
	public void homeLanding(ArrayList<ArrayList<String>> customers) {
		
		for (int i=0; i<customers.size(); i++) {
			HBox person = new HBox();
			person.setId("personbox" + i % 2);
			person.setPrefWidth(container.getPrefWidth());
			
			Label name = new Label(customers.get(i).get(0));
			name.setId("personboxLabel");
			name.setPrefWidth(200);
			
			Label skritt = new Label(customers.get(i).get(1));
			skritt.setId("personboxLabel");
			person.getChildren().addAll(name,skritt);
			person.setPrefHeight(50);
			person.setPrefWidth(380);
			content.getChildren().add(person);
		}	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Trainer t = new Trainer();
		homeLanding(t.getCustomers());
		homeLanding(t.getCustomers());
		homeLanding(t.getCustomers());
	}
}