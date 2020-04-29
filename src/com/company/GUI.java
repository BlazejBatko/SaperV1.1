package com.company;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame {
    int spacing = 3;

    public int mx = -100;
    public int my = -100;

    Random rand = new Random();


    int[][] mines = new int[16][9];
    int[][] neighbours = new int[16][9];
    boolean[][] revealed = new boolean[16][9];
    boolean[][] flagged = new boolean[16][9];

    public GUI() {
        this.setTitle("Saper");
        this.setSize(1286, 829);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (rand.nextInt(100) < 20) {
                    mines[i][j] = 1;
                } else {
                    mines[i][j] = 0;
                }
                revealed[i][j] = false;
            }
        }


        Board board = new Board();
        this.setContentPane(board);

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);


    }

    public class Board extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.darkGray);
            g.fillRect(0, 0, 1280, 800);

            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 9; j++) {
                    g.setColor(Color.gray);

                    if (revealed[i][j] == true) {
                      g.setColor(Color.YELLOW);
                      }
                    if (mx >= spacing + i * 80 && mx < spacing + i * 80 + 80 - 2 * spacing && my >= spacing + j * 80 + 80 + 26 && my < spacing + j * 80 + 26 + 80 + 80 - 2 * spacing) {
                        g.setColor(Color.red);
                    }

                    g.fillRect(spacing + i * 80, spacing + j * 80 + 80, 80 - 2 * spacing, 80 - 2 * spacing);

                }
            }

        }
    }

    public class Move implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
           // System.out.println("Myszka sie ruszyla");
            mx = e.getX();
            my = e.getY();
          //  System.out.println("X: " + mx + " Y: " + my);

        }
    }

    class Click implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (inBoxX() != -1 && inBoxY() != -1){
                revealed[inBoxX()][inBoxY()] = true;
            }
            if (inBoxX() != -1 && inBoxY() != -1) {
                System.out.println("Kursor znajduje się na polu: [ " + inBoxX() + ", " + inBoxY()+"]");

            }
            else {
                System.out.println("Kursor nie jest na żadnym z pól");
            }


        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }

    public int inBoxX() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {

                if (mx >= spacing + i * 80 && mx < spacing + i * 80 + 80 - 2 * spacing && my >= spacing + j * 80 + 80 + 26 && my < spacing + j * 80 + 26 + 80 + 80 - 2 * spacing) {
                    return (i);
                }
            }
        }
        return (-1);
    }

    public int inBoxY() {
        for (int i = 0; i < 16; i++) {


            for (int j = 0; j < 9; j++) {

                if (mx >= spacing + i * 80 && mx < spacing + i * 80 + 80 - 2 * spacing && my >= spacing + j * 80 + 80 + 26 && my < spacing + j * 80 + 26 + 80 + 80 - 2 * spacing) {
                    return (j);
                }
            }

        }
        return -1;

    }
}