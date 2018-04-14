package tdt4140.gr1806.app.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * 
 * @author ingriddomben
 * Controller class for handling events in popup window
 */


public class PopupController {
	
	boolean answer;
	@FXML Button yesButton;
	@FXML Button noButton;
	
	public PopupController() {
	}
	
	@FXML
	public void yesButtonClick(ActionEvent event) {
		answer = true;
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
		
	@FXML
	public void noButtonClick(ActionEvent event) {
		answer = false;
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
		
}
