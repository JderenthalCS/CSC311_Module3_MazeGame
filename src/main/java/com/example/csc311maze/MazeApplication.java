package com.example.csc311maze;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class MazeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MazeApplication.class.getResource("mazeView.fxml"));
        Parent root = fxmlLoader.load();

        


        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(MazeApplication.class.getResource("mazeStyle.css").toExternalForm());


        stage.setTitle("Maze Game: Robot's Demise");
        stage.setResizable(false);


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}