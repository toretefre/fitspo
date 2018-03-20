package tdt4140.gr1806.app.core;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    public static Connection conn;

    /**
    *
    * Connects to the database.
    */
    public static Connection connect(){
        try {
            String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/matiasre_FitsPo?useSSL=false";
            conn = DriverManager.getConnection(url, "matiasre_gruppe6", "123safari");

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return conn;
    }
    
}