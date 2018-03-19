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
 * OBS: Alle metoder og felt skal være merket med @FXML
 * @author Magnus
 *
 */

public class MenuController {
	
	private FitspoAppController fitspo;
	
	@FXML Button homeButton;
	@FXML Button somethingButton;
	FXMLLoader loader;
	
	@FXML public void homeStage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("FitspoApp.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML public void something(ActionEvent event) throws Exception {
		System.out.println("Something cool happend here");
	}
	
	public void init(FitspoAppController fitspoAppController) {
		System.out.println("init called in menucontroller");
		fitspo = fitspoAppController;
	};
	
	
}

