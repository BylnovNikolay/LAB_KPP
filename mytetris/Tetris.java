package com.ssaurel.mytetris;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

  public class Tetris extends JFrame {
    private final static int WIDTH = 200;
    private final static int HEIGHT = 400;	
    private JLabel statusBar;  
    public Tetris() {    
      statusBar = new JLabel("0"); // to display lines number
	  add(statusBar, BorderLayout.SOUTH);
	  Board board = new Board(this);
	  add(board);
	  // start lines down
	  board.start();		
	  setSize(WIDTH,HEIGHT);
	  setTitle("My Tetris : ");
	  setDefaultCloseOperation(EXIT_ON_CLOSE);
    }	
    public JLabel getStatusBar() {
      return statusBar;
    }	
    public static void main(String[] args) {
      Tetris myTetris = new Tetris();
	  myTetris.setLocationRelativeTo(null); // center
	  myTetris.setVisible(true);
    }
  }
