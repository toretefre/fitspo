package tdt4140.gr1806.app.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tdt4140.gr1806.app.ui.FitspoAppController;

/**
 * OBS: All methods and attributes must be marked with @FXML
 * @author Magnus
 *
 */

public class MenuController {
	
	private FitspoAppController fitspo;
	
	@FXML Button homeButton;
	@FXML Button goalsButton;
	FXMLLoader loader;
	
	@FXML public void homeStage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("FitspoApp.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML public void something(ActionEvent event) throws Exception {
		System.out.println("Goals called");
	}
	
	public void init(FitspoAppController fitspoAppController) {
		fitspo = fitspoAppController;
	};
	
	
}

