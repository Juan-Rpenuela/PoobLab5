package Clustering.src.presentation;

import javax.swing.*;
import Clustering.src.domain.Clustering;
import Clustering.src.domain.Tile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.border.EmptyBorder;

/**
 * La clase Clustering\_GUI representa la interfaz gráfica de usuario para el juego de Clustering.
 * Extiende JFrame e incluye todos los elementos necesarios para interactuar con el juego.
 */
public class Clustering_GUI extends JFrame {

    private Clustering game;
    private boolean isGameStarted;
    private static final Dimension PREFERED_DIMENSION = new Dimension(1000,700);
    /* Barra superior */
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu config;
    private JMenuItem createTileMenuItem;
    private JMenuItem deleteTileMenuItem;
    private JMenuItem exitGameMenuItem;
    private JMenuItem nuevo;
    private JMenuItem open;
    private JMenuItem save;
    /* Puntajes */
    private JLabel score;
    private JLabel moves;
    /* Tablero */
    private JPanel board;
    private Canvas canvas;
    private Tile[][] cells;
    /* Botones */
    private JButton up;
    private JButton down;
    private JButton left;
    private JButton right;
    private JButton play;
    private JButton restart;
    private JButton changeSize;

    /* Movimientos y puntajes */
    private int moveCount;
    private int scoreCount;

    /**
     * Constructor de Clustering\_GUI.
     * Inicializa los elementos y acciones de la interfaz gráfica.
     */
    public Clustering_GUI(){
        this.prepareElements();
        this.game = new Clustering(cells);
        this.prepareActions();
        this.setVisible(true);
        this.isGameStarted = false;
    }

    /**
     * Método principal para iniciar la aplicación.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args){
        Clustering_GUI gui = new Clustering_GUI();
    }

    /**
     * Prepara los elementos de la interfaz gráfica.
     */
    private void prepareElements(){
        this.setTitle("Clustering");
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        config = new JMenu("Configuracion");
        menuBar.add(menu);
        menuBar.add(config);
        createTileMenuItem = new JMenuItem("Crear ficha");
        menu.add(createTileMenuItem);
        nuevo = new JMenuItem("Nuevo");
        menu.add(nuevo);
        open = new JMenuItem("Abrir");
        menu.add(open);
        save = new JMenuItem("Salvar");
        menu.add(save);
        deleteTileMenuItem = new JMenuItem("Borrar ficha");
        menu.add(deleteTileMenuItem);
        exitGameMenuItem = new JMenuItem("Salir");
        menu.add(exitGameMenuItem);
        this.setJMenuBar(menuBar);
        this.setSize(PREFERED_DIMENSION);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel movementPanel = new JPanel();
        movementPanel.setLayout(new GridLayout(3,3,5,5));
        movementPanel.setPreferredSize(new Dimension(200,200));
        movementPanel.setBorder(new EmptyBorder(20,20,20,20));
        up = new JButton("↑");
        down = new JButton ("↓");
        left = new JButton("←");
        right = new JButton("→");
        movementPanel.add(new JLabel());
        movementPanel.add(up);
        movementPanel.add(new JLabel());
        movementPanel.add(left);
        movementPanel.add(down);
        movementPanel.add(right);
        movementPanel.add(new JLabel());
        movementPanel.add(new JLabel());
        movementPanel.add(new JLabel());
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(movementPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(new EmptyBorder(120,30,30,50));
        score = new JLabel("Puntaje: 0");
        moves = new JLabel("Movimientos: 0");
        infoPanel.add(score);
        infoPanel.add(moves);
        this.add(infoPanel, BorderLayout.EAST);

        prepareElementsBoard(10,10);

        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        play = new JButton("Jugar");
        restart = new JButton("Reiniciar");
        changeSize = new JButton("Cambiar Tamaño");
        startPanel.setBorder(new EmptyBorder(10,10,10,10));
        play.setPreferredSize(new Dimension(100,50));
        startPanel.add(play);
        startPanel.add(Box.createVerticalStrut(10));
        startPanel.add(restart);
        startPanel.add(Box.createVerticalStrut(10));
        startPanel.add(changeSize);
        bottomPanel.add(startPanel, BorderLayout.WEST);

        up.setEnabled(false);
        down.setEnabled(false);
        left.setEnabled(false);
        right.setEnabled(false);
    }

    /**
     * Prepara el tablero de juego con el tamaño especificado.
     *
     * @param m Número de filas.
     * @param n Número de columnas.
     */
    public void prepareElementsBoard(int m, int n) {
        if (board != null) {
            this.remove(board);
        }
        board = new JPanel(new GridLayout(m, n, 5, 5));
        board.setBorder(new EmptyBorder(10, 10, 10, 10));

        cells = new Tile[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                Tile cell = new Tile();
                cell.setBackground(getRandomColor());

                cell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color selectedColor = JColorChooser.showDialog(null, "Selecciona un color", cell.getBackground());
                        if (selectedColor != null && !cell.getBackground().equals(Color.WHITE)) {
                            cell.setBackground(selectedColor);
                        }
                    }
                });

