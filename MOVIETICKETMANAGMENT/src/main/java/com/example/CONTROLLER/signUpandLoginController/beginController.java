package com.example.CONTROLLER.signUpandLoginController;

import com.example.MODEL.DataProcessing;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class beginController {

    /**
     * IT HAS 3 BUTTONS
     * 1 -> CLOSE BUTTON
     * 2 -> SIGN UP BUTTON
     * 3 -> LOGIN BUTTON
     * */
    @FXML
    private void buttonClicked(MouseEvent e){
        Button clickedButton = (Button) e.getSource();//TO GET ID

        DataProcessing dp = new DataProcessing();
        if(clickedButton.getId().equals("closeButton")) dp.closeProgram(); // IF BUTTON ID IS CLOSEBUTTON PROGRAM WILL CLOSE
        else dp.changeScene(clickedButton.getId()); // OTHERWISE USING CHANGE SCENE, ID WILL SEND CHANGESCENE METHOD

    }

}
