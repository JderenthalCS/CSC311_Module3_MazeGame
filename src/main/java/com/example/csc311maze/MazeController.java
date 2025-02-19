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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;


import java.io.IOException;

public class MazeController {

    @FXML
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

    private CollisionDetection collisionDetection;

    /**
     *  Allows button to switch from Main Menu (mainMenu.fxml) and Maze 1 (mazeView1.fxml)
     * @param event
     * @throws IOException
     */
    @FXML
    void mazeButton1Click(ActionEvent event) throws IOException {
        // Check if "Hard Maze" tab already exists
        for (Tab existingTab : tabPane.getTabs()) {
            if ("Easy Maze".equals(existingTab.getText())) {
                tabPane.getSelectionModel().select(existingTab); // Select existing tab
                return;
            }
        }

        // Load FXML for Maze 2
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mazeView1.fxml"));
        fxmlLoader.setController(this);
        Parent tabContent1 = fxmlLoader.load();

        // Create a new Tab
        Tab tab = new Tab("Easy Maze");
        tab.setContent(tabContent1);

        // Add the tab and select it
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);

        //Create collision detector
        if(tabContent1 instanceof AnchorPane){
            collisionDetection = new CollisionDetection((AnchorPane) tabContent1,roboPane);
            roboPane.requestFocus();
        }

//        // Apply key event filter to the SCENE to prevent arrow key scrolling
        Scene scene = tabPane.getScene();
        if (scene != null) {
            scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode().isArrowKey()) {
                    e.consume(); // Prevents default behavior
                }
            });
        } else {
            System.out.println("Scene is null, cannot add key filter.");
        }



    }

    /**
     * Allows button to switch from Main Menu (mainMenu.fxml) and Maze 2 (mazeView2.fxml)
     * @param event
     * @throws IOException
     */
    @FXML
    void mazeButton2Click(ActionEvent event) throws IOException {
        // Check if "Hard Maze" tab already exists
        for (Tab existingTab : tabPane.getTabs()) {
            if ("Hard Maze".equals(existingTab.getText())) {
                tabPane.getSelectionModel().select(existingTab); // Select existing tab
                return;
            }
        }

        // Load FXML for Maze 2
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mazeView2.fxml"));
        fxmlLoader.setController(this);
        Parent tabContent2 = fxmlLoader.load();

        // Create a new Tab
        Tab tab = new Tab("Hard Maze");
        tab.setContent(tabContent2);

        // Add the tab and select it
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);

        if(tabContent2 instanceof AnchorPane){
            collisionDetection = new CollisionDetection((AnchorPane) tabContent2,roboPane);
            roboPane.requestFocus();
        }

        // Apply key event filter to the SCENE to prevent arrow key scrolling
        Scene scene = tabPane.getScene();
        if (scene != null) {
            scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode().isArrowKey()) {
                    e.consume(); // Prevents default behavior
                }
            });
        } else {
            System.out.println("Scene is null, cannot add key filter.");
        }
    }


    /**
     * Allows 'back' button to switch from maze1 -> main menu
     */
//    @FXML
//    private void backToMainMenu(){
//        tabPane.getSelectionModel().select(mainMenuTab);
//
//        //filter tab pane to not move with key inputs
//        tabPane.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
//            if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) {
//                e.consume(); // Prevents default behavior
//            }
//        });
//
//    }

    @FXML
    public void initialize() {

        roboPane.setFocusTraversable(true);
        roboPane.requestFocus();

        // Set the key event handler on the mazePane (or root node)
        roboPane.setOnKeyPressed(event -> handleKeyPress(event));

    }

    @FXML
    private void handleKeyPress(KeyEvent event) {
        System.out.println("Key pressed: " + event.getCode());
        double changeX = 0;
        double changeY = 0;

        if (event.getCode() == KeyCode.D) {
            changeX = 10;
            //roboPane.setLayoutX(roboPane.getLayoutX() + 10); // Move right by 10 pixels
        } else if (event.getCode() == KeyCode.A) {
            changeX = -10;
            //roboPane.setLayoutX(roboPane.getLayoutX() - 10); // Move left by 10 pixels
        } else if (event.getCode() == KeyCode.W) {
            changeY = -10;
            //roboPane.setLayoutY(roboPane.getLayoutY() - 10); // Move up by 10 pixels
        } else if (event.getCode() == KeyCode.S) {
            changeY = 10;
            //roboPane.setLayoutY(roboPane.getLayoutY() + 10); // Move down by 10 pixels
        }
        //If getCollision returns false, Pane(with ImageView) halts at current position
        if(!(collisionDetection.getCollision(changeX, changeY))) {
            roboPane.setLayoutX(roboPane.getLayoutX() + changeX);
            roboPane.setLayoutY(roboPane.getLayoutY() + changeY);

        }
    }

    public void backToMainMenu(ActionEvent actionEvent) {

    }
}

