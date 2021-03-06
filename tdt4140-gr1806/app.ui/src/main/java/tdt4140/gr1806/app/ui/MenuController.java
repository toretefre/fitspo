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
	
	//this attribute is never used. remove?
	private FitspoAppController fitspo;
	
	@FXML Button homeButton;
	@FXML Button goalsButton;
	FXMLLoader loader;
	
	@FXML 
	public void homeStage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("FitspoApp.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	@FXML 
	public void messageStage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MessageView.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	@FXML public void goalStage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("GoalView.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML public void graphStage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("StepsGraph.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public void init(FitspoAppController fitspoAppController) {
		fitspo = fitspoAppController;
	};
}

