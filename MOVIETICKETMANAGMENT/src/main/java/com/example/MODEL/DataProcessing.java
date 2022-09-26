package com.example.MODEL;

import com.example.START.startOfApp;

public class DataProcessing {

    /**
     * IN MODEL PACKAGE I'VE USED TO CLOSE PROGRAM THIS METHOD
     * */
    public void closeProgram(){
        System.exit(0);
    }

    /**
     * I'VE USED THIS METHOD TO REACH CHANGESCENE PARTS.
     * I THINK, IN BEST PRACTICE REACH STARTCLASS IS WRONG
     * AND THIS METHOD HAVE TO USE INT LOGIN AND SIGN UP PARTS
     * MAIN PARTS METHOD WILL BE DIFFERENT LITTLE BIT BECAUSE CLASS PATHS ARE DIFFERENT
     * */
    public void changeScene(String ID){
        startOfApp.changeBeginningScene(ID);
    }

    public void loadMainScene(String ID){
        startOfApp.changeToMainParts(ID);
    }
}
