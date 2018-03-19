package tdt4140.gr1806.app.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FitspoApp extends Application {
	Stage window;
	Scene startScene;

    @Override
    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FitspoApp.fxml"));
        startScene = new Scene(root);
        window.setScene(startScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
