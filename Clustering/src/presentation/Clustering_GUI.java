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

public class Clustering_GUI extends JFrame {

    private Clustering game;
    private boolean isGameStarted;
    private static final Dimension PREFERED_DIMENSION = new Dimension(1000,700);
    /*Barra superior*/
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu config;
    private JMenuItem createTileMenuItem;
    private JMenuItem deleteTileMenuItem;
    private JMenuItem exitGameMenuItem;
    private JMenuItem nuevo;
    private JMenuItem open;
    private JMenuItem save;
    /*Scores*/

    private JLabel score;
    private JLabel moves;
    /*Tablero*/
    private JPanel board;
    private Canvas canvas;
    private Tile[][] cells;
    /*Botones*/
    private JButton up;
    private JButton down;
    private JButton left;
    private JButton right;
    private JButton play;

    /*move And scores*/
    private int moveCount;
    private int scoreCount;


    public Clustering_GUI(){
        this.prepareElements();
        this.game = new Clustering(cells);
        this.prepareActions();
        this.setVisible(true);
        this.isGameStarted = false;
    }


    public static void main(String[] args){
        Clustering_GUI gui = new Clustering_GUI();
    }

    private void prepareElements(){
        this.setTitle("Clustering");
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        //ciclo1
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
        exitGameMenuItem = new  JMenuItem("Salir");
        menu.add(exitGameMenuItem);
        this.setJMenuBar(menuBar);
        this.setSize(PREFERED_DIMENSION);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel movementPanel = new JPanel();
        movementPanel.setLayout(new GridLayout(3,3,5,5));
        movementPanel.setPreferredSize(new Dimension(200,200));
        movementPanel.setBorder(new EmptyBorder(20,20,20,20));//añadimos un borde al panel de movimientos.
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
        bottomPanel.add(movementPanel, BorderLayout.EAST);//haacemos un panel a la derecha de la ventana y ubicamos el panel de movimientos a  la derecha.
        this.add(bottomPanel, BorderLayout.SOUTH);//ubicamos el panel de abajo en la parte inferior de la ventana.

        //Panel de puntajes
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(new EmptyBorder(120,30,30,50));
        score = new JLabel("Puntaje: 0");
        moves = new JLabel("Movimientos: 0");
        infoPanel.add(score);
        infoPanel.add(moves);
        this.add(infoPanel, BorderLayout.EAST);

//        prepareElementsBoard();
        prepareElementsBoard(10,10);

        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        play = new JButton("Jugar");
        startPanel.setBorder(new EmptyBorder(10,10,10,10));
        play.setPreferredSize(new Dimension(100,50));
        startPanel.add(play);
        bottomPanel.add(startPanel, BorderLayout.CENTER);

        //Deshabilitados los botones de movimiento antes de iniciar el juego
        up.setEnabled(false);
        down.setEnabled(false);
        left.setEnabled(false);
        right.setEnabled(false);

    }

    public void prepareElementsBoard(int m, int n) {
        // Inicializar el panel del tablero
        board = new JPanel(new GridLayout(m, n, 5, 5));
        board.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Inicializar la matriz de celdas
        cells = new Tile[m][n];

        // Llenar el tablero con celdas de colores aleatorios
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                Tile cell = new Tile();
                cell.setBackground(getRandomColor()); // inicializamos con un color aleatorio para cada celda

                // Añadir ActionListener a cada celda
                cell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color selectedColor = JColorChooser.showDialog(null, "Selecciona un color", cell.getBackground());
                        if (selectedColor != null && !cell.getBackground().equals(Color.WHITE)) {
                            cell.setBackground(selectedColor);
                        }

                    }
                });

                cells[row][col] = cell; // Guardamos la celda en la matriz
                board.add(cell);// Añadimos la celda al panel del tablero;
            }
        }

        // Añadir el panel de tablero a la ventana principal
        this.add(board, BorderLayout.CENTER);
    }
//    private void prepareElementsBoard(){
//        //Panel de tablero
//        board = new JPanel(new BorderLayout()); // Tablero con BorderLayout para ubicar el canva en el centro
//        board.setBorder(new EmptyBorder(15, 10, 0, 10)); // Añadir un margen alrededor del tablero
//        board.setPreferredSize(new Dimension(400, 400)); // Tamaño del tablero
//
//
//        initCanvas(400, 400);
//        board.add(canvas, BorderLayout.CENTER);
//
//
//        this.add(board, BorderLayout.CENTER);
//    }

    private void refresh() {
        Tile[][] boardState = game.getBoard();
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                cells[i][j].setBackground(boardState[i][j].getBackground());
            }
        }
    }



    private void prepareActions() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                setVisible(false);
                System.exit(0);
            }
        });

        //Acción de salir
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
        //ciclo2
        //Acción de abrir archivos
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
        //ciclo2
        //Acción de guardar archivos
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

        // Acciones de movimiento
        up.addActionListener(e -> {game.moveUp(
        );
            incrementMoves();
            refresh();
        });
        down.addActionListener(e -> {game.moveDown()
        ;
            incrementMoves();
            refresh();
        });
        left.addActionListener(e -> {game.moveLeft()
        ;
            incrementMoves();
            refresh();
        });
        right.addActionListener(e -> {game.moveRight()
        ;
            incrementMoves();
            refresh();
        });
//ciclo6
        play.addActionListener(e -> {
            // Acción que se realiza cuando se presiona el botón play
            game = new Clustering(cells);
            refresh();
            isGameStarted = true; // El juego comienza
            // Habilita los botones de movimiento
            up.setEnabled(true);
            down.setEnabled(true);
            left.setEnabled(true);
            right.setEnabled(true);
        });
    }
    private Color getRandomColor() {
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.WHITE};
        int randomIndex = (int) (Math.random() * colors.length);
        return colors[randomIndex];
    }
    //ciclo6
    private void incrementMoves() {
        moveCount++;
        moves.setText("Movimientos: " + moveCount);
    }

    private void updateScore() {
        scoreCount = game.calculateScore();
        score.setText("Puntaje: " + scoreCount);
    }

//    public void initCanvas(int width, int height) {
//        this.canvas = new Canvas();
//        this.canvas.setSize(width, height);
//        canvas.setBackground(Color.BLACK);
//    }

}
