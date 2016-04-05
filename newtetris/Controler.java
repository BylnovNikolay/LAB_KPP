import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import Board_Tetris.Board;
import Board_Tetris.CleanLines;

import Figure.Figure;

import GUI_Tetris.MainGraph;

import GameData.GameData;
import GameData.RandomFigureGenerator;

public class Controler {
  private MainGraph MG;
  private Board BD;
  private boolean loose;
  private RandomFigureGenerator fig;
  final static int HEIGHT = 20;
  final static int WIDTH = 10;
  private Figure[] twoFigures;

  private GameData GD;

  private int a;
  private int b;
  private int c;

  private long nowTime;
  private long nexTime;

  private KeyListener keyListener;
  private int keyCode;

  public Controler() {
    this.MG = new MainGraph();
    this.BD = new Board();
    this.loose = false;
    this.fig = new RandomFigureGenerator();

    twoFigures = new Figure[2];

    GD = new GameData();

    twoFigures[0] = fig.giveRandomFigure();
    twoFigures[1] = fig.giveRandomFigure();

    // ADDING A NEW FIGURE TO THE BOARD
    BD.addFigure(twoFigures[1]);
    displayBoard();

    // SET TO DISPLAY NEXT FIGURE - twoFigures[0];
    MG.getInfoPanel().getNextFigurePanel().refreshNextFigure(twoFigures[0]);

    // KEY LISTENERS
    keyListener = new KeyListener() {

      @Override
      public void keyPressed(KeyEvent e) {

        keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
          if (!BD.freshFigureMustStopLeft()) {
            BD.freshFigureLeft();
            displayBoard();
          }
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
          if (!BD.freshFigureMustStopRight()) {
            BD.freshFigureRight();
            displayBoard();
          }
        }

        if (keyCode == KeyEvent.VK_DOWN) {
          if (!BD.freshFigureMustStopDown()) {
            BD.freshFigureDown();
            displayBoard();
          }
        }

        if (keyCode == KeyEvent.VK_SHIFT) {
          while (!BD.freshFigureMustStopDown()) {
            BD.freshFigureDown();
          }
          displayBoard();
        }

        if (keyCode == KeyEvent.VK_SPACE) {
          BD.getFreshFigure().rotate(Board.getBlackWhite());
          displayBoard();
        }
        e.consume();
      }

      @Override
      public void keyReleased(KeyEvent arg0) {}

      @Override
      public void keyTyped(KeyEvent arg0) {}
    };

    MG.addKeyListener(keyListener);

    // TIMER
    nowTime = 0;
    nexTime = 0;

    a = 0;
    b = 0;
    c = 0;
    // GAME LOOP
    while (loose == false) {
      nowTime = System.currentTimeMillis();

      while (nexTime - GD.getMiliseconds() < nowTime) {
        nexTime = System.currentTimeMillis();
      }

      if (!BD.freshFigureMustStopDown()) {
        BD.freshFigureDown();
      } else {
        BD.cleanLinesIfNeeded();// CLEAN LINES IF LINE FULL
        BD.putDownBlocksIfNeeded();

        // SCORE!
        if (CleanLines.getScoreNr() > 0) {
          for (int i = 0; i < CleanLines.getScoreNr(); i++) {
            GD.addPoints();
          }

          MG.getInfoPanel().getScorePanel().refreshScore(GD.getPoints());
        }

        twoFigures[1] = twoFigures[0];
        twoFigures[0] = fig.giveRandomFigure();

        BD.addFigure(twoFigures[1]);

        MG.getInfoPanel().getNextFigurePanel().refreshNextFigure(twoFigures[0]);

        GD.dataRefresh();

        if (GD.changedLevel()) {
          MG.getInfoPanel().getLevelPanel().setLevel(GD.getLevel());
        }

      }
      displayBoard();
      if (BD.verifyWin()) {
        loose = true;
        JOptionPane.showMessageDialog(MG,
            "The end \n your score is: " + GD.getPoints() + "\n your level is: " + GD.getLevel(),
            "", JOptionPane.PLAIN_MESSAGE);
      }
      // Board.printXRay();
    }
  }

  private void displayBoard() {
    // COLORING AND UNCOLORING THE BOXES
    // GET THE SPECIFIC BLOCK
    a = 0;
    b = 0;
    c = 0;

    for (int j = 0; j < BD.nrOfFigures(); j++) {
      for (int k = 0; k < BD.getFigureFromIndex(j).getNrOfBlocks(); k++) {
        a = BD.getFigureFromIndex(j).getBlockX(k);
        b = BD.getFigureFromIndex(j).getBlockY(k);
        c = BD.getFigureFromIndex(j).getBlockImg(k);
        // COLORING THE SPECIFIC BLOCK TO IT'S COLOR
        MG.colorGFPanel(b, a, c);
      }
    }

    for (int i = 0; i < HEIGHT; i++) {
      for (int v = 0; v < WIDTH; v++) {
        // IF THERE IS NO BLOCK; NEEDS TO BE (re)PAINTED IN WHITE
        if (!Board.xRay(i, v)) {
          MG.unColorGFPanel(i, v);
        }
      }
    }
  }
}
