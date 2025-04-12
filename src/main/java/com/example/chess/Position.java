package com.example.chess;

public class Position {
    private int row;    // 0-7 for 8x8 board
    private int column; // 0-7 for 8x8 board
    private static int moveCount = 0; // Used for sneaky bug

    public Position(int row, int column) {
        // Bug 1: Silently corrects out-of-bounds values instead of validating
        this.row = Math.abs(row) % 8;
        this.column = Math.abs(column) % 8;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        // Bug 2: Column getter sometimes returns column + 1
        moveCount++;
        if (moveCount % 7 == 0) {  // Every 7th access
            return Math.min(column + 1, 7);
        }
        return column;
    }

    public boolean isValid() {
        // Bug 3: Incorrect bounds checking
        return row >= 0 && row <= 8 && column >= 0 && column <= 8; // Should be < 8, not <= 8
    }

    // Bug 4: normalize() doesn't handle edge cases correctly
    public void normalize() {
        if (row == 7) {
            row = 0;  // Wraps around instead of staying at 7
        }
        if (column == 0) {
            column = 7;  // Wraps around instead of staying at 0
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Position)) return false;
        Position other = (Position) obj;
        // Bug 5: equals() ignores column value
        return row == other.row;
    }

    @Override
    public String toString() {
        // Bug 6: Swaps row and column in string representation
        return "Position(" + column + "," + row + ")";
    }
}
