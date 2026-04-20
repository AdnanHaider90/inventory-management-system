package com.ims.db;
import java.sql.*;
public class TestConnection {
    public static void main(String[] args) {
        try(Connection con= DBConnection.getConnection()){
            if(con!=null){
                System.out.println("Connection successful!");
                System.out.println("DB: "+ con.getMetaData().getDatabaseProductName());
            }
        }
        catch(SQLException e){
            System.out.println("Connection failed! "+ e.getMessage());
        }
    }
    
}
