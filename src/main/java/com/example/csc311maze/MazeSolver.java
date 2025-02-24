package com.example.csc311maze;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import java.util.*;

public class MazeSolver {
    private final Image maze;
    private final int cellSize;
    private int[][] mazeGrid;
    private int[] endPoint;

    public MazeSolver(Image maze, String selectedTab) {
        this.maze = maze;
        this.cellSize = 5;
        this.endPoint = findEndpoint(selectedTab);
        this.mazeGrid = convertMazeImageToMatrix();
    }

    // Matrix representation of the maze
    private int[][] convertMazeImageToMatrix() {
        int rows = (int) (maze.getHeight() / cellSize);
        int cols = (int) (maze.getWidth() / cellSize);
        int[][] grid = new int[rows][cols];
        PixelReader reader = maze.getPixelReader();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Color color = reader.getColor(col * cellSize, row * cellSize);
                grid[row][col] = (color.equals(Color.rgb(255, 255, 255)) || color.equals(Color.rgb(204, 119, 0))) ? 1 : 0;
            }
        }

        System.out.println(String.format("Rows: %s", rows) + String.format(" Columns: %s", cols));
        return grid;
    }

    // Using pixel colors to determine the end point didn't work
//    private void findEndpoint() {
//        PixelReader reader = maze.getPixelReader();
//        int rows = (int) (maze.getHeight() / cellSize);
//        int cols = (int) (maze.getWidth() / cellSize);
//        boolean found = false;
//        for (int row = 0; row < rows && !found; row++) {
//            for (int col = 0; col < cols; col++) {
//                Color color = reader.getColor(col * cellSize, row * cellSize);
//                if (color.equals(Color.rgb(102, 0, 214))) {
//                    this.endPoint[0] = row;
//                    this.endPoint[1] = col;
//                    found = true;
//                    break;
//                }
//            }
//        }
//        if (!found) {
//            this.endPoint = new int[]{52, cols - 1};
//        }
//        System.out.println("\n\n\n" + Arrays.toString(this.endPoint) + "\n\n\n");
//    }

    private int[] findEndpoint(String tab) {
        if (tab.equals("Easy Maze")) {
            return new int[]{60, 90};
        } else if (tab.equals("Hard Maze")) {
            return new int[]{51, 118};
        }
        return new int[]{0,0};
    }

    // Breadth First Search to find path
    public List<int[]> solveMazeBFS(int startRow, int startCol) {
        int endRow = endPoint[0], endCol = endPoint[1];

        if (mazeGrid[startRow][startCol] == 0 || mazeGrid[endRow][endCol] == 0) return null;

        int rows = mazeGrid.length, cols = mazeGrid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        Queue<Cell> queue = new LinkedList<>();
        visited[startRow][startCol] = true;
        queue.offer(new Cell(startRow, startCol, null));

        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            if (current.row == endRow && current.col == endCol) return reconstructPath(current);
            for (int[] direction : directions) {
                int newRow = current.row + direction[0], newCol = current.col + direction[1];
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && mazeGrid[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    queue.offer(new Cell(newRow, newCol, current));
                }
            }
        }
        return null;
    }

    private List<int[]> reconstructPath(Cell endCell) {
        Deque<int[]> path = new ArrayDeque<>();
        Cell current = endCell;
        while (current != null) {
            path.addFirst(new int[]{current.row, current.col});
            current = current.parent;
        }
        return new ArrayList<>(path);
    }

    public void printMazeGrid() {
        for (int row = 0; row < mazeGrid.length; row++) {
            for (int col = 0; col < mazeGrid[0].length; col++) {
                System.out.print(mazeGrid[row][col] + " ");
            }
            System.out.println();
        }
    }

    public int getCellSize() {
        return cellSize;
    }

    private static class Cell {
        int row;
        int col;
        Cell parent;
        Cell(int row, int col, Cell parent) {
            this.row = row;
            this.col = col;
            this.parent = parent;
        }
    }
}
