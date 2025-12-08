module com.example.javaprojetfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires transitive mysql.connector.j;
    requires javafx.graphics;

    // Ouvrez le package qui contient les contrôleurs et les fichiers FXML
    opens com.example.javaprojetfinal to javafx.fxml;
    opens com.example.javaprojetfinal.Controller to javafx.fxml;
    opens com.example.javaprojetfinal.dao to javafx.fxml;
    opens com.example.javaprojetfinal.model to javafx.fxml;
    opens com.example.javaprojetfinal.utils to javafx.fxml;
    opens com.example.javaprojetfinal.service to javafx.fxml;
    // Exportez le package qui contient la classe Application
    exports  com.example.javaprojetfinal.Controller;
}