package src.domain;

import javax.swing.*;
import java.awt.*;

public class Clustering {

    private JButton[][] board;

    public Clustering() {
    }

    public void setBoard(JButton[][] board) {
        this.board = board;
    }

    public void tilt(String direction) {
        int rows = board.length;
        int cols = board[0].length;

        switch (direction) {
            case "UP":
                for (int col = 0; col < cols; col++) {
                    for (int row = 1; row < rows; row++) {
                        if (board[row][col].getBackground() != Color.WHITE) {
                            int newRow = row;
                            if (newRow > 0 && board[newRow - 1][col].getBackground() == Color.WHITE) {
                                board[newRow - 1][col].setBackground(board[newRow][col].getBackground());
                                board[newRow][col].setBackground(Color.WHITE);
                            }
                        }
                    }
                }
                break;
            case "DOWN":
                for (int col = 0; col < cols; col++) {
                    for (int row = rows - 2; row >= 0; row--) {
                        if (board[row][col].getBackground() != Color.WHITE) {
                            int newRow = row;
                            if (newRow < rows - 1 && board[newRow + 1][col].getBackground() == Color.WHITE) {
                                board[newRow + 1][col].setBackground(board[newRow][col].getBackground());
                                board[newRow][col].setBackground(Color.WHITE);
                            }
                        }
                    }
                }
                break;
            case "LEFT":
                for (int row = 0; row < rows; row++) {
                    for (int col = 1; col < cols; col++) {
                        if (board[row][col].getBackground() != Color.WHITE) {
                            int newCol = col;
                            if (newCol > 0 && board[row][newCol - 1].getBackground() == Color.WHITE) {
                                board[row][newCol - 1].setBackground(board[row][newCol].getBackground());
                                board[row][newCol].setBackground(Color.WHITE);
                            }
                        }
                    }
                }
                break;
            case "RIGHT":
                for (int row = 0; row < rows; row++) {
                    for (int col = cols - 2; col >= 0; col--) {
                        if (board[row][col].getBackground() != Color.WHITE) {
                            int newCol = col;
                            if (newCol < cols - 1 && board[row][newCol + 1].getBackground() == Color.WHITE) {
                                board[row][newCol + 1].setBackground(board[row][newCol].getBackground());
                                board[row][newCol].setBackground(Color.WHITE);
                            }
                        }
                    }
                }
                break;
        }
    }
}