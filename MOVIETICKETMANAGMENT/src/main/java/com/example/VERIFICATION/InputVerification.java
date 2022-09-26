package com.example.VERIFICATION;

import com.example.MODEL.MOVIE;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.regex.Pattern;

public class InputVerification {


    /**
     * USERNAME VE PASSWORD U KONTROL EDER.
     * 1-> HERHANGI BIRI BOSSA MAP E YAZI VE FALSE EKLER
     * 2-> SIFRE 8 KARAKTERDEN KUCUKSE MAP E YAZI VE FALSE EKLER
     * 3-> PROBLEM YOKSA EMPTY VE TRUE EKLER
     *
     *
     *
     *
     *
     * */

    //THIS VARIABLE WILL BIND IN CONTROLLERS WITH LABELS. TO ENSURE MVC CONSEPT
    // CONTROLLER CHANGE MODEL AND MODEL EFFECTS VIEW PARTS.
    private StringProperty errormsg = new SimpleStringProperty();

    /**
     * BELOW METHOD CONTROLS USERNAME AND PASSWORD (USED IN LOGIN PART)
     *  1 -> IT CONTROLS FILLITY OF FIELDS
     *  2 -> CONTROLS PASSWORD, PASSWORD MUST BE LEAST 8 CHARS.
     *  3 -> ABOVE VARIABLE WILL CLEAR CONDITIONS ARE SUITABLE.
     *
     * */

    public boolean usernameAndPasswordVerification(String username, String password){

        if(username.trim().isEmpty() || password.trim().isEmpty()){
            setErrorMessage("FILL ALL BLANKS");
            return false;
        }

        if(!Pattern.matches("[a-zA-Z0-9]{8,}",password)){
            setErrorMessage("PASSWORD MUST BE \nLEAST 8 CHARACTER");
            return false;
        }

        clearErrorMessage();
        return true;
    }
    /**
     * BELOW METHOD CONTROLS USERNAME AND PASSWORD (USED IN SIGN-UP PART)
     *  1 -> IT CONTROLS FILLITY OF FIELDS
     *  2 -> CONTROLS PASSWORD, PASSWORD MUST BE LEAST 8 CHARS.
     *  3 -> RADIOBUTTON CONTROL. WHEN BOTH OF THEM DIDN'T CHOOSEN, IT WILL RETURN FALSE
     *  4 -> ABOVE VARIABLE WILL CLEAR CONDITIONS ARE SUITABLE.
     * */

    public boolean usernameAndPasswordVerification(String username,String password, Boolean adminChoosen , Boolean userChoosen){
        if(!usernameAndPasswordVerification(username,password)) return false;
        if(!adminChoosen && !userChoosen) {
            setErrorMessage("CHOOSE AN OPTION");
            return false;
        }
        clearErrorMessage();
        return true;
    }

    //TO MAKE BINDING ABOVE METHODS ARE NECESSARY
    public StringProperty getErrorMessage(){
        return errormsg;
    }

    public void setErrorMessage(String newValue){
        errormsg.setValue(newValue);
    }

    public void clearErrorMessage(){
        setErrorMessage("");
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------
    //MAIN PART INSERT MOVIE VERIFICATION

    /**
     * IF ANY PART OF INPUT SECTIONS BLANK RETURNS FALSE
     *
     * */
    public boolean insertMovieVerification(MOVIE movieToControl){
        if(movieToControl.getMovieName().trim().isEmpty() ||
        movieToControl.getMovieLength() == 0||
        movieToControl.getAvaibleLanguages().isEmpty() ||
        movieToControl.getAvaibleTypes().isEmpty() ||
        movieToControl.getMoviePremiereDate().isEmpty() ||
        movieToControl.getMovieLastDate().isEmpty()){
            setErrorMessage("FILL ALL BLANKS!");
            return false;
        }

        if(movieToControl
                .getAvaibleTypes()
                .stream()
                .filter(movieType -> (movieType.equals("2D") || movieType.equals("3D") || movieType.equals("4DX"))).count() != movieToControl.getAvaibleTypes().size()){
            setErrorMessage("ENTER TYPES CORRECTLY");
            return false;
        }

        clearErrorMessage();
        return true;
    }


}
