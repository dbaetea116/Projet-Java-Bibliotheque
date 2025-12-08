package com.example.javaprojetfinal.dao;

import com.example.javaprojetfinal.model.Utilisateur;
import com.example.javaprojetfinal.utils.DatabaseConnection;
import java.sql.*;

public class UtilisateurDAO {

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();


    public Utilisateur getByUsername(String username) throws Exception {
        String sql = "SELECT * FROM utilisateur WHERE username=?";
        PreparedStatement ps = connectDB.prepareStatement(sql);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Utilisateur(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("passwordHash"),
                    rs.getString("role")
            );
        }
        return null; // pas trouvé
    }
}
