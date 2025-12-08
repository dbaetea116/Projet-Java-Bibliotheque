package controller;

import dao.UtilisateurDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Utilisateur;

public class LoginController {

    @FXML private TextField tfUsername;
    @FXML private PasswordField tfPassword;

    private UtilisateurDAO userDAO = new UtilisateurDAO();

    @FXML
    private void login() {
        try {
            String username = tfUsername.getText();
            String pw = tfPassword.getText();

            Utilisateur u = userDAO.getByUsername(username);

            if (u == null) {
                alert("Erreur", "Utilisateur introuvable");
                return;
            }

            if (!pw.equals(u.getPasswordHash())) {
                alert("Erreur", "Mot de passe incorrect");
                return;
            }

            // TODO: Change scene → dashboard.fxml

            alert("Succès", "Connexion OK !");
        } catch (Exception e) {
            alert("Erreur", e.getMessage());
        }
    }

    private void alert(String titre, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(titre);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.show();
    }
}
