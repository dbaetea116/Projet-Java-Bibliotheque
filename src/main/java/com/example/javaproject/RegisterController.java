package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.mindrot.jbcrypt.BCrypt;
import javafx.scene.Node;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController {
    @FXML
    private Button closeButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Hyperlink connectezCompte;

    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerButtonOnAction(ActionEvent event) {
        if (setPasswordField.getText().equals(confirmPasswordField.getText())){
            registerUser();
            confirmPasswordLabel.setText("You are set");
        }else {
            confirmPasswordLabel.setText("Passwords does not match");
        }
    }

    public void redirectToLogin(ActionEvent event){
        try {
            // Fermer la fenêtre d'inscription actuelle
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            // Charger la page de connexion (login.fxml)
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 438);
            // Créer une nouvelle fenêtre pour le login
            Stage loginStage = new Stage();
            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.setScene(scene);
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void registerUser(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String passwordHash = setPasswordField.getText();

        // Hachage du mot de passe avec BCrypt
        String passwordHashed = BCrypt.hashpw(passwordHash, BCrypt.gensalt());

        String insertFields = "INSERT INTO USERS(firstname, lastname, username, email, passwordHash) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + username + "','" + email + "','" + passwordHashed + "')";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            registrationMessageLabel.setText(" User has been registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }

}


