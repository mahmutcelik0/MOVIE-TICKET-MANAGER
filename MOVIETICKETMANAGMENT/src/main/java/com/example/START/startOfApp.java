package com.example.START;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class startOfApp extends Application {
    //I MADE STAE STATIC TO USE IN DIFFERENT METHODS.
    private static Stage programStage = null;

    //EXECUTION OF PROGRAM
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(startOfApp.class.getResource("/com/example/VIEW/signUpandLogin/begin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 425);
        stage.initStyle(StageStyle.UNDECORATED);

        programStage = stage;

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    /**
     * BELOW METHOD IS MAIN CHANGE SCENE METHOD. USING THAT I CAN CHANGE WHOLE SCENES
     * I CREATED THIS BECAUSE CLASS PATHS ARE DIFFERENT.
     * IT TAKES CLASSPATH, WIDTH AND HEGIHT AND LOAD SCENE TO STAGE
     * */
    private static void toChangeScene(String newFxmlclassPath,int width,int height) throws IOException{
        FXMLLoader fxml= new FXMLLoader(startOfApp.class.getResource(newFxmlclassPath));
        Scene scene = new Scene(fxml.load(),width,height);

        programStage.setScene(scene);
        programStage.show();
    }

    /**
     * I USED THIS METHOD TO CHANGE LOGIN PART PAGES. PACKAGE PATH HAS ENTERED AND
     * WIDTH ,HEIGHT IS DEFINED INITIALLY
     *
     * */
    public static void changeBeginningScene(String newFxml){
        try {
            toChangeScene("/com/example/VIEW/signUpandLogin/" +newFxml+".fxml",500,425);
        }catch (IOException e){
            System.out.println("CHANGE BEGINNING SCENE EXCEPTION");
            e.printStackTrace();
        }
    }

    public static void changeToMainParts(String newFxml){
        try {
            toChangeScene("/com/example/VIEW/mainParts/" +newFxml+".fxml",700,716);
        }catch (IOException e){
            System.out.println("CHANGE MAIN PARTS EXCEPTION");
            e.printStackTrace();
        }
    }
}