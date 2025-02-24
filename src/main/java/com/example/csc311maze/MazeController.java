package com.example.csc311maze;

import javafx.application.Platform;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.image.PixelReader;

import javafx.scene.image.Image;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MazeController {


    @FXML
    public Pane roboPane = new Pane();

    @FXML
    public Pane jeepWrangler = new Pane();

    @FXML
    private TabPane tabPane = new TabPane();

    @FXML
    private Tab mainMenuTab;

    @FXML
    private ImageView mazeImageView;
    @FXML
    private ImageView mazeImageView2;

    /**
     * Allows button to switch from Main Menu (mainMenu.fxml) and Maze 1 (mazeView1.fxml)
     *
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


        // Load FXML for Maze 1
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mazeView1.fxml"));
        fxmlLoader.setController(this);
        Parent tabContent1 = fxmlLoader.load();

        // Create a new Tab
        Tab tab = new Tab("Easy Maze");
        tab.setContent(tabContent1);

        // Add the tab and select it
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);

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
     *
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
     * when car button 1 is pressed makes a new tab with easy maze for car
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void onCarMaze1ButtonClick(ActionEvent event) throws IOException {
        for (Tab existingTab : tabPane.getTabs()) {
            if ("Easy Car Maze".equals(existingTab.getText())) {
                tabPane.getSelectionModel().select(existingTab); // Select existing tab
                return;
            }
        }

        // Load FXML for Car Maze 1
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CarMazeView1.fxml"));
        fxmlLoader.setController(this);
        Parent carContent = fxmlLoader.load();

        // Create a new Tab
        Tab tab = new Tab("Easy Car Maze");
        tab.setContent(carContent);

        // Add the tab and select it
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);

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
     * when car button 2 is pressed makes a new tab with hard maze for car
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void onCarMaze2ButtonClick(ActionEvent event) throws IOException {
        for (Tab existingTab : tabPane.getTabs()) {
            if ("Hard Car Maze".equals(existingTab.getText())) {
                tabPane.getSelectionModel().select(existingTab); // Select existing tab
                return;
            }
        }

        // Load FXML for Car Maze 1
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CarMazeView2.fxml"));
        fxmlLoader.setController(this);
        Parent carContent = fxmlLoader.load();

        // Create a new Tab
        Tab tab = new Tab("Hard Car Maze");
        tab.setContent(carContent);

        // Add the tab and select it
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);

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
     * Allows 'back' button to switch from maze1 -> main menu
     */
    @FXML
    private void backToMainMenu() {
        tabPane.getSelectionModel().select(mainMenuTab);

        //filter tab pane to not move with key inputs
        tabPane.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) {
                e.consume(); // Prevents default behavior
            }
        });

    }

    /**
     * set the car model up to receive input
     */
    @FXML
    public void initializeCar() {

        jeepWrangler.setFocusTraversable(true);
        jeepWrangler.requestFocus();

        // Set the key event handler on the mazePane (or root node)
        jeepWrangler.setOnKeyPressed(event -> handleCarKeyPress(event));

    }

    /**
     * uses wasd key input to move the car pane around
     *
     * @param event
     */
    private void handleCarKeyPress(KeyEvent event) {
        System.out.println("Key pressed: " + event.getCode());

        // Movement increment
        double changeX = 0;
        double changeY = 0;

        // Determine movement direction based on key press
        if (event.getCode() == KeyCode.D) {
            changeX = 10; // Move right
        } else if (event.getCode() == KeyCode.A) {
            changeX = -10; // Move left
        } else if (event.getCode() == KeyCode.W) {
            changeY = -10; // Move up
        } else if (event.getCode() == KeyCode.S) {
            changeY = 10; // Move down
        }

        // Calculate the new position based on the current position and movement
        double newX = jeepWrangler.getLayoutX() + changeX;
        double newY = jeepWrangler.getLayoutY() + changeY;

        // Check for collisions before moving
        if (!isColliding(newX, newY, jeepWrangler.getWidth(), jeepWrangler.getHeight())) {
            // If no collision, update the Wrangler's position
            jeepWrangler.setLayoutX(newX);
            jeepWrangler.setLayoutY(newY);

            // Optionally update rotation to match the movement direction
            if (event.getCode() == KeyCode.D) {
                jeepWrangler.setRotate(270); // Rotate to the right
            } else if (event.getCode() == KeyCode.A) {
                jeepWrangler.setRotate(90); // Rotate to the left
            } else if (event.getCode() == KeyCode.W) {
                jeepWrangler.setRotate(180); // Rotate upwards
            } else if (event.getCode() == KeyCode.S) {
                jeepWrangler.setRotate(0); // Rotate downwards
            }
        } else {
            System.out.println("Collision detected, cannot move.");
        }
    }

    /**
     * set the robot model up to receive input
     */
    @FXML
    public void initialize() {

        roboPane.setFocusTraversable(true);
        roboPane.requestFocus();

        // Set the key event handler on the mazePane (or root node)
        roboPane.setOnKeyPressed(event -> handleKeyPress(event));

    }

    /**
     * allows robot to receive keyboard inputs
     *
     * @param event
     */
    private void handleKeyPress(KeyEvent event) {
        System.out.println("Key pressed: " + event.getCode());
        double changeX = 0;
        double changeY = 0;

        if (event.getCode() == KeyCode.D) {
            changeX = 10; // Move right
        } else if (event.getCode() == KeyCode.A) {
            changeX = -10; // Move left
        } else if (event.getCode() == KeyCode.W) {
            changeY = -10; // Move up
        } else if (event.getCode() == KeyCode.S) {
            changeY = 10; // Move down
        }

//new position
        double newX = roboPane.getLayoutX() + changeX;
        double newY = roboPane.getLayoutY() + changeY;

// Check collision at multiple points (edges & center)
        if (!isColliding(newX, newY, roboPane.getWidth(), roboPane.getHeight())) {
            roboPane.setLayoutX(newX);
            roboPane.setLayoutY(newY);
        }
    }

    private boolean isColliding(double x, double y, double width, double height) {
        // Check multiple points (center, corners, and edges)
        return isBluePixel(x, y) ||                     // Top-left
                isBluePixel(x + width, y) ||             // Top-right
                isBluePixel(x, y + height) ||            // Bottom-left
                isBluePixel(x + width, y + height) ||    // Bottom-right
                isBluePixel(x + width / 2, y + height / 2); // Center
    }


    private boolean isBluePixel(double x, double y) {
        ImageView activeMaze = getActiveMazeImageView();

        if (activeMaze.getImage() == null) return false;
        PixelReader pixelReader = activeMaze.getImage().getPixelReader();

        int imageWidth = (int) activeMaze.getImage().getWidth();
        int imageHeight = (int) activeMaze.getImage().getHeight();

        double viewWidth = activeMaze.getFitWidth();
        double viewHeight = activeMaze.getFitHeight();

        // Convert scene coordinates to image coordinates
        int imageX = (int) ((x / viewWidth) * imageWidth);
        int imageY = (int) ((y / viewHeight) * imageHeight);

        // Ensure coordinates are within bounds
        if (imageX < 0 || imageY < 0 || imageX >= imageWidth || imageY >= imageHeight) return false;

        // Read pixel color
        Color color = pixelReader.getColor(imageX, imageY);
        return color.getBlue() > 0.5 && color.getRed() < 0.5 && color.getGreen() < 0.5;
    }

    private ImageView getActiveMazeImageView() {
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab == null) {
            System.out.println("Warning: No tab is selected!");
            return null;
        }

        String tabName = selectedTab.getText();
        System.out.println("Selected Tab: " + tabName);

        if(selectedTab.getText().equals("Easy Maze") || selectedTab.getText().equals("Easy Car Maze")){
            return mazeImageView;
        }
        else if(selectedTab.getText().equals("Hard Maze") || selectedTab.getText().equals("Hard Car Maze")){
            return mazeImageView2;
        }


        System.out.println("Warning: Unrecognized tab name: " + tabName);
        return null;
    }


    // This will create an instance of MazeSolver
    @FXML
    private void onSolveMazeButtonClick(ActionEvent event) {
        // The robot starting position will be needed
        int startRow = 0;
        int startCol = 0;

        Image currentMazeImage = null;
        String selectedTab = tabPane.getSelectionModel().getSelectedItem().getText();
        if (selectedTab.equals("Easy Maze")) {
            currentMazeImage = mazeImageView.getImage();
            startRow = 5;
            startCol = 5;

        } else if (selectedTab.equals("Hard Maze")) {
            currentMazeImage = mazeImageView2.getImage();
            startRow = 52;
            startCol = 2;
        }

        if (currentMazeImage == null) {
            System.out.println("Maze image not found. Cannot solve maze.");
            return;
        }

        MazeSolver solver = new MazeSolver(currentMazeImage, selectedTab);
        solver.printMazeGrid();



        List<int[]> path = solver.solveMazeBFS(startRow, startCol);
        if (path == null) {
            System.out.println("No solution found for the maze.");
            return;
        }

        ImageView activeMazeView = getActiveMazeImageView();
        double scaleX = activeMazeView.getFitWidth() / activeMazeView.getImage().getWidth();
        double scaleY = activeMazeView.getFitHeight() / activeMazeView.getImage().getHeight();
        int cellSize = solver.getCellSize();

        new Thread(() -> {
            for (int[] cell : path) {
                double xPos = cell[1] * cellSize * scaleX;  // Column to X coordinate
                double yPos = cell[0] * cellSize * scaleY;  // Row to Y coordinate

                javafx.application.Platform.runLater(() -> {
                    roboPane.setLayoutX(xPos);
                    roboPane.setLayoutY(yPos);
                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}




