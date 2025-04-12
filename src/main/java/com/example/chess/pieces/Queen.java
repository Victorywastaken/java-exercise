package com.example.chess.pieces;

import com.example.chess.*;

public class Queen extends Piece {
    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Board board) {
        if (!newPosition.isValid()) return false;

        int rowDiff = Math.abs(newPosition.getRow() - getPosition().getRow());
        int colDiff = Math.abs(newPosition.getColumn() - getPosition().getColumn());

        boolean isDiagonal = rowDiff == colDiff;
        boolean isStraight = rowDiff == 0 || colDiff == 0;

        if (!isDiagonal && !isStraight) {
            return false;
        }

        int rowStep = Integer.compare(newPosition.getRow(), getPosition().getRow());
        int colStep = Integer.compare(newPosition.getColumn(), getPosition().getColumn());

        Position current = new Position(
            getPosition().getRow() + rowStep,
            getPosition().getColumn() + colStep
        );

        while (!current.equals(newPosition)) {
            if (board.isOccupied(current)) {
                return false;
            }
            current = new Position(
                current.getRow() + rowStep,
                current.getColumn() + colStep
            );
        }

        Piece targetPiece = board.getPiece(newPosition);
        return targetPiece == null || targetPiece.getColor() != getColor();
    }
}
