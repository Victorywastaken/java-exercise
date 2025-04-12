package com.example.chess.pieces;

import com.example.chess.*;

public class Bishop extends Piece {
    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Board board) {
        if (!newPosition.isValid()) return false;

        int rowDiff = Math.abs(newPosition.getRow() - getPosition().getRow());
        int colDiff = Math.abs(newPosition.getColumn() - getPosition().getColumn());

        if (rowDiff == colDiff) {
            Piece targetPiece = board.getPiece(newPosition);
            return targetPiece == null || targetPiece.getColor() != getColor();
        }

        return false;
    }
}
