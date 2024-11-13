package Clustering.src.presentation;

import javax.swing.*;
import Clustering.src.domain.Clustering;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFileChooser;
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
    private JMenuItem nuevo;
    private JMenuItem open;
    private JMenuItem save;
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
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    }

}
