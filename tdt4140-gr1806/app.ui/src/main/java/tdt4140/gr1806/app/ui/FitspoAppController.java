package tdt4140.gr1806.app.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tdt4140.gr1806.app.ui.MenuController;
import tdt4140.gr1806.app.ui.FitspoAppController_trainer;


/**
 * FitspoAppController Class uses the CustomerRepository Class to represent an updated list of customers and their total steps.
 * Controlling the FitspoApp.fxml
 * @author Magnus
 * @version 1.0
 * @see tdt4140.gr1806.app.core.Trainer.java
 * @see tdt4140.gr1806.app.ui.FitspoApp.fxml
 */

public class FitspoAppController implements Initializable {
	ResourceBundle resources;
	
	// All different controllers
	@FXML private CustomerViewController customerViewController;
	@FXML private MenuController menuBarController; 
	@FXML private FitspoAppController_trainer fitspoAppController_trainer;
	@FXML private MessageViewController messageViewController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.resources = resources;
		System.out.println("Start");
		menuBarController.init(this);
		customerViewController.init(this);
	}
}