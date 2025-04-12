package com.example.chess.pieces;

import com.example.chess.*;

public class Knight extends Piece {
    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Board board) {
        if (!newPosition.isValid()) return false;

        int rowDiff = Math.abs(newPosition.getRow() - getPosition().getRow());
        int colDiff = Math.abs(newPosition.getColumn() - getPosition().getColumn());

        boolean isValidKnightMove = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);

        if (isValidKnightMove) {
            Piece targetPiece = board.getPiece(newPosition);
            return targetPiece == null || targetPiece.getColor() != getColor();
        }

        return false;
    }
}
