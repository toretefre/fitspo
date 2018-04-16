package tdt4140.gr1806.app.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Class for creating a popup window
 * @author ingriddomben
 *
 */

public class PopupWindow {
	
	/**
	 * Method for creating new popup window when trying to delete a customer
	 * @return answer which specifies whether the yes button or no button was clicked
	 * @throws Exception
	 */
	public boolean display() throws Exception {
		
		Stage popupWindow = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupWindow.fxml"));
	    Parent root = loader.load();
		popupWindow.initModality(Modality.APPLICATION_MODAL);
		popupWindow.setAlwaysOnTop(true);
		
		popupWindow.setScene(new Scene(root));
		popupWindow.showAndWait();
		
		PopupController contrl = (PopupController)loader.getController();
		boolean answer = contrl.answer;
		
		return answer;

	}
}
