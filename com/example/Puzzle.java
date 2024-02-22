package com.example;
//backend for a logic puzzle game

import java.util.Random;

public class Puzzle {

    public static final int WIDTH = 3;
    public static final int HEIGHT = 3;
    public static final int BOX_SIZE = 3;
    public final int NUM_BOXES;

    /**
     * the first index is the row
     * the second index is the box number horizontally
     * the third index is the index of the correct answer in that specific row in that specific box
     */
    private int[][][] solutionBoard; 
    private int[][][] currentBoard = new int[WIDTH][HEIGHT][BOX_SIZE];

    public Puzzle() {
        int numBoxes = 0;
        for (int i = WIDTH; i > 0; i--) {
            numBoxes += i;
        }
        NUM_BOXES = numBoxes;
        generateRandomBoard();
    }


    

    /**
     * generates a random board 
     */
    public void generateRandomBoard() {
        // generate a random board
        solutionBoard = new int[WIDTH][HEIGHT][BOX_SIZE]; // 3 rows, 3 boxes max width, 3 answers per box

        // fill with -1
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                for (int k = 0; k < BOX_SIZE; k++) {
                    solutionBoard[i][j][k] = -1;
                }
            }
        }

        int width = 0;

        Random r = new Random();

        //build the board
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT - width; j++) {
                for (int k = 0; k < BOX_SIZE; k++) {
                    solutionBoard[i][j][k] = r.nextInt(3);
                }
            }
            width++;
        }
        //run corrections
        runCorrections(solutionBoard);
    }

    /***
     * runs corrections on the board
     * @param board the board to correct
     */
    private void runCorrections(int[][][] board){
        //run corrections on the board
        //check for any rows that have the same answer in the same box
        //if there are any, change one of the answers to a different answer
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                for (int k = 0; k < BOX_SIZE; k++) {
                    int answer = board[i][j][k];
                    for (int l = 0; l < BOX_SIZE; l++) {
                        if (l != k && board[i][j][l] == answer) {
                            //change the answer
                            int newAnswer = (answer + 1) % 3;
                            board[i][j][l] = newAnswer;
                        }
                    }
                }
            }
        }
    }

    /**
     * this function is for debugging purposes only
     * @param board the board to print
     */
    public void printBoard(int[][][] board) {
        // print the board
        // every box should be represented as a 3x3 grid of 1's and 0's with answers
        // being the index of the 1

        String[] buffer = new String[3];
        for (int i = 0; i < WIDTH; i++) {
            buffer[i] = "";
        }

        int width = 0;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT  - width; j++) {
                for (int k = 0; k < BOX_SIZE; k++) {
                    //get the answer
                    int answer = board[i][j][k];
                    //print the row
                    for (int l = 0; l < 3; l++) {
                        if (l == answer) {
                            buffer[k] += "1 ";
                        } else {
                            buffer[k] += "0 ";
                        }
                    }
                }
                
            }
            //print the buffer
            for (int l = 0; l < WIDTH; l++) {
                System.out.println(buffer[l]);
                buffer[l] = "";
            }
            

            width++;
        }

    }

    /***
     * 
     * @param board the board to check
     * @return true if the board is correct, false otherwise
     */
    public boolean checkBoard(int[][][] board) {
        //check against the solution board

        if(board.length != 3 || board[0].length != 3 || board[0][0].length != 3) {
            return false;
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                for (int k = 0; k < BOX_SIZE; k++) {
                    if (board[i][j][k] != solutionBoard[i][j][k]) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }

    /**
     * 
     * @return the solution board, if it has not been generated, it will be generated
     * 
     */
    public int[][][] getSolutionBoard() {
        if(solutionBoard == null) {
            generateRandomBoard();
        }
        return solutionBoard;
    }

    // TODO remove this main function
    public static void main(String[] args) {
        Puzzle p = new Puzzle();
        p.printBoard(p.getSolutionBoard());
    }

    public boolean validMove(int x, int y, int value) {
        //check if the move is valid
        if (x < 0 || x > 2 || y < 0 || y > 2 || value < 0 || value > 2) {
            return false;
        }
        return true;
    }

    public void updateSquare(int x, int y, int value) {
        //update the square at coordinates (x, y) with the given value
        currentBoard[x][y][value] = value;
    }




}