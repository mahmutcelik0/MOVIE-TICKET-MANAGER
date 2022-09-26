package com.example.CONTROLLER.signUpandLoginController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adminController {
    @FXML
    private Pane paneToImportFxml;

    @FXML
    private void buttonClicked(MouseEvent e){
        Button clickedButton = (Button) e.getSource();
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/com/example/VIEW/mainParts/adminBelowParts/"+clickedButton.getId()+".fxml"));

            AnchorPane newPane = fxml.load();

            paneToImportFxml.getChildren().addAll(newPane);

        }catch (IOException m){
            m.printStackTrace();
        }
    }
}
