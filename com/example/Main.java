package com.example;

import javafx.application.Application;
import javafx.stage.Stage;

//generate a javafx stub

public class Main extends Application{

    private static Config config;
    private static Puzzle puzzle;

    public static void main(String[] args) {
        config = new Config();
        puzzle = new Puzzle();
        puzzle.printBoard(puzzle.getSolutionBoard());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new Game(primaryStage);
    }
}
