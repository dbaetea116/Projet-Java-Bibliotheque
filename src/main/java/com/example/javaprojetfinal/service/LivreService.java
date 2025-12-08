package com.example.javaprojetfinal.service;

import com.example.javaprojetfinal.model.Livre;

public class LivreService {

    public void validerLivre(Livre l) throws Exception {

        if (l.getTitre() == null || l.getTitre().isEmpty()) {
            throw new Exception("Le titre est obligatoire.");
        }

        if (l.getAuteur() == null || l.getAuteur().isEmpty()) {
            throw new Exception("L'auteur est obligatoire.");
        }

        if (l.getStock() < 0) {
            throw new Exception("Le stock ne peut pas être négatif.");
        }

        if (l.getAnnee() < 1900) {
            throw new Exception("L'année est invalide.");
        }
    }
}
