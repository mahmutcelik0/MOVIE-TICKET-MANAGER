package com.example.CONTROLLER.signUpandLoginController;

import com.example.DTO.DatabaseConnection;
import com.example.MODEL.DataProcessing;
import com.example.VERIFICATION.InputVerification;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class signUpController implements Initializable {

    // INSTANTIATION OF NODES FROM FXML
    @FXML
    private Label warningMessage;
    @FXML
    private RadioButton adminRadio,userRadio;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;


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
     * 3 -> BUTTON IS (SIGN UP). IT WILL CHECK INPUT AND DATABASE AND AFTER PERSON WILL
     *  ADD DATABASE
     * */
    @FXML
    private void buttonClicked(MouseEvent e){
        Button clickedButton = (Button) e.getSource(); //TO GET BUTTON ID

        DataProcessing dp = new DataProcessing(); // CLOSE OR CHANGE PROGRAM

        if(clickedButton.getId().equals("closeButton")) dp.closeProgram();

        else if(clickedButton.getId().equals("begin")) dp.changeScene(clickedButton.getId());

        //IF CLICKED BUTTON IS SIGN UP BELOW PART WILL EXECUTE AND
        //FIRSTLY, CONTROL INPUTS
        //SECONDLY, CONTROL DATABASE EXIST SITUATION
        else {
            //INPUT AVAILABILITY CHECK
            if(!inputVerification.usernameAndPasswordVerification(usernameField.getText(),passwordField.getText(),adminRadio.isSelected(),userRadio.isSelected())) return;

            //DATABASE CHECK
            // IF INPUTS ARE EXIST IN DATABASE IT WILL TRUE OTHERWISE WILL FALSE
            boolean resultFromDatabase = databaseConnection.adminOrUserSignUpControl(usernameField.getText(),passwordField.getText());

            if(resultFromDatabase){
                inputVerification.setErrorMessage("THIS USER \nALREADY REGISTERED"); // CHANGE MODEL VARIABLE
                return;
            }

            else{
                //SIGN UP NEW USER
                databaseConnection.signUpNewUser(usernameField.getText(),passwordField.getText(),adminRadio.isSelected(),userRadio.isSelected());
            }


            inputVerification.setErrorMessage("SIGN UP COMPLETED \nSUCCESSFULLY");
        }

    }
}
