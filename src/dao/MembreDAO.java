package dao;

import model.Membre;
import utils.Connexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDAO {

    Connection con = Connexion.getConnexion();

    public void insert(Membre m) throws Exception {
        String sql = "INSERT INTO membre (nom, email, telephone) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, m.getNom());
        ps.setString(2, m.getEmail());
        ps.setString(3, m.getTelephone());
        ps.executeUpdate();
    }

    public void update(Membre m) throws Exception {
        String sql = "UPDATE membre SET nom=?, email=?, telephone=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, m.getNom());
        ps.setString(2, m.getEmail());
        ps.setString(3, m.getTelephone());
        ps.setInt(4, m.getId());
        ps.executeUpdate();
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM membre WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public List<Membre> getAll() throws Exception {
        List<Membre> liste = new ArrayList<>();
        String sql = "SELECT * FROM membre";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            Membre m = new Membre(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("email"),
                    rs.getString("telephone")
            );
            liste.add(m);
        }
        return liste;
    }
}
