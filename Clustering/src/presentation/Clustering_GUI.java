package Clustering.src.presentation;

import javax.swing.*;
import Clustering.src.domain.Clustering;

import java.awt.*;

public class Clustering_GUI extends JFrame {

    private static final Dimension PREFERED_DIMENSION = new Dimension(10000,1000);

    private final Clustering game;

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
    }


    public static void main(String[] args){
        Clustering_GUI gui = new Clustering_GUI();
        gui.setVisible(true);
    }

    private void prepareElements(){
        JFrame frame = new JFrame("Clustering game");
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuBar.add(menu);
        createTileMenuItem = new JMenuItem("Create Tile");
        menu.add(createTileMenuItem);
        deleteTileMenuItem = new JMenuItem("Delete Tile");
        menu.add(deleteTileMenuItem);
        exitGameMenuItem = new  JMenuItem("Exit");
        menu.add(exitGameMenuItem);
        frame.setJMenuBar(menuBar);
        frame.setSize(350,250);
        frame.setVisible(true);
    }

    private void prepareActions() {
    }

}
