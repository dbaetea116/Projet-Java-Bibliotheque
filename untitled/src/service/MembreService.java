package service;

import model.Membre;

public class MembreService {

    public void validerMembre(Membre m) throws Exception {

        if (m.getNom() == null || m.getNom().isEmpty()) {
            throw new Exception("Le nom est obligatoire.");
        }

        if (m.getEmail() == null || !m.getEmail().contains("@")) {
            throw new Exception("Email invalide.");
        }

        if (m.getTelephone().length() < 8) {
            throw new Exception("Numéro de téléphone invalide.");
        }
    }
}
