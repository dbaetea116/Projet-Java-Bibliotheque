package com.example.javaproject;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    private final String databaseName = "bibliotheque";
    private final String databaseUser = "root";
    private final String databasePassword = "";
    private final String url = "jdbc:mysql://localhost:3306/" + databaseName;


    public Connection getConnection() {
        Connection connection = null;
        try{
            // Class.forName("com.mysql.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
            connection = databaseLink;
            System.out.println("Connection Done");

        } catch (Exception e) {
            e.printStackTrace();// Retourne la raison exacte de l'erreur
            e.getCause();
            connection = null; // S'assurer qu'elle retourne null ou gère l'exception
        }
        return connection;
    }
}
