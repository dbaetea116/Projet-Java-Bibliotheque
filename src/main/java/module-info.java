module com.example.javaproject {
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

    opens com.example.javaproject to javafx.fxml;
    exports com.example.javaproject;
}