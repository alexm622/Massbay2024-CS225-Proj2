package com.example;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Clue {

    private String clue;

    private int boxnum;

    private int[] indiciesEffected;

    private ClueType type;

    public Clue(String clue, int boxnum, int[] indiciesEffected, ClueType type) {
        this.clue = clue;
        this.boxnum = boxnum;
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

    public int getBoxnum() {
        return boxnum;
    }

    public void setBoxnum(int boxnum) {
        this.boxnum = boxnum;
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
                ", boxnum=" + boxnum +
                ", indiciesEffected=" + Arrays.toString(indiciesEffected) +
                ", type=" + type +
                '}';
    }

    
}
