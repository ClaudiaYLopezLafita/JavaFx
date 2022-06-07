package com.example.project.javafxp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.lang.*;


public class ConnectionDB {
    private static Connection con=null;

    public static Connection getConnection() {

        try {
            if (con == null) {
                con = DriverManager.getConnection("jdbc:mysql://localhost:336/classicmodels?user=root&password=root");
                System.out.println("Conexion Existosa \n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (con);
    }
}
