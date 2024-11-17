package Clustering.src.domain;

import java.awt.Color;

public class Clustering {
    private Tile[][] board;

    public Clustering(Tile[][] board) {
        this.board = board;
    }

    public void setEachWhiteTileDisabled() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].checkAndDisableIfWhite();
            }
        }
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void moveUp() {
        for (int col = 0; col < board[0].length; col++) {
            for (int row = 1; row < board.length; row++) {
                if (board[row][col].getBackground().equals(Color.WHITE)) {
                    continue;
                }
                if (board[row - 1][col].getBackground().equals(Color.WHITE)) {
                    board[row - 1][col].setBackground(board[row][col].getBackground());
                    board[row][col].setBackground(Color.WHITE);
                }
            }
        }
    }

    public void moveDown() {
        for (int col = 0; col < board[0].length; col++) {
            for (int row = board.length - 2; row >= 0; row--) {
                if (board[row][col].getBackground().equals(Color.WHITE)) {
                    continue;
                }
                if (board[row + 1][col].getBackground().equals(Color.WHITE)) {
                    board[row + 1][col].setBackground(board[row][col].getBackground());
                    board[row][col].setBackground(Color.WHITE);
                }
            }
        }
    }

    public void moveLeft() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 1; col < board[row].length; col++) {
                if (board[row][col].getBackground().equals(Color.WHITE)) {
                    continue;
                }
                if (board[row][col - 1].getBackground().equals(Color.WHITE)) {
                    board[row][col - 1].setBackground(board[row][col].getBackground());
                    board[row][col].setBackground(Color.WHITE);
                }
            }
        }
    }

    public void moveRight() {
        for (int row = 0; row < board.length; row++) {
            for (int col = board[row].length - 2; col >= 0; col--) {
                if (board[row][col].getBackground().equals(Color.WHITE)) {
                    continue;
                }
                if (board[row][col + 1].getBackground().equals(Color.WHITE)) {
                    board[row][col + 1].setBackground(board[row][col].getBackground());
                    board[row][col].setBackground(Color.WHITE);
                }
            }
        }
    }
    public int calculateScore() {
        int score = 0;
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!visited[i][j]) {
                    int clusterSize = countCluster(i, j, board[i][j].getBackground(), visited);
                    score += clusterSize; // El puntaje aumenta linealmente con el tamaño del grupo
                }
            }
        }
        return score;
    }

    private int countCluster(int row, int col, Color color, boolean[][] visited) {
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || visited[row][col] || !board[row][col].getBackground().equals(color)) {
            return 0;
        }

        visited[row][col] = true;
        int size = 1;

        size += countCluster(row - 1, col, color, visited);
        size += countCluster(row + 1, col, color, visited);
        size += countCluster(row, col - 1, color, visited);
        size += countCluster(row, col + 1, color, visited);

        return size;
    }

}