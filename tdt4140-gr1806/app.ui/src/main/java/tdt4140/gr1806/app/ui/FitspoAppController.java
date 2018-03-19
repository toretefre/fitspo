package tdt4140.gr1806.app.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tdt4140.gr1806.app.core.Trainer;
import tdt4140.gr1806.app.ui.MenuController;
/**
 * FitspoAppController Class use the Trainer Class to represent an updated list of customers and their total steps.
 * Controlling the FitspoApp.fxml
 * @author Magnus
 * @version 1.0
 * @see tdt4140.gr1806.app.core.Trainer.java
 * @see tdt4140.gr1806.app.ui.FitspoApp.fxml
 */
public class FitspoAppController implements Initializable {
	// All different controllers
	@FXML private CustomerViewController customerViewController;
	@FXML private MenuController menuBarController; 
	
	@FXML BorderPane mainStage;
	@FXML VBox centerContent;

	
	@FXML public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Start");
		menuBarController.init(this);
		customerViewController.init(this);
	}
	

	
	
}