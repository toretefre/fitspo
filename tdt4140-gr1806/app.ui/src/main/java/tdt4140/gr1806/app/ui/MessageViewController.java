package tdt4140.gr1806.app.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import tdt4140.gr1806.app.core.Customer;
import tdt4140.gr1806.app.core.CustomerRepository;

public class MessageViewController {
	FitspoAppController fitspo;
	CustomerRepository cr = new CustomerRepository();
	@FXML private ChoiceBox<String> customerChoiceBox;
	
	
	public void changeCenterContent(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MessageView.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public void updateDropDown(ArrayList<Customer> customers) {
		System.out.println("hei");
		
		
		for(Customer cus : customers) {
			customerChoiceBox.getItems().add(cus.getName());
		}
			
	}
	
	
	public void init(FitspoAppController fitspoAppController) {
		fitspo = fitspoAppController;
		System.out.println("hallo");
		updateDropDown(this.cr.findAllCustomers());
	};
	

}
