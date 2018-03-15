package tdt4140.gr1806.app.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import tdt4140.gr1806.app.ui.FitspoAppController;

/**
 * OBS: Alle metoder og felt skal være merket med @FXML
 * @author Magnus
 *
 */

public class MenuController {
	
	private FitspoAppController fitspo;
	
	@FXML Button homeButton;
	FXMLLoader loader;
	
	@FXML public void navigateHome(ActionEvent event) throws Exception {
		
		
		
		fitspo.centerContent.getChildren().clear();
		fitspo.centerContent.getChildren().add(loader.load(getClass().getResource("CustomerView.fxml")));
       }

	public void init(FitspoAppController fitspoAppController) {
		fitspo = fitspoAppController;
	};
	
}

