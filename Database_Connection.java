package com.revstox.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_Connection
{

    static
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Explicitly load the driver
        }
        catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
    }
    private static final String URL = "jdbc:mysql://localhost:3306/rev1stox";
    private static final String USER = "root";
    private static final String PASSWORD = "password";



    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}
