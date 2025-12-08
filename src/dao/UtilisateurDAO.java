package dao;

import model.Utilisateur;
import utils.Connexion;
import java.sql.*;

public class UtilisateurDAO {

    Connection con = Connexion.getConnexion();

    public Utilisateur getByUsername(String username) throws Exception {
        String sql = "SELECT * FROM utilisateur WHERE username=?";
        PreparedStatement ps = con.prepareStatement(sql);
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
