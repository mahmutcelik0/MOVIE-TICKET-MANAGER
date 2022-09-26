module com.example.movieticketmanagment {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;

    exports com.example.START;
    opens com.example.START to javafx.fxml;
    exports com.example.CONTROLLER.signUpandLoginController;
    opens com.example.CONTROLLER.signUpandLoginController to javafx.fxml;
}