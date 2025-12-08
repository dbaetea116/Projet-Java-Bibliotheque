package com.example.javaprojetfinal.dao;

import com.example.javaprojetfinal.model.Livre;
import com.example.javaprojetfinal.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {

    // Assurez-vous que cette connexion est ouverte et gérée correctement.
    // L'utilisation d'un try-with-resources est souvent préférable dans les méthodes DAO.
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();


    // 1. MÉTHODE INSERT (AJOUTER) - Déjà présente
    public void insert(Livre l) throws Exception {
        String sql = "INSERT INTO livre (titre, auteur, annee, categorie, stock) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connectDB.prepareStatement(sql)) {
            ps.setString(1, l.getTitre());
            ps.setString(2, l.getAuteur());
            ps.setInt(3, l.getAnnee());
            ps.setString(4, l.getCategorie());
            ps.setInt(5, l.getStock());
            ps.executeUpdate();
        }
    }

    // 2. MÉTHODE GET ALL (RÉCUPÉRER TOUT) - Déjà présente
    public List<Livre> getAll() throws Exception {
        List<Livre> liste = new ArrayList<>();
        String sql = "SELECT * FROM livre";

        // Utilisation de try-with-resources pour Statements et ResultSets
        try (Statement st = connectDB.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

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
        }
        return liste;
    }

    // 3. NOUVELLE MÉTHODE : UPDATE (MODIFIER)
    // Cette méthode corrige l'erreur "Cannot resolve method 'update'"
    public void update(Livre l) throws Exception {
        // La requête UPDATE doit prendre tous les champs modifiables
        // et utiliser l'ID pour identifier la ligne à changer.
        String sql = "UPDATE livre SET titre=?, auteur=?, annee=?, categorie=?, stock=? WHERE id=?";

        try (PreparedStatement ps = connectDB.prepareStatement(sql)) {
            ps.setString(1, l.getTitre());
            ps.setString(2, l.getAuteur());
            ps.setInt(3, l.getAnnee());
            ps.setString(4, l.getCategorie());
            ps.setInt(5, l.getStock());

            // L'ID est toujours le dernier paramètre dans la clause WHERE
            ps.setInt(6, l.getId());

            ps.executeUpdate();
        }
    }

    // 4. NOUVELLE MÉTHODE : DELETE (SUPPRIMER)
    // Cette méthode corrige l'erreur "Cannot resolve method 'delete'"
    public void delete(int id) throws Exception {
        // La requête DELETE utilise l'ID pour spécifier la ligne à supprimer.
        String sql = "DELETE FROM livre WHERE id=?";

        try (PreparedStatement ps = connectDB.prepareStatement(sql)) {
            ps.setInt(1, id); // L'ID du livre à supprimer
            ps.executeUpdate();
        }
    }
    // Methode getById()
    public Livre getById(int idLivre) throws Exception {
        Livre livre = null;

        // 1. Requête SQL pour sélectionner un seul livre par son ID
        String sql = "SELECT * FROM livre WHERE id = ?";

        // Utilisez la connexion existante (connectDB)
        try (PreparedStatement ps = connectDB.prepareStatement(sql)) {
            ps.setInt(1, idLivre); // Définir l'ID dans la requête

            try (ResultSet rs = ps.executeQuery()) {
                // 2. Vérifier si un résultat a été trouvé
                if (rs.next()) {
                    // 3. Créer et initialiser l'objet Livre
                    livre = new Livre(
                            rs.getInt("id"),
                            rs.getString("titre"),
                            rs.getString("auteur"),
                            rs.getInt("annee"),
                            rs.getString("categorie"),
                            rs.getInt("stock")
                    );
                }
            }
        }
        return livre; // Retourne le livre ou null
    }
}