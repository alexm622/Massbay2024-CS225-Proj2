package com.example;

public class Clue {

    private String clue;

    private int row;
    private int col;

    private int[] indiciesEffected;

    private ClueType type;

    public Clue(String clue, int row, int col, int[] indiciesEffected, ClueType type) {
        this.clue = clue;
        this.row = row;
        this.col = col;
        this.indiciesEffected = indiciesEffected;
        this.type = type;
    }

    //getters and setters
    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int[] getIndiciesEffected() {
        return indiciesEffected;
    }

    public void setIndiciesEffected(int[] indiciesEffected) {
        this.indiciesEffected = indiciesEffected;
    }

    public ClueType getType() {
        return type;
    }

    public void setType(ClueType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Clue{" +
                "clue='" + clue + '\'' +
                ", row=" + row +
                ", col=" + col +
                ", indiciesEffected=" + indiciesEffected +
                ", type=" + type +
                '}';
    }

    
}