                cells[row][col] = cell;
                board.add(cell);
            }
        }

        this.add(board, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    /**
     * Refresca el estado del tablero de juego.
     */
    private void refresh() {
        Tile[][] boardState = game.getBoard();
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                cells[i][j].setBackground(boardState[i][j].getBackground());
            }
        }
    }

    /**
     * Prepara las acciones de los elementos de la interfaz gráfica.
     */
    private void prepareActions() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                setVisible(false);
                System.exit(0);
            }
        });

        exitGameMenuItem.addActionListener(e->{
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Estas seguro de que quieres salir?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        });

        open.addActionListener(e->{
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION){
                File selectedFile = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(null,
                        "Abrir archivo: " + selectedFile.getName(),
                        "Abrir",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        save.addActionListener(e->{
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION){
                File selectedFile = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(null,
                        "Guardar archivo: " + selectedFile.getName(),
                        "Guardar",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        up.addActionListener(e -> {
            game.moveUp();
            incrementMoves();
            refresh();
            updateScore();
        });
        down.addActionListener(e -> {
            game.moveDown();
            incrementMoves();
            refresh();
            updateScore();
        });
        left.addActionListener(e -> {
            game.moveLeft();
            incrementMoves();
            refresh();
            updateScore();
        });
        right.addActionListener(e -> {
            game.moveRight();
            incrementMoves();
            refresh();
            updateScore();
        });

        play.addActionListener(e -> {
            game = new Clustering(cells);
            refresh();
            isGameStarted = true;
            up.setEnabled(true);
            down.setEnabled(true);
            left.setEnabled(true);
            right.setEnabled(true);
        });

        restart.addActionListener(e -> {
            prepareElementsBoard(10, 10);
            game = new Clustering(cells);
            moveCount = 0;
            scoreCount = 0;
            moves.setText("Movimientos: " + moveCount);
            score.setText("Puntaje: " + scoreCount);
            refresh();
        });

        changeSize.addActionListener(e -> {
            String rows = JOptionPane.showInputDialog("Ingrese el número de filas:");
            String cols = JOptionPane.showInputDialog("Ingrese el número de columnas:");
            if (rows != null && cols != null) {
                try {
                    int m = Integer.parseInt(rows);
                    int n = Integer.parseInt(cols);
                    prepareElementsBoard(m, n);
                    game = new Clustering(cells);
                    moveCount = 0;
                    scoreCount = 0;
                    moves.setText("Movimientos: " + moveCount);
                    score.setText("Puntaje: " + scoreCount);
                    refresh();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese números válidos.");
                }
            }
        });
    }

    /**
     * Obtiene un color aleatorio de una lista de colores predefinidos.
     *
     * @return Un color aleatorio.
     */
    private Color getRandomColor() {
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.WHITE};
        int randomIndex = (int) (Math.random() * colors.length);
        return colors[randomIndex];
    }

    /**
     * Incrementa el contador de movimientos y actualiza la etiqueta correspondiente.
     */
    private void incrementMoves() {
        moveCount++;
        moves.setText("Movimientos: " + moveCount);
    }

    /**
     * Actualiza la puntuación y la etiqueta correspondiente.
     */
    private void updateScore() {
        scoreCount = game.calculateScore();
        score.setText("Puntaje: " + scoreCount);
    }
}