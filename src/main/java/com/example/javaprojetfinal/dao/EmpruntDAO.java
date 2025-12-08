package com.example.javaprojetfinal.dao;

import com.example.javaprojetfinal.model.Emprunt;
import com.example.javaprojetfinal.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAO {

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();


    public void insert(Emprunt e) throws Exception {
        String sql = "INSERT INTO emprunt (idLivre, idMembre, dateEmprunt, dateRetourPrevue) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connectDB.prepareStatement(sql);
        ps.setInt(1, e.getIdLivre());
        ps.setInt(2, e.getIdMembre());
        ps.setDate(3, Date.valueOf(e.getDateEmprunt()));
        ps.setDate(4, Date.valueOf(e.getDateRetourPrevue()));
        ps.executeUpdate();
    }

    public void update(Emprunt e) throws Exception {
        String sql = "UPDATE emprunt SET dateRetourReelle=? WHERE id=?";
        PreparedStatement ps = connectDB.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(e.getDateRetourReelle()));
        ps.setInt(2, e.getId());
        ps.executeUpdate();
    }

    public List<Emprunt> getEnCours() throws Exception {
        List<Emprunt> liste = new ArrayList<>();
        String sql = "SELECT * FROM emprunt WHERE dateRetourReelle IS NULL";
        Statement st = connectDB.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            Emprunt e = new Emprunt(
                    rs.getInt("id"),
                    rs.getInt("idLivre"),
                    rs.getInt("idMembre"),
                    rs.getDate("dateEmprunt").toLocalDate(),
                    rs.getDate("dateRetourPrevue").toLocalDate(),
                    rs.getDate("dateRetourReelle") == null ? null : rs.getDate("dateRetourReelle").toLocalDate()
            );
            liste.add(e);
        }
        return liste;
    }
}

