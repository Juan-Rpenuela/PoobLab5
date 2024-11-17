package Clustering.test;

import Clustering.src.domain.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.*;

public class ClusteringTest {
    private Clustering clustering;
    private Tile[][] board;

    @Before
    public void setUp() {
        board = new Tile[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Tile();
                board[i][j].setBackground(Color.WHITE);
            }
        }
        clustering = new Clustering(board);
    }

    @After
    public void tearDown() {
        // Teardown code if needed
    }

    @Test
    public void shouldMoveUp_movesTileToEmptySpace() {
        board[2][1].setBackground(Color.RED);
        clustering.moveUp();
        assertEquals(Color.RED, board[1][1].getBackground());
        assertEquals(Color.WHITE, board[2][1].getBackground());
    }

    @Test
    public void shouldMoveDown_movesTileToEmptySpace() {
        board[0][1].setBackground(Color.RED);
        clustering.moveDown();
        assertEquals(Color.RED, board[1][1].getBackground());
        assertEquals(Color.WHITE, board[0][1].getBackground());
    }

    @Test
    public void shouldMoveLeft_movesTileToEmptySpace() {
        board[1][2].setBackground(Color.RED);
        clustering.moveLeft();
        assertEquals(Color.RED, board[1][1].getBackground());
        assertEquals(Color.WHITE, board[1][2].getBackground());
    }

    @Test
    public void shouldMoveRight_movesTileToEmptySpace() {
        board[1][0].setBackground(Color.RED);
        clustering.moveRight();
        assertEquals(Color.RED, board[1][1].getBackground());
        assertEquals(Color.WHITE, board[1][0].getBackground());
    }

    @Test
    public void shouldNotMoveUp_doesNotMoveTileToNonEmptySpace() {
        board[2][1].setBackground(Color.RED);
        board[1][1].setBackground(Color.BLUE);
        clustering.moveUp();
        assertEquals(board[2][1].getBackground(), board[2][1].getBackground());
        assertEquals(board[1][1].getBackground(), board[1][1].getBackground());
    }

    @Test
    public void shouldNotMoveDown_doesNotMoveTileToNonEmptySpace() {
        board[0][1].setBackground(Color.RED);
        board[1][1].setBackground(Color.BLUE);
        clustering.moveDown();
        assertEquals(board[0][1].getBackground(), board[0][1].getBackground());
        assertEquals(board[1][1].getBackground(), board[1][1].getBackground());
    }

    @Test
    public void shouldNotMoveLeft_doesNotMoveTileToNonEmptySpace() {
        board[1][2].setBackground(Color.RED);
        board[1][1].setBackground(Color.BLUE);
        clustering.moveLeft();
        assertEquals(board[1][2].getBackground(), board[1][2].getBackground());
        assertEquals( board[1][1].getBackground(), board[1][1].getBackground());
    }

    @Test
    public void shouldNotMoveRight_doesNotMoveTileToNonEmptySpace() {
        board[1][0].setBackground(Color.RED);
        board[1][1].setBackground(Color.BLUE);
        clustering.moveRight();
        assertEquals( board[1][0].getBackground(), board[1][0].getBackground());
        assertEquals(board[1][1].getBackground(), board[1][1].getBackground());
    }
}