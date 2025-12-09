package com.example.javaprojetfinal.Controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.example.javaprojetfinal.utils.DatabaseConnection;
import java.sql.PreparedStatement;
import org.mindrot.jbcrypt.BCrypt;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private Hyperlink creerCompte;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private Label loginMessageLabel;
    public void loginButtonOnAction(ActionEvent e){
        loginMessageLabel.setText("You Try to login!");
        if(usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false){
            // loginMessageLabel.setText("You Try to login!");
            validateLogin();
        }else {
            loginMessageLabel.setText("Please enter your username and password!");
        }
    }


    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void openDashboard(int userRole) {
        try {
            // Fermez la fenêtre de connexion
            Stage loginStage = (Stage) loginMessageLabel.getScene().getWindow();
            loginStage.close();

            // Charger le fichier FXML du Dashboard
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/javaprojetfinal/view/dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load()); // Laissez JavaFX déterminer la taille

            // Créer la nouvelle Stage pour le Dashboard
            Stage dashboardStage = new Stage();
            dashboardStage.initStyle(StageStyle.UNDECORATED); // Optionnel
            dashboardStage.setScene(scene);
            dashboardStage.show();

            // OPTIONNEL : Si vous voulez passer le rôle au contrôleur du Dashboard,
            // vous aurez besoin de récupérer le contrôleur via fxmlLoader.getController()
            // et d'appeler une méthode d'initialisation sur ce contrôleur.

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            // Gérer l'erreur si le dashboard.fxml n'est pas trouvé
        }
    }

    public void redirectToRegister(ActionEvent event) {
        Stage stage = (Stage) creerCompte.getScene().getWindow();
        stage.close();
        // Appel de la methode qui gère le chargement du FXML
        createAccountForm();
    }
    public void validateLogin(){
        String username = usernameTextField.getText();

        String passwordHash = passwordPasswordField.getText();
        DatabaseConnection connectNow = new DatabaseConnection();
        //Connection connectDB = connectNow.getConnection();

        // Requête préparée pour la SÉCURITÉ : on récupère seulement le hachage du mot de passe
        // et on vérifie que l'utilisateur existe.
        String verifyLogin = "SELECT passwordHash, role FROM users WHERE username = ?";

        // String verifyLogin = "SELECT count(1) FROM users WHERE username = '" + usernameTextField.getText() + "' AND passwordHashed = '" + passwordPasswordField.getText() + "'";

        try (Connection connectDB = connectNow.getConnection();
             PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin)) {

            // 2. Définir le paramètre de la requête préparée (Sécurité contre l'injection SQL)
            preparedStatement.setString(1, username);

            try (ResultSet queryResult = preparedStatement.executeQuery()) {

                if (queryResult.next()) {
                    // L'utilisateur existe, récupérons le hash stocké
                    String storedHashedPassword = queryResult.getString("passwordHash");
                    int userRole = queryResult.getInt("role"); // Récupération du rôle

                    // 3. Vérification du mot de passe avec BCrypt
                    // BCrypt.checkpw(MOT_DE_PASSE_EN_CLAIR, HACHAGE_STOCKÉ)
                    if (BCrypt.checkpw(passwordHash, storedHashedPassword)) {
                        // Redirection en fonction du rôle.
                        if(userRole == 1){ // Redirection pour l'administrateur
                            // Message de bienvenu
                            loginMessageLabel.setText("Welcome Dear Administrator!");
                            // Redirection vers le Dashboard(gestion des rôles)
                            openDashboard(userRole);
                        }else{ // Redirection pour l'utilisateur
                            loginMessageLabel.setText("Welcome Dear User And Now You are a Member !");
                            // Redirection vers le Dashboard(gestion des rôles)
                            openDashboard(userRole);
                        }
                        loginMessageLabel.setText("Connexion réussie !");
                        //createAccountForm(); // Redirection vers l'application principale
                        //openDashboard(userRole);
                    } else {
                        // Ce message couvre l'échec du mot de passe
                        loginMessageLabel.setText("Your password or username is incorrect!.");
                    }
                } else {
                    // L'utilisateur n'existe pas
                    loginMessageLabel.setText("Your password or username is incorrect!.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            loginMessageLabel.setText("Your  password or username is incorrect!");
        }
        // if(usernameTextField.getText().isBlank() == false){}
    }


    public void createAccountForm(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/javaprojetfinal/view/Register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 619, 595);
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(scene);
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
