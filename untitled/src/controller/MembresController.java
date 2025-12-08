package controller;

import dao.MembreDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Membre;
import service.MembreService;

public class MembresController {

    @FXML private TableView<Membre> tableMembres;
    @FXML private TableColumn<Membre, String> colNom;
    @FXML private TableColumn<Membre, String> colEmail;
    @FXML private TableColumn<Membre, String> colTel;

    @FXML private TextField tfNom, tfEmail, tfTelephone;

    private MembreDAO membreDAO = new MembreDAO();
    private MembreService membreService = new MembreService();

    @FXML
    public void initialize() {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        rafraichirTable();
    }

    private void rafraichirTable() {
        try {
            tableMembres.getItems().setAll(membreDAO.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ajouterMembre() {
        try {
            Membre m = new Membre();
            m.setNom(tfNom.getText());
            m.setEmail(tfEmail.getText());
            m.setTelephone(tfTelephone.getText());

            membreService.validerMembre(m);
            membreDAO.insert(m);

            rafraichirTable();
            viderChamps();
            alert("Succès", "Membre ajouté !");
        } catch (Exception e) {
            alert("Erreur", e.getMessage());
        }
    }

    @FXML
    private void modifierMembre() {
        try {
            Membre m = tableMembres.getSelectionModel().getSelectedItem();
            if (m == null) {
                alert("Erreur", "Sélectionnez un membre.");
                return;
            }

            m.setNom(tfNom.getText());
            m.setEmail(tfEmail.getText());
            m.setTelephone(tfTelephone.getText());

            membreService.validerMembre(m);
            membreDAO.update(m);

            rafraichirTable();
            alert("Succès", "Membre modifié !");
        } catch (Exception e) {
            alert("Erreur", e.getMessage());
        }
    }

    @FXML
    private void supprimerMembre() {
        try {
            Membre m = tableMembres.getSelectionModel().getSelectedItem();
            if (m == null) {
                alert("Erreur", "Sélectionnez un membre.");
                return;
            }

            membreDAO.delete(m.getId());
            rafraichirTable();

            alert("Succès", "Membre supprimé !");
        } catch (Exception e) {
            alert("Erreur", e.getMessage());
        }
    }

    private void viderChamps() {
        tfNom.clear();
        tfEmail.clear();
        tfTelephone.clear();
    }

    private void alert(String titre, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(titre);
        a.setContentText(msg);
        a.show();
    }
}
