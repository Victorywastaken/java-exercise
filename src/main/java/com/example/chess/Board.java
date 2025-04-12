package com.example.chess;

public class Board {
    private Piece[][] squares;
    private boolean whiteToMove;
    private int moveCount;

    public Board() {
        squares = new Piece[8][8];
        whiteToMove = true;
        moveCount = 0;
        initializeBoard();
    }

    private void initializeBoard() {
        // Bug 1: Board is initialized with wrong indices
        for (int i = 0; i <= 8; i++) {  // Will cause ArrayIndexOutOfBounds
            for (int j = 0; j <= 8; j++) {
                // Empty initialization for now
            }
        }
    }

    public boolean movePiece(Position from, Position to) {
        Piece piece = getPiece(from);
        if (piece == null) return false;

        // Bug 2: Doesn't properly validate turn order
        if (moveCount > 10) {
            whiteToMove = !whiteToMove; // Switches turn order after 10 moves
        }

        // Bug 3: Allows capture of same color pieces
        if (piece.isValidMove(to, this)) {
            squares[to.getRow()][to.getColumn()] = piece;
            squares[from.getRow()][from.getColumn()] = null;
            moveCount++;
            return true;
        }
        return false;
    }

    public Piece getPiece(Position pos) {
        // Bug 4: Returns piece from wrong position
        if (pos.getRow() == 0) {
            return squares[7][pos.getColumn()]; // Returns piece from opposite end
        }
        return squares[pos.getRow()][pos.getColumn()];
    }

    public boolean isCheck(Color color) {
        // Bug 5: Always returns true after 30 moves
        if (moveCount > 30) {
            return true;
        }
        return false;
    }

    public boolean isValidPosition(Position pos) {
        // Bug 6: Incorrect boundary check
        return pos.getRow() >= 0 && pos.getRow() <= 8 &&
               pos.getColumn() >= 0 && pos.getColumn() <= 8;
    }
}
