package com.example.csc311maze;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class MazeController {

    @FXML
    private Button mazeButton1;

    @FXML
    private Button mazeButton2;




    @FXML
    void mazeButton1Click(ActionEvent event) throws IOException {
        maze1Window();


    }

    @FXML
    void mazeButton2Click(ActionEvent event) throws IOException {
        maze2Window();

    }

    //method to make a new window
    @FXML
    void maze1Window() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Maze");

        FXMLLoader fxmlLoader = new FXMLLoader(MazeApplication.class.getResource("maze1Window.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.show();



    }
    @FXML
    void maze2Window() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Maze");

        FXMLLoader fxmlLoader = new FXMLLoader(MazeApplication.class.getResource("maze2Window.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.show();



    }

    @FXML
    void onRightArrowClick(ActionEvent event) throws IOException {


    }
}
