package Clustering.src.domain;

import java.awt.Color;

/**
 * La clase Clustering representa la lógica del juego para un juego de agrupamiento.
 * Administra el estado del tablero y proporciona métodos para mover fichas y calcular la puntuación.
 */
public class Clustering {
    private Tile[][] board;

    /**
     * Construye un juego de Clustering con el tablero dado.
     *
     * @param board El estado inicial del tablero.
     */
    public Clustering(Tile[][] board) {
        this.board = board;
    }

    /**
     * Deshabilita cada ficha que es blanca.
     */
    public void setEachWhiteTileDisabled() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].checkAndDisableIfWhite();
            }
        }
    }

    /**
     * Devuelve el estado actual del tablero.
     *
     * @return El estado actual del tablero.
     */
    public Tile[][] getBoard() {
        return board;
    }

    /**
     * Mueve todas las fichas hacia arriba, llenando los espacios vacíos con fichas blancas.
     */
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

    /**
     * Mueve todas las fichas hacia abajo, llenando los espacios vacíos con fichas blancas.
     */
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

    /**
     * Mueve todas las fichas hacia la izquierda, llenando los espacios vacíos con fichas blancas.
     */
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

    /**
     * Mueve todas las fichas hacia la derecha, llenando los espacios vacíos con fichas blancas.
     */
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

    /**
     * Calcula la puntuación basada en el tamaño de los grupos de fichas del mismo color.
     *
     * @return La puntuación calculada.
     */
    public int calculateScore() {
        int score = 0;
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!visited[i][j]) {
                    int clusterSize = countCluster(i, j, board[i][j].getBackground(), visited);
                    score += clusterSize; // La puntuación aumenta linealmente con el tamaño del grupo
                }
            }
        }
        return score;
    }

    /**
     * Cuenta recursivamente el tamaño de un grupo de fichas del mismo color.
     *
     * @param row El índice de fila de la ficha actual.
     * @param col El índice de columna de la ficha actual.
     * @param color El color del grupo.
     * @param visited Un array 2D para llevar un registro de las fichas visitadas.
     * @return El tamaño del grupo.
     */
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