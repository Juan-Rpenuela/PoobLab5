package Clustering.src.domain;

import javax.swing.JButton;
import java.awt.Color;

public class Tile extends JButton {
    public Tile() {
        super();
    }

    public void checkAndDisableIfWhite() {
        if (this.getBackground().equals(Color.WHITE)) {
            this.setEnabled(false);
        }
    }
}