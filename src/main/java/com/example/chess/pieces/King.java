package com.example.chess.pieces;

import com.example.chess.*;

public class King extends Piece {
    private boolean hasMoved = false;

    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Board board) {
        if (!newPosition.isValid()) return false;

        int rowDiff = Math.abs(newPosition.getRow() - getPosition().getRow());
        int colDiff = Math.abs(newPosition.getColumn() - getPosition().getColumn());

        // Bug: Allows diagonal movement of 2 squares
        if (rowDiff <= 1 && colDiff <= 2) {
            Piece targetPiece = board.getPiece(newPosition);
            return targetPiece == null || targetPiece.getColor() != getColor();
        }

        // Bug: Castling implementation missing checks for:
        // - King hasn't moved
        // - Rook hasn't moved
        // - No pieces between
        // - King not in check
        // - King doesn't pass through check
        if (!hasMoved && rowDiff == 0 && colDiff == 2) {
            return true;  // Oversimplified castling
        }

        return false;
    }

    @Override
    protected boolean updatePosition(Position newPosition) {
        // Bug: forgot to update hasMoved
        return super.updatePosition(newPosition);
    }
}
