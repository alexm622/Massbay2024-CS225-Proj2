package com.example;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Game {

    private final int WIDTH = 720;
    private final int HEIGHT = 720;
    private final int GRID_SIZE = 9;

    private Canvas canvas;

    private SquareEnum[][] board = new SquareEnum[GRID_SIZE][GRID_SIZE];

    public Game(Stage primaryStage) {

        //initialize board
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                board[i][j] = SquareEnum.EMPTY;
            }
        }

        primaryStage.setTitle("Hello World!");

        Scene scene = new Scene(new Group(), WIDTH, HEIGHT);


        drawBoard(scene);

        // Example: Updating a square
        updateSquare(0, 0, SquareEnum.CIRCLE);
        updateSquare(1, 1, SquareEnum.CROSS);


        //listen for mouse clicks
        scene.setOnMouseClicked(e -> {
            int[] square = calculateSquare((int) e.getX(), (int) e.getY());
            updateSquare(square[0], square[1], board[square[0]][square[1]].getNext());
        });


        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /***
     * Draw the game board
     * @param scene The scene to draw the board on
     */
    private void drawBoard(Scene scene) {
        canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Code to draw the logic puzzle on the canvas
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        // Example: Drawing a grid
        int cellSize = WIDTH / GRID_SIZE;

        gc.setStroke(Color.BLACK);
        for (int i = 0; i < GRID_SIZE; i++) {
            gc.strokeLine(i * cellSize, 0, i * cellSize, HEIGHT);
            gc.strokeLine(0, i * cellSize, WIDTH, i * cellSize);
        }

        ((Group) scene.getRoot()).getChildren().add(canvas);
    }


    /**
     * Update the square at coordinates (x, y) with the given value
     * @param x x coord
     * @param y y coord
     * @param value value to update
     */
    private void updateSquare(int x, int y, SquareEnum value) {

        board[x][y] = value;

        // Code to update the square at coordinates (x, y) with the given value
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int cellSize = WIDTH / GRID_SIZE;
        int squareX = x * cellSize;
        int squareY = y * cellSize;

        // Clear the existing square
        gc.clearRect(squareX, squareY, cellSize, cellSize);

        // Draw the updated square
        gc.setFill(Color.WHITE);
        gc.fillRect(squareX, squareY, cellSize, cellSize);
        gc.setStroke(Color.BLACK);
        gc.strokeRect(squareX, squareY, cellSize, cellSize);
        gc.setFill(Color.BLACK);
        if (value == SquareEnum.CIRCLE) {
            gc.fillOval(squareX, squareY, cellSize, cellSize);
        } else if (value == SquareEnum.CROSS) {
            gc.strokeLine(squareX, squareY, squareX + cellSize, squareY + cellSize);
            gc.strokeLine(squareX, squareY + cellSize, squareX + cellSize, squareY);
        }else {
            //clear square
            gc.clearRect(squareX, squareY, cellSize, cellSize);
        }
    }

    private int[] calculateSquare(int x, int y) {
        int cellSize = WIDTH / GRID_SIZE;
        int squareX = x / cellSize;
        int squareY = y / cellSize;
        return new int[]{squareX, squareY};
    }

}
