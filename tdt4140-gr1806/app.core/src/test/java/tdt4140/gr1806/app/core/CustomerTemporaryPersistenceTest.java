package tdt4140.gr1806.app.core;


import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;

import static junit.framework.TestCase.assertTrue;

// Will transfer the method(s) from this class to CustomerPersistenceTest when that class is ready.
public class CustomerTemporaryPersistenceTest {

    @Test
    public void getTotalStepsInDateRange() {

        // Add user with steps on 5 days, test different cases

        Connection conn = ConnectionManager.connect();

        Integer id = null;
        Integer stepsExpected = 100 + 1500 + 2000 + 10;

        LocalDate startDate = LocalDate.of(2018, 2, 2);
        LocalDate endDate = LocalDate.of(2018, 2, 7);

        try {
            id = Customer.addCustomer("Per Johny Testersen");
            Customer.addSteps(id, 500, "2018-02-1");
            Customer.addSteps(id, 100, "2018-02-2");
            Customer.addSteps(id, 1500, "2018-02-4");
            Customer.addSteps(id, 2000, "2018-02-6");
            Customer.addSteps(id, 10, "2018-02-7");
            Customer.addSteps(id, 3000, "2018-02-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Could not add customer or steps to that customer");
        }

        int stepsReturned = Customer.getTotalStepsInDateRange(id, startDate, endDate);
        assertTrue("Did not get expected steps in range", stepsExpected == stepsReturned);


        // Now delete customer and steps
        try {
            String sqlDeleteCustomer = "delete from Customer where id=?";
            PreparedStatement pstmtDeleteCustomer = conn.prepareStatement(sqlDeleteCustomer);
            pstmtDeleteCustomer.setInt(1, id);
            pstmtDeleteCustomer.execute();

            String sqlDeleteSteps = "delete from StepsOnDay where customerId=?";
            PreparedStatement pstmtDeleteSteps = conn.prepareStatement(sqlDeleteSteps);
            pstmtDeleteSteps.setInt(1, id);
            pstmtDeleteSteps.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Could not delete customer and steps, please delete customer with id " + Integer.toString(id));
        }

    }

}
