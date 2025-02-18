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

public class MazeController {

    public Pane roboPane = new Pane();

    @FXML
    private TabPane tabPane = new TabPane();


    @FXML
    private Tab mainMenuTab;

    @FXML
    private Tab maze1Tab;

    @FXML
    private Tab maze2Tab;

    @FXML
    public ImageView m2Robot;

    @FXML
    public ImageView m1Robot;


    @FXML
    private Button mazeButton1;

    @FXML
    private Button mazeButton2;

    /**
     *  Allows button to switch from Main Menu (mainMenu.fxml) and Maze 1 (mazeView1.fxml)
     * @param event
     * @throws IOException
     */
    @FXML
    void mazeButton1Click(ActionEvent event) throws IOException {
//        tabPane.getSelectionModel().select(1);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mazeView1.fxml"));
        Parent tabContent = fxmlLoader.load();


        Tab tab = new Tab("Easy Maze"); // Create a new Tab
        tab.setContent(tabContent); // Set the loaded FXML as the content

        tabPane.getTabs().add(tab);

        //filter tab pane to not move with key inputs
        tabPane.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) {
                e.consume(); // Prevents default behavior
            }
        });



    }

    /**
     * Allows button to switch from Main Menu (mainMenu.fxml) and Maze 2 (mazeView2.fxml)
     * @param event
     * @throws IOException
     */
    @FXML
    void mazeButton2Click(ActionEvent event) throws IOException {
//        tabPane.getSelectionModel().select(2);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mazeView2.fxml"));
        Parent tabContent = fxmlLoader.load();

        Tab tab = new Tab("Hard Maze"); // Create a new Tab
        tab.setContent(tabContent); // Set the loaded FXML as the content

        tabPane.getTabs().add(tab);

        //filter tab pane to not move with key inputs
        tabPane.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) {
                e.consume(); // Prevents default behavior
            }
        });
    }


    /**
     * Allows 'back' button to switch from maze1 -> main menu
     */
    @FXML
    private void backToMainMenu(){
        tabPane.getSelectionModel().select(mainMenuTab);

        //filter tab pane to not move with key inputs
        tabPane.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) {
                e.consume(); // Prevents default behavior
            }
        });

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

    public void backToMainMenu(ActionEvent actionEvent) {

    }
}

