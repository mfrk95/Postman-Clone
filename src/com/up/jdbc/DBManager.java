package com.up.jdbc;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    static final String DB_URL = "jdbc:mysql://localhost:3306/labo1";
    static final String USER = "root";
    static final String PASS = "123456";

    public static Connection connect() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error connecting to the database","Error",JOptionPane.ERROR_MESSAGE);
        }
        return c;
    }

}
