package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {
        final String URL = System.getenv("DB_URL");
        final String USER = System.getenv("DB_USER");
        final String PASSWORD = System.getenv("DB_PASSWORD");
        
        Connection con = null;
        
        try {
        	Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
}
