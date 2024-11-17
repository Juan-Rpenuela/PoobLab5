package Clustering.src.domain;

import javax.swing.JButton;
import java.awt.Color;

/**
 * La clase Tile representa una sola ficha en el juego de agrupamiento.
 * Extiende JButton e incluye funcionalidad para deshabilitar la ficha si es blanca.
 */
public class Tile extends JButton {

    /**
     * Construye un objeto Tile.
     */
    public Tile() {
        super();
    }

    /**
     * Verifica si el color de fondo de la ficha es blanco y deshabilita la ficha si lo es.
     */
    public void checkAndDisableIfWhite() {
        if (this.getBackground().equals(Color.WHITE)) {
            this.setEnabled(false);
        }
    }
}