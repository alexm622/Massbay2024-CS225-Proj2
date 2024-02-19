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

    public Game(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");

        Scene scene = new Scene(new Group(), WIDTH, HEIGHT);

        drawBoard(scene);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void drawBoard(Scene s) {

    private void drawBoard(Scene scene) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Code to draw the logic puzzle on the canvas
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        // Example: Drawing a grid
        int gridSize = 10;
        int cellSize = WIDTH / gridSize;

        gc.setStroke(Color.BLACK);
        for (int i = 0; i < gridSize; i++) {
            gc.strokeLine(i * cellSize, 0, i * cellSize, HEIGHT);
            gc.strokeLine(0, i * cellSize, WIDTH, i * cellSize);
        }

        ((Group) scene.getRoot()).getChildren().add(canvas);
    }

    }

    private void updateSquare(int x, int y, String value) {

        // Code to update the square at coordinates (x, y) with the given value
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int cellSize = WIDTH / gridSize;
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
        gc.fillText(value, squareX + cellSize / 2, squareY + cellSize / 2);
    }

}
