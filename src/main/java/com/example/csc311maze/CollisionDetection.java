package com.example.csc311maze;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * This class is not used. It was made prior to pixel readers' implementation
 */
public class CollisionDetection {

    private final AnchorPane anchorPane;
    private final Pane roboPane;

    /**
     * CollisionDetection constructor with specified anchorPane and pane that contains imageView
     *
     * @param mazePane
     * @param robopane
     */
    public CollisionDetection(AnchorPane mazePane, Pane robopane) {
        this.anchorPane = mazePane;
        this.roboPane = robopane;

    }

    /**
     * Method to detect if pane that contains imageView collides with  hidden Rectangle
     *
     * @param x
     * @param y
     * @return boolean
     */
    public boolean getCollision(double x, double y) {
        //roboPane current position
        double originalX = roboPane.getLayoutX();
        double originalY = roboPane.getLayoutY();

        //roboPane new position
        roboPane.setLayoutX(originalX + x);
        roboPane.setLayoutY(originalY + y);

        //if false, collision does not occur
        boolean collide = false;

        //Detects all Nodes(objects) that are children of parent AnchorPane
        for (Node object : anchorPane.getChildren()) {

            //If object is Pane continue detection with no action
            if (object == roboPane) continue;

            //If object detected is a rectangle from javafxRectangle class, declare it a wall
            if (object instanceof Rectangle wall) {

                //If the bounds of Pane intersect with the bounds of the declared walls(Rectangles) collision is true
                if (roboPane.getBoundsInParent().intersects(wall.getBoundsInParent())) {
                    collide = true;
                    break;
                }
            }

        }

        //Resets Panes position
        roboPane.setLayoutX(originalX);
        roboPane.setLayoutY(originalY);
        return collide;

    }
}
