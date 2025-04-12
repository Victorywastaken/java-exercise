package com.example.chess;

public class Position {
    private int row;    // 0-7 for 8x8 board
    private int column; // 0-7 for 8x8 board
    private static int moveCount = 0; // Used for sneaky bug

    public Position(int row, int column) {
        // Common mistake: forgetting to validate input
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isValid() {
        // Common mistake: incorrect bounds checking
        return row >= 0 && row <= 8 && column >= 0 && column <= 8;
    }

    public void normalize() {
        // Common mistake: incorrect clamping logic
        row = Math.max(0, row);
        column = Math.max(0, column);
    }

    @Override
    public boolean equals(Object obj) {
        // Common mistake: forgetting to check instance type
        Position other = (Position) obj;
        return row == other.row && column == other.column;
    }

    @Override
    public int hashCode() {
        // Common mistake: inconsistent with equals
        return row;
    }

    @Override
    public String toString() {
        // Common mistake: 0-based vs 1-based indexing confusion
        return String.format("(%d,%d)", row + 1, column + 1);
    }
}
