package com.BAGG20230814.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://localhost:8080;databaseName=BAGGTareaDB";
        String username = "root";
        String password = "";

        return DriverManager.getConnection(url, username, password);
    }
}
