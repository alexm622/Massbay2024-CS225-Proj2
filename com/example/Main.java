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
        System.out.println();
        puzzle.printTable(puzzle.getAnswerTable());

        ClueGenerator clueGenerator = new ClueGenerator();
        Clue[] clues = clueGenerator.generateClues();
        for(Clue clue : clues){
            System.out.println(clue);
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new Game(primaryStage);
    }

    public static Config getConfig() {
        return config;
    }

    public static Puzzle getPuzzle() {
        return puzzle;
    }
    
}
