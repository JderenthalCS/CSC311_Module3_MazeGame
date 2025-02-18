package com.example.csc311maze;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;


import java.io.IOException;
import java.security.Key;

public class MazeController {

    public Pane roboPane = new Pane();

    @FXML
    public TabPane tabPane;

    @FXML
    private Tab mainMenuTab; //Main Menu

    @FXML
    private Tab maze1Tab; //Maze 1

    @FXML
    private Tab maze2Tab; //Maze 2

    @FXML
    private Button backButton1; //Maze1 Back Button

    @FXML
    private Button backButton2; //Maze2 Back Button

    @FXML
    public ImageView m2Robot;

    @FXML
    public ImageView m1Robot;


    @FXML
    private Button mazeButton1;

    @FXML
    private Button mazeButton2;

    /**
     * Allows button to switch from Main Menu (mainMenu.fxml) and Maze 1 (mazeView1.fxml)
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void mazeButton1Click(ActionEvent event) throws IOException {
        tabPane.getSelectionModel().select(1);

    }

    /**
     * Allows button to switch from Main Menu (mainMenu.fxml) and Maze 2 (mazeView2.fxml)
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void mazeButton2Click(ActionEvent event) throws IOException {
        tabPane.getSelectionModel().select(2);
    }


    //method to make a new window
    @FXML
    void maze1Window() throws IOException { //makes sa new window for the easy maze with fxml file
        Stage stage = new Stage();
        stage.setTitle("Easy Maze");

        FXMLLoader fxmlLoader = new FXMLLoader(MazeApplication.class.getResource("mazeView1.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void maze2Window() throws IOException { //makes sa new window for the hard maze with fxml file
        Stage stage = new Stage();
        stage.setTitle("Hard Maze");

        FXMLLoader fxmlLoader = new FXMLLoader(MazeApplication.class.getResource("maze2Window.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.show();


    }

    @FXML
    public void initialize() {

        // Set the key event handler on the mazePane (or root node)
        roboPane.setOnKeyPressed(event -> handleKeyPress(event));

        // Ensure pane is focusable to receive key events
        roboPane.setFocusTraversable(true);
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.RIGHT) {
            roboPane.setLayoutX(roboPane.getLayoutX() + 10); // Move right by 10 pixels
        } else if (event.getCode() == KeyCode.LEFT) {
            roboPane.setLayoutX(roboPane.getLayoutX() - 10); // Move left by 10 pixels
        } else if (event.getCode() == KeyCode.UP) {
            roboPane.setLayoutY(roboPane.getLayoutY() - 10); // Move up by 10 pixels
        } else if (event.getCode() == KeyCode.DOWN) {
            roboPane.setLayoutY(roboPane.getLayoutY() + 10); // Move down by 10 pixels
        }

    }

    /**
     * Allows 'back' button to switch from maze1/2 -> main menu
     */
    public void backToMainMenu(ActionEvent actionEvent) {
        // Try manually looking up the TabPane
        TabPane parentTabPane = (TabPane) ((Button) actionEvent.getSource()).getScene().lookup("#tabPane");

        if (parentTabPane != null) {
            parentTabPane.getSelectionModel().select(0); // Selects first tab (Main Menu)
            System.out.println("✅ Switched to Main Menu using manual lookup.");
        } else {
            System.out.println("❌ ERROR: Could not find TabPane dynamically.");
        }
    }

}


