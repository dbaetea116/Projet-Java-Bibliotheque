package service;

import model.Emprunt;
import model.Livre;

import java.time.LocalDate;

public class EmpruntService {

    public Emprunt emprunter(Livre livre, int idMembre) throws Exception {

        if (livre.getStock() <= 0) {
            throw new Exception("Le livre n'est pas disponible.");
        }

        Emprunt e = new Emprunt();
        e.setIdLivre(livre.getId());
        e.setIdMembre(idMembre);
        e.setDateEmprunt(LocalDate.now());
        e.setDateRetourPrevue(LocalDate.now().plusDays(15));

        // Diminuer stock
        livre.setStock(livre.getStock() - 1);

        return e;
    }

    public void retourner(Emprunt e, Livre livre) {

        e.setDateRetourReelle(LocalDate.now());
        livre.setStock(livre.getStock() + 1);
    }
}
