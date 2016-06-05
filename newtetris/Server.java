package HeadPackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import Board_Tetris.Board;
import Board_Tetris.CleanLines;
import Figure.Figure;
import GameData.GameData;
import GameData.RandomFigureGenerator;

public class Server implements Runnable {
  private boolean loose;
  Thread t;
  private RandomFigureGenerator fig;
  private Figure[] twoFigures;
  private long nowTime;
  private long nexTime;
  private KeyListener keyListener;
  private int keyCode;
  private Client CL;

  public Server() {
    t = new Thread(this, "client");
    this.loose = false;
    this.fig = new RandomFigureGenerator();
    twoFigures = new Figure[2];
    keyListener = new KeyListener() {
      @Override
      public void keyPressed(KeyEvent e) {
        keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
          if (!CL.freshFigureMustStopLeft()) {
            CL.freshFigureLeft();
            CL.fresh();
          }
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
          if (!CL.freshFigureMustStopRight()) {
            CL.freshFigureRight();
            CL.fresh();
          }
        }

        if (keyCode == KeyEvent.VK_DOWN) {
          if (!CL.freshFigureMustStopDown()) {
            CL.freshFigureDown();
            CL.fresh();
          }
        }

        if (keyCode == KeyEvent.VK_SHIFT) {
          while (!CL.freshFigureMustStopDown()) {
            CL.freshFigureDown();
          }
          CL.fresh();
        }
        if (keyCode == KeyEvent.VK_SPACE) {         
          CL.rotate();          
          CL.fresh();
        }
        e.consume();
      }

      @Override
      public void keyReleased(KeyEvent arg0) {}

      @Override
      public void keyTyped(KeyEvent arg0) {}
    };
    nowTime = 0;
    nexTime = 0;
    CL = new Client(keyListener);
    t.start();     
  }

  public void run() {
    twoFigures[0] = fig.giveRandomFigure();
    twoFigures[1] = fig.giveRandomFigure();
    CL.addFigure(twoFigures[0], twoFigures[1]);
    CL.fresh();
    while (loose == false) {
      nowTime = System.currentTimeMillis();
      while (nexTime - CL.getMiliseconds() < nowTime) {
        System.out.println("playing!");
        nexTime = System.currentTimeMillis();
      }
      if (!CL.freshFigureMustStopDown()) {
        CL.freshFigureDown();
        CL.fresh();
      } else {
        CL.cleanLinesIfNeeded();// CLEAN LINES IF LINE FULL
        CL.putDownBlocksIfNeeded();

        // SCORE!
        if (CleanLines.getScoreNr() > 0) {
          for (int i = 0; i < CleanLines.getScoreNr(); i++) {
            CL.addPoints();
          }
        }
        twoFigures[1] = twoFigures[0];
        twoFigures[0] = fig.giveRandomFigure();
        CL.addFigure(twoFigures[0], twoFigures[1]);
        CL.dataRefresh();
        CL.fresh();

        if (CL.verifyWin()) {
          loose = true;
          CL.finish();          
        }       
      }
    }
  }
}
