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
	@FXML Button somethingButton;
	FXMLLoader loader;
	
	@FXML public void navigateHome(ActionEvent event) throws Exception {
		
		fitspo.centerContent.getChildren().clear();
		fitspo.centerContent.getChildren().add(loader.load(getClass().getResource("CustomerView.fxml")));
		fitspo.mainStage.getChildren().add(fitspo.centerContent.getChildren().add(loader.load(getClass().getResource("CustomerView.fxml"))));
       }

	@FXML public void something(ActionEvent event) throws Exception {
		System.out.println("Something cool happend here");
		
	}
	
	public void init(FitspoAppController fitspoAppController) {
		fitspo = fitspoAppController;
	};
	
}

