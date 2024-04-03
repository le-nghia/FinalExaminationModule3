package com.cg.module3.finalexamination_module3.DBContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBContext {
    private static final String URL = "jdbc:mysql://localhost:3306/Module3";
    private static final String username = "root";
    private static final String password = "Nghia.it97@LN";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("===> Connections success!");
            return DriverManager.getConnection(URL, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Failed to establish connection to database:" + e.getMessage());
        }
    }
    public static void closed(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
