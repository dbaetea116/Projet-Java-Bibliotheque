package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {

    private static Connection con;

    public static Connection getConnexion() {
        try {
            if (con == null) {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:8889/bibliotheque",
                        "root",      // ton username MySQL
                        "root"          // ton mot de passe MySQL
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
