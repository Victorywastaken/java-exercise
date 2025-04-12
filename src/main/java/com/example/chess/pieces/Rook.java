package com.example.chess.pieces;

import com.example.chess.*;

public class Rook extends Piece {
    private boolean hasMoved = false;

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Board board) {
        if (!newPosition.isValid()) return false;

        int rowDiff = newPosition.getRow() - getPosition().getRow();
        int colDiff = newPosition.getColumn() - getPosition().getColumn();

        if (rowDiff == 0 || colDiff == 0) {
            int rowStep = rowDiff != 0 ? rowDiff / Math.abs(rowDiff) : 0;
            int colStep = colDiff != 0 ? colDiff / Math.abs(colDiff) : 0;

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

        return false;
    }

    @Override
    protected boolean updatePosition(Position newPosition) {
        return super.updatePosition(newPosition);
    }

    public boolean getHasMoved() {
        return hasMoved;
    }
}
