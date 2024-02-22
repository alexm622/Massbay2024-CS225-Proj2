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
    private int[][] answerTable;
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
        //first generate the answer table
        solutionBoard = new int[WIDTH][HEIGHT][BOX_SIZE];
        answerTable = new int[WIDTH][BOX_SIZE];
        Random rand = new Random();

        //first create the answer table
        for(int i = 0; i < WIDTH; i++) {
            int[] row = new int[BOX_SIZE];
            for(int j = 0; j < BOX_SIZE; j++) {
                int answer = rand.nextInt(3);
                //check to make sure the answer is not already in the row
                while(true) {
                    boolean found = false;
                    for(int k = 0; k < j; k++) {
                        if(row[k] == answer) {
                            found = true;
                            break;
                        }
                    }
                    if(found) {
                        answer = rand.nextInt(3);
                    } else {
                        break;
                    }
                }
                row[j] = answer;
            }
            answerTable[i] = row;
        }

        //now create the solution board
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT - i; j++) {
                int[] row = new int[BOX_SIZE];
                for (int k = 0; k < BOX_SIZE; k++) {
                    row[k] = answerTable[i][k];
                }
                solutionBoard[i][j] = row;
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

    public void printTable(int[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
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

    public int[][] getAnswerTable() {
        return answerTable;
    }

    // TODO remove this main function
    public static void main(String[] args) {
        Puzzle p = new Puzzle();
        p.printBoard(p.getSolutionBoard());
        p.printTable(p.answerTable);
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