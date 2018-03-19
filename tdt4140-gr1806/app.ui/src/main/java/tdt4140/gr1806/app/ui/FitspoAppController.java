package tdt4140.gr1806.app.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	
	/**
	 * uses the Trainer Class method getCustomer which returns a ArrayList<ArrayList<String>> of customers and puts it into
	 * the ScrollPane from FitspoApp.fxml. Sets differents ID for styling purposes.
	 * @param customers
	 * @see tdt4140.gr1806.app.core.Trainer.java
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Start");
		menuBarController.init(this);
		customerViewController.init(this);
	}
}