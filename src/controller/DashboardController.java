package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import utils.Navigation;

public class DashboardController {

    @FXML
    private void openUsers(javafx.event.ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Navigation.goTo(stage, "/fxml/Users.fxml");
    }

    @FXML
    private void openBooks(javafx.event.ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Navigation.goTo(stage, "/fxml/Books.fxml");
    }

    @FXML
    private void openLoans(javafx.event.ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Navigation.goTo(stage, "/fxml/Loans.fxml");
    }
}
