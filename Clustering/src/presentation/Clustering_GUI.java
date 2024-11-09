package Clustering.src.presentation;

import javax.swing.*;
import Clustering.src.domain.Clustering;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Clustering_GUI extends JFrame {

    private final Clustering game;
    private static final Dimension PREFERED_DIMENSION = new Dimension(1000,700);
    /*Barra superior*/
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu config;
    private JMenuItem createTileMenuItem;
    private JMenuItem deleteTileMenuItem;
    private JMenuItem exitGameMenuItem;

    /*Scores*/
    private JLabel textScore;
    private JLabel score;
    private JLabel textMoves;
    private JLabel moves;

    public Clustering_GUI(){
        this.game = new Clustering();
        this.prepareElements();
        this.prepareActions();
        this.setVisible(true);
    }


    public static void main(String[] args){
        Clustering_GUI gui = new Clustering_GUI();
    }

    private void prepareElements(){
        this.setTitle("Clustering");
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        config = new JMenu("Configuracion");
        menuBar.add(menu);
        menuBar.add(config);
        createTileMenuItem = new JMenuItem("Create Tile");
        menu.add(createTileMenuItem);
        deleteTileMenuItem = new JMenuItem("Delete Tile");
        menu.add(deleteTileMenuItem);
        exitGameMenuItem = new  JMenuItem("Exit");
        menu.add(exitGameMenuItem);
        this.setJMenuBar(menuBar);
        this.setSize(PREFERED_DIMENSION);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void prepareActions() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                setVisible(false);
                System.exit(0);
            }
        });
    }

}
