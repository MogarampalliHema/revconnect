package com.revconnect.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521/orclpdb";

    private static final String USERNAME = "revconnect";
    private static final String PASSWORD = "revconnect123";

    public static Connection getConnection() {
        Connection con = null;
        try {
            // Updated to modern Oracle Driver
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
        return con;
    }
}
