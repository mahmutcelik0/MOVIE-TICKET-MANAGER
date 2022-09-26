package com.example.CONTROLLER.signUpandLoginController;

import com.example.DTO.DatabaseConnection;
import com.example.MODEL.DataProcessing;
import com.example.VERIFICATION.InputVerification;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class loginController implements Initializable {

    // INSTANTIATION OF NODES FROM FXML
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label warningMessage;

    private InputVerification inputVerification = new InputVerification();
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    /**
     *  CONTROLLER CLASSES DON'T HAVE A CONSTRUCTOR. THEREFORE I USED INITIALIZABLE INTERFACE TO
     *  MAKE BINDING BEGINNING OF USING SIGN UP FXML PAGE
     *  --- WARNINGMESSAGE IS A LABEL FROM FXML AND IN INPUTVERIFICATION CLASS I HAVE A
     *  VARIABLE THAT WILL BIND WITH LABEL. IN MVC MODEL HAVE TO CHANGE VIEW PARTS.
     *  ! CONTROLLER SHOULDN'T CHANGE VIEW PARTS IN BEST PRACTICES.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warningMessage.textProperty().bind(inputVerification.getErrorMessage());
    }

    /**
     * THERE ARE 3 BUTTONS IN SIGN UP FXML. USING THEIR ID'S I MADE SOME CONDITIONAL STATEMENTS
     * 1 -> BUTTON IS (X). PROGRAM WILL CLOSE
     * 2 -> BUTTON IS (<-). PAGE WILL GO BACK TO BEGIN FXML PAGE
     * 3 -> BUTTON IS (LOGIN). IT WILL CHECK INPUT AND DATABASE AND AFTER ADMIN OR USER PANEL WILL LOAD
     * */
    @FXML
    private void buttonClicked(MouseEvent e){
        Button clickedButton = (Button) e.getSource(); //TO GET BUTTON ID

        DataProcessing dp  = new DataProcessing(); // CLOSE OR CHANGE PROGRAM

        if(clickedButton.getId().equals("closeButton")) dp.closeProgram();

        else if(clickedButton.getId().equals("begin")) dp.changeScene(clickedButton.getId());

            //IF CLICKED BUTTON IS LOGIN BELOW PART WILL EXECUTE AND
            //FIRSTLY, CONTROL INPUTS
            //SECONDLY, CONTROL DATABASE EXIST SITUATION
        else{
            //INPUT AVAILABILITY CHECK
            if(!inputVerification.usernameAndPasswordVerification(usernameField.getText(),passwordField.getText())) return;

            //DATABASE CHECK
            // IF INPUTS ARE NOT EXIST IN DATABASE RESULTFROMDATABASE WILL -> 0
            // IF INPUTS ARE EXIST AND REGISTER AS AN ADMIN RESULTFROMDATABASE WILL -> 1
            // IF INPUTS ARE EXIST AND REGISTER AS AN USER RESULTFROMDATABASE WILL -> 2

            int resultFromDatabase = databaseConnection.adminOrUserLoginControl(usernameField.getText(),passwordField.getText());

            if(resultFromDatabase == 0) {
                inputVerification.setErrorMessage("THIS USER IS NOT REGISTERED");
                return;
            }
            //OPENS ADMIN PANEL
            else if(resultFromDatabase == 1){
                System.out.println("OPENING ADMIN PANEL");
                dp.loadMainScene(clickedButton.getId());
                return;
            }
            //OPENS USER PANEL
            else if(resultFromDatabase == 2){
                System.out.println("OPENING USER PANEL");
                //USER PANELE GIRIS OLACAK EN ALTTAKI SILINECEK
                return;
            }
            else{
                System.exit(0);
            }

            dp.changeScene(clickedButton.getId()); //SILINECEK
        }
    }
}
