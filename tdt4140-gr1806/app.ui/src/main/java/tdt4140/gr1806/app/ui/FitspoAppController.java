package tdt4140.gr1806.app.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FitspoAppController implements Initializable {
	@FXML ScrollPane container;
	@FXML VBox content;
	
	
	public void homeLanding() {
		HBox person = new HBox();
		for (int i=0; i<2; i++) {
			
			Label name = new Label("mann"+i);
			Label skritt = new Label("100"+i);
			person.getChildren().addAll(name,skritt);
			
		}
		content.getChildren().add(person);
		container.setContent(container);
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeLanding();
	}
}