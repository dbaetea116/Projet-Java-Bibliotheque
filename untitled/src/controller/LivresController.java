package controller;

import dao.LivreDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Livre;
import service.LivreService;

public class LivresController {

    @FXML private TableView<Livre> tableLivres;
    @FXML private TableColumn<Livre, String> colTitre;
    @FXML private TableColumn<Livre, String> colAuteur;
    @FXML private TableColumn<Livre, Integer> colStock;

    @FXML private TextField tfTitre, tfAuteur, tfAnnee, tfCategorie, tfStock;

    private LivreDAO livreDAO = new LivreDAO();
    private LivreService livreService = new LivreService();

    @FXML
    public void initialize() {
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colAuteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        rafraichirTable();
    }

    private void rafraichirTable() {
        try {
            tableLivres.getItems().setAll(livreDAO.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ajouterLivre() {
        try {
            Livre l = new Livre();
            l.setTitre(tfTitre.getText());
            l.setAuteur(tfAuteur.getText());
            l.setAnnee(Integer.parseInt(tfAnnee.getText()));
            l.setCategorie(tfCategorie.getText());
            l.setStock(Integer.parseInt(tfStock.getText()));

            livreService.validerLivre(l);
            livreDAO.insert(l);

            rafraichirTable();
            viderChamps();
            alert("Succès", "Livre ajouté !");
        } catch (Exception e) {
            alert("Erreur", e.getMessage());
        }
    }

    @FXML
    private void modifierLivre() {
        try {
            Livre l = tableLivres.getSelectionModel().getSelectedItem();
            if (l == null) {
                alert("Erreur", "Sélectionnez un livre.");
                return;
            }

            l.setTitre(tfTitre.getText());
            l.setAuteur(tfAuteur.getText());
            l.setAnnee(Integer.parseInt(tfAnnee.getText()));
            l.setCategorie(tfCategorie.getText());
            l.setStock(Integer.parseInt(tfStock.getText()));

            livreService.validerLivre(l);
            livreDAO.update(l);

            rafraichirTable();
            alert("Succès", "Livre modifié !");
        } catch (Exception e) {
            alert("Erreur", e.getMessage());
        }
    }

    @FXML
    private void supprimerLivre() {
        try {
            Livre l = tableLivres.getSelectionModel().getSelectedItem();
            if (l == null) {
                alert("Erreur", "Sélectionnez un livre.");
                return;
            }

            livreDAO.delete(l.getId());
            rafraichirTable();
            alert("Succès", "Livre supprimé !");
        } catch (Exception e) {
            alert("Erreur", e.getMessage());
        }
    }

    private void viderChamps() {
        tfTitre.clear();
        tfAuteur.clear();
        tfAnnee.clear();
        tfCategorie.clear();
        tfStock.clear();
    }

    private void alert(String titre, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(titre);
        a.setContentText(msg);
        a.show();
    }
}
