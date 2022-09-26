package com.example.CONTROLLER.signUpandLoginController;

import com.example.MODEL.MOVIE;
import com.example.VERIFICATION.InputVerification;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.PatternSyntaxException;

public class insertMovieController implements Initializable {
    @FXML
    private Button insertButton;

    @FXML
    private TextField languageField;

    @FXML
    private DatePicker lastDatePicker;

    @FXML
    private TextField lengthField;

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker premiereDatePicker;

    @FXML
    private TextField typeField;

    @FXML
    private Label warningMessage;

    private InputVerification inputVerification = new InputVerification();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warningMessage.textProperty().bind(inputVerification.getErrorMessage());
    }

    @FXML
    private void toAddDatabase(){
        // SEND INPUT VERIFICATION
        // USING RESULT OF VERIFICATION IF TRUE ADD DATABASE MOVIE TABLE
        // IF FALSE USE METHOD WILL CHANGE SETERRORMESSAGE AND RETURN THE METHOD

        boolean resultOfVerification = false;

        try {
             resultOfVerification = inputVerification.insertMovieVerification(new MOVIE(nameField.getText(),
                    Integer.parseInt(lengthField.getText()),
                    new ArrayList<>(Arrays.asList(languageField.getText().split(" "))),
                    new ArrayList<>(Arrays.asList(typeField.getText().split(" "))),
                    premiereDatePicker.getValue().toString(),
                    lastDatePicker.getValue().toString()));
        }catch (PatternSyntaxException e){
            System.err.println("SPLIT EXCEPTION");
            inputVerification.setErrorMessage("FILL ALL BLANKS");
            return;
        }catch (NumberFormatException e){
            System.err.println("PARSE INT EXCEPTION");
            inputVerification.setErrorMessage("ENTER DIGIT IN MOVIE LENGTH");
            return;
        }catch (NullPointerException e ){
            System.err.println("DATE PICKER EXCEPTION");
            inputVerification.setErrorMessage("ENTER CORRECT VALUE TO DATE PICKER");
            return;
        }catch (DateTimeParseException e){
            System.err.println("DATE PICKER EXCEPTION 2");
            inputVerification.setErrorMessage("ENTER CORRECT VALUE TO DATE PICKER");
            return;
        }

        if(!resultOfVerification){
            System.out.println("PROBLEM");
            return;
        }
        System.out.println("ADDED1");

    }
}
