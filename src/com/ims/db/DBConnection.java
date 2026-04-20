package com.ims.db;
import java.sql.*;
public class DBConnection {

    private static final String URL="jdbc:mysql://127.0.0.1:3306/ims_db";
    private static final String USER="root";
    private static final String PASSWORD="mysql@900";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}



    

