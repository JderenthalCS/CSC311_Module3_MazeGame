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

    public MazeSolver(Image maze) {
        this.maze = maze;
        this.cellSize = 5;  // Adjust if your cell size is different
        this.mazeGrid = convertMazeImageToGrid();
        this.endPoint = new int[2];
        findEndpoint();
    }

    private int[][] convertMazeImageToGrid() {
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
        return grid;
    }

    private void findEndpoint() {
        PixelReader reader = maze.getPixelReader();
        int rows = (int) (maze.getHeight() / cellSize);
        int cols = (int) (maze.getWidth() / cellSize);
        boolean found = false;
        for (int row = 0; row < rows && !found; row++) {
            for (int col = 0; col < cols; col++) {
                Color color = reader.getColor(col * cellSize, row * cellSize);
                if (color.equals(Color.rgb(102, 0, 204))) {
                    endPoint[0] = row;
                    endPoint[1] = col;
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            endPoint = new int[]{rows - 1, cols - 1};
        }
    }

    public List<int[]> solveMazeBFS(int startRow, int startCol) {
        int endRow = endPoint[0];
        int endCol = endPoint[1];
        if (mazeGrid[startRow][startCol] == 0 || mazeGrid[endRow][endCol] == 0) {
            return null;
        }
        int rows = mazeGrid.length;
        int cols = mazeGrid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[][][] parent = new int[rows][cols][2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                parent[i][j][0] = -1;
                parent[i][j][1] = -1;
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        visited[startRow][startCol] = true;
        queue.add(new int[]{startRow, startCol});
        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curRow = current[0], curCol = current[1];
            if (curRow == endRow && curCol == endCol) {
                return reconstructPath(parent, endRow, endCol);
            }
            for (int[] dir : directions) {
                int newRow = curRow + dir[0];
                int newCol = curCol + dir[1];
                if (newRow >= 0 && newRow < rows &&
                        newCol >= 0 && newCol < cols &&
                        mazeGrid[newRow][newCol] == 1 &&
                        !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    parent[newRow][newCol][0] = curRow;
                    parent[newRow][newCol][1] = curCol;
                    queue.add(new int[]{newRow, newCol});
                }
            }
        }
        return null;
    }

    private List<int[]> reconstructPath(int[][][] parent, int endRow, int endCol) {
        List<int[]> path = new ArrayList<>();
        int row = endRow, col = endCol;
        while (row != -1 && col != -1) {
            path.add(new int[]{row, col});
            int prevRow = parent[row][col][0];
            int prevCol = parent[row][col][1];
            row = prevRow;
            col = prevCol;
        }
        Collections.reverse(path);
        return path;
    }

    public void printMazeGrid() {
        for (int row = 0; row < mazeGrid.length; row++) {
            for (int col = 0; col < mazeGrid[0].length; col++) {
                System.out.print(mazeGrid[row][col] + " ");
            }
            System.out.println();
        }
    }
}
