package com.example.javaprojetfinal.Controller;

import com.example.javaprojetfinal.dao.EmpruntDAO;
import com.example.javaprojetfinal.dao.LivreDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.javaprojetfinal.model.Emprunt;
import com.example.javaprojetfinal.model.Livre;
import com.example.javaprojetfinal.service.EmpruntService;

public class EmpruntsController {

    @FXML private TableView<Emprunt> tableEmprunts;
    @FXML private TableColumn<Emprunt, Integer> colLivre;
    @FXML private TableColumn<Emprunt, Integer> colMembre;
    @FXML private TableColumn<Emprunt, String> colDate;
    @FXML private TableColumn<Emprunt, String> colRetour;
    @FXML private TableColumn<Emprunt, String> colRetourReel;

    @FXML private TextField tfIdLivre, tfIdMembre;

    private final EmpruntDAO empruntDAO = new EmpruntDAO();
    private final LivreDAO livreDAO = new LivreDAO();
    private final EmpruntService empruntService = new EmpruntService();

    @FXML
    public void initialize() {
        colLivre.setCellValueFactory(new PropertyValueFactory<>("idLivre"));
        colMembre.setCellValueFactory(new PropertyValueFactory<>("idMembre"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateEmprunt"));
        colRetour.setCellValueFactory(new PropertyValueFactory<>("dateRetourPrevue"));
        colRetourReel.setCellValueFactory(new PropertyValueFactory<>("dateRetourReelle"));

        rafraichirTable();
    }

    private void rafraichirTable() {
        try {
            tableEmprunts.getItems().setAll(empruntDAO.getEnCours());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void emprunterLivre() {
        try {
            int idLivre = Integer.parseInt(tfIdLivre.getText());
            int idMembre = Integer.parseInt(tfIdMembre.getText());

            Livre livre = livreDAO.getById(idLivre);

            if (livre == null) {
                alert("Erreur", "Livre introuvable.");
                return;
            }

            Emprunt e = empruntService.emprunter(livre, idMembre);

            empruntDAO.insert(e);
            livreDAO.update(livre);

            rafraichirTable();
            alert("Succès", "Livre emprunté !");
        } catch (Exception e) {
            alert("Erreur", e.getMessage());
        }
    }

    @FXML
    private void retournerLivre() {
        try {
            Emprunt e = tableEmprunts.getSelectionModel().getSelectedItem();
            if (e == null) {
                alert("Erreur", "Sélectionnez un emprunt.");
                return;
            }

            Livre livre = livreDAO.getById(e.getIdLivre());

            empruntService.retourner(e, livre);

            empruntDAO.update(e);
            livreDAO.update(livre);

            rafraichirTable();
            alert("Succès", "Livre retourné !");
        } catch (Exception ex) {
            alert("Erreur", ex.getMessage());
        }
    }

    private void alert(String titre, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(titre);
        a.setContentText(msg);
        a.show();
    }
}
