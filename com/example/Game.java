package com.example;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        

        

    }

}
