package tdt4140.gr1806.app.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import tdt4140.gr1806.app.core.Customer;
import tdt4140.gr1806.app.core.CustomerRepository;

import java.util.ArrayList;

public class StepsGraphController {

    private CustomerRepository cr = new CustomerRepository();
    private Customer selectedCustomer;

    @FXML ComboBox<Customer> customerComboBox;

    public void handleUpdate() {

    }

    @FXML
    public void initialize() {

        setComboBox();




    }

    private void updateChart() {


    }

    private void setComboBox() {

        ObservableList<Customer> observableCustomerList = FXCollections.observableArrayList(cr.findAllCustomers());
        customerComboBox.setItems(observableCustomerList);
        customerComboBox.setConverter(new StringConverter<Customer>() {
            @Override
            public String toString(Customer customer) {
                return customer.getName();
            }
            @Override
            public Customer fromString(String strin) {
                return null;
            }
        });

        customerComboBox.setOnAction(e -> {
            this.selectedCustomer = customerComboBox.getValue();
            updateChart();
        });
    }

}
