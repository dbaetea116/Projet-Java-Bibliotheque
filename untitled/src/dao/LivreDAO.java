package dao;

import model.Livre;
import utils.Connexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {

    Connection con = Connexion.getConnexion();

    public void insert(Livre l) throws Exception {
        String sql = "INSERT INTO livre (titre, auteur, annee, categorie, stock) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, l.getTitre());
        ps.setString(2, l.getAuteur());
        ps.setInt(3, l.getAnnee());
        ps.setString(4, l.getCategorie());
        ps.setInt(5, l.getStock());

        ps.executeUpdate();
    }

    public List<Livre> getAll() throws Exception {
        List<Livre> liste = new ArrayList<>();

        String sql = "SELECT * FROM livre";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            Livre l = new Livre(
                    rs.getInt("id"),
                    rs.getString("titre"),
                    rs.getString("auteur"),
                    rs.getInt("annee"),
                    rs.getString("categorie"),
                    rs.getInt("stock")
            );
            liste.add(l);
        }

        return liste;
    }
}
