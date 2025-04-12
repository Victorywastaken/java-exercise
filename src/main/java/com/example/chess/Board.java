package com.example.chess;

import com.example.chess.pieces.*;

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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 1) {
                    squares[i][j] = new Pawn(Color.BLACK, new Position(i, j));
                } else if (i == 6) {
                    squares[i][j] = new Pawn(Color.WHITE, new Position(i, j));
                }
            }
        }
    }

    public boolean movePiece(Position from, Position to) {
        if (!isValidPosition(from) || !isValidPosition(to)) {
            return false;
        }

        Piece piece = getPiece(from);
        if (piece == null || piece.getColor() != (whiteToMove ? Color.WHITE : Color.BLACK)) {
            return false;
        }

        if (piece.isValidMove(to, this)) {
            squares[to.getRow()][to.getColumn()] = piece;
            squares[from.getRow()][from.getColumn()] = null;
            piece.setPosition(to);
            whiteToMove = !whiteToMove;
            moveCount++;
            return true;
        }
        return false;
    }

    public Piece getPiece(Position pos) {
        if (!isValidPosition(pos)) {
            return null;
        }
        return squares[pos.getRow()][pos.getColumn()];
    }

    public boolean isOccupied(Position pos) {
        return getPiece(pos) != null;
    }

    public boolean isCheck(Color color) {
        Position kingPos = findKing(color);
        if (kingPos == null) return false;

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Piece piece = squares[i][j];
                if (piece != null && piece.getColor() != color) {
                    if (piece.isValidMove(kingPos, this)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Position findKing(Color color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = squares[i][j];
                if (piece != null && piece.getColor() == color) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    public boolean isValidPosition(Position pos) {
        return pos != null && pos.getRow() >= 0 && pos.getRow() <= 7 &&
               pos.getColumn() >= 0 && pos.getColumn() <= 7;
    }
}
