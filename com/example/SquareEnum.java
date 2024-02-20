package com.example;

public enum SquareEnum {
    EMPTY, // Empty square
    CIRCLE,
    CROSS;

    public SquareEnum getNext() {
        return values()[(ordinal() + 1) % values().length];
    }

}
