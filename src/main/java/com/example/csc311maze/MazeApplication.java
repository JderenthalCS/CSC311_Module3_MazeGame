package com.example.csc311maze;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class creates the environment for the "Maze Game: Robot's Demise" application to run
 *
 */
public class MazeApplication extends Application {
    /**
     * This method allows for the environment of the application to be shown
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MazeApplication.class.getResource("mainMenu.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 800, 630); //Creating new scene <(x,y)>
        stage.setTitle("Maze Game: Robot's Demise"); //Set's window title
        stage.setResizable(false); //Removes resizability, as ratios are accurate

        stage.setScene(scene);
        stage.show();
    }
    /**
     * launch() -- Launches program
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}

