package com.example;
//backend for a logic puzzle game

import java.util.Random;

public class Puzzle {
    /**
     * the first index is the row
     * the second index is the box number horizontally
     * the third index is the index of the correct answer in that specific row in that specific box
     */
    private int[][][] solutionBoard; 

    /**
     * generates a random board 
     */
    public void generateRandomBoard() {
        // generate a random board
        solutionBoard = new int[3][3][3]; // 3 rows, 3 boxes max width, 3 answers per box

        // fill with -1
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    solutionBoard[i][j][k] = -1;
                }
            }
        }

        int width = 0;

        Random r = new Random();

        //build the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3 - width; j++) {
                for (int k = 0; k < 3; k++) {
                    solutionBoard[i][j][k] = r.nextInt(3);
                }
            }
            width++;
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
        for (int i = 0; i < 3; i++) {
            buffer[i] = "";
        }

        int width = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3  - width; j++) {
                for (int k = 0; k < 3; k++) {
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
            for (int l = 0; l < 3; l++) {
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

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
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
        p.generateRandomBoard();
        p.printBoard(p.getSolutionBoard());
    }

}