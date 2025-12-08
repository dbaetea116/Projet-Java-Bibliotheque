package service;

import model.Utilisateur;

public class UtilisateurService {

    public void validerUtilisateur(Utilisateur u) throws Exception {

        if (u.getUsername() == null || u.getUsername().isEmpty()) {
            throw new Exception("Username obligatoire.");
        }

        if (u.getPasswordHash() == null || u.getPasswordHash().isEmpty()) {
            throw new Exception("Mot de passe obligatoire.");
        }

        if (!u.getRole().equals("ADMIN") && !u.getRole().equals("USER")) {
            throw new Exception("Rôle invalide.");
        }
    }
}
