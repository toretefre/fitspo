package tdt4140.gr1806.app.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import tdt4140.gr1806.app.ui.FitspoAppController;

public class MenuController {
	
	private FitspoAppController fitspo;
	
	@FXML Button homeButton;
	
	public void navigateHome() {
		homeButton.setOnAction((event) -> {System.out.println("Funker!!!!!!");});
	}
	
	public void init(FitspoAppController fitspoAppController) {
		fitspo = fitspoAppController;
		
	}
}
