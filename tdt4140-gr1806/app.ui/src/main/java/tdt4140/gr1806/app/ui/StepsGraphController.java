package tdt4140.gr1806.app.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import tdt4140.gr1806.app.core.Customer;
import tdt4140.gr1806.app.core.CustomerRepository;
import tdt4140.gr1806.app.core.DayWithStepsData;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class StepsGraphController {

    private CustomerRepository cr = new CustomerRepository();
    private Customer selectedCustomer = null;
    private Date fromDate = stringToDate("10000101");
    private Date toDate = stringToDate("99991231");

    @FXML ComboBox<Customer> customerComboBox;
    @FXML DatePicker fromDatePicker;
    @FXML DatePicker toDatePicker;
    @FXML Button updateButton;
    @FXML ScrollPane scrollPane;
    @FXML Text totalSteps;

    public void handleUpdate() {

    }

    @FXML
    public void initialize() {

        setComboBox();

        fromDatePicker.setOnAction(e -> {
            LocalDate fromLocalDate = fromDatePicker.getValue();
            toDate = Date.valueOf(fromLocalDate);
        });
        toDatePicker.setOnAction(e -> {
            LocalDate toLocalDate = toDatePicker.getValue();
            toDate = Date.valueOf(toLocalDate);
        });
        updateButton.setOnAction(e -> {
            updateChart();
        });

    }

    private void updateChart() {


        if (selectedCustomer != null) {

            ArrayList<DayWithStepsData> daysWithSteps = cr.getStepsDataOfCustomer(selectedCustomer, fromDate, toDate);
            BarChart<String, Integer> bc = StepsBarChartCreator.getBarChart(daysWithSteps);

            int totalStepsInt = cr.getTotalStepsInDateRange(
                    selectedCustomer, fromDate.toLocalDate(), toDate.toLocalDate());

            totalSteps.setText(Integer.toString(totalStepsInt));

            scrollPane.setContent(bc);
        }


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

    private Date stringToDate(String yyyyMMdd) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        java.util.Date parsed = null;
        try {
            parsed = format.parse(yyyyMMdd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(parsed.getTime());
    }

}
