package com.example.javaprojetfinal.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox; // <-- Importez la classe du conteneur VBox
import javafx.stage.Stage;
import java.io.IOException;

public class DashboardController {

    // 1. Déclarez le conteneur racine avec fx:id="rootPane"
    @FXML
    private VBox rootPane;

    @FXML
    public void goLivres() { // <-- Changé de private à public
        changerPage("/com/example/javaprojetfinal/view/livres.fxml");
    }

    @FXML
    public void goMembres() { // <-- Changé de private à public
        changerPage("/com/example/javaprojetfinal/view/membres.fxml");
    }

    @FXML
    public void goEmprunts() { // <-- Changé de private à public
        changerPage("/com/example/javaprojetfinal/view/emprunts.fxml");
    }

    @FXML
    public void logout() { // <-- Changé de private à public
        changerPage("/com/example/javaprojetfinal/view/login.fxml");
    }

    private void changerPage(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();

            // CORRECTION DE L'ERREUR DE STAGE (NullPointerException anticipé)
            // Utilisez le rootPane déjà chargé pour obtenir la Stage actuelle.
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
            // Vous pouvez afficher une alerte ici en cas de FXML introuvable
            // new Alert(Alert.AlertType.ERROR, "Erreur de chargement de page: " + e.getMessage()).show();
        }
    }

    // SUPPRIMER : La méthode tableStage() n'est plus nécessaire et cause l'erreur
    // private Parent tableStage() throws Exception { ... }
}