package HeadPackage;

import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import Board_Tetris.Board;
import Figure.Figure;
import GUI_Tetris.MainGraph;
import GameData.GameData;
import java.awt.Component;

public class Client implements Runnable {
  private MainGraph MG;
  private Board BD;
  // private KeyListener keyListener;
  private GameData GD;
  Thread t;
  private int a;
  private int b;
  private int c;
  private KeyListener KeyListner;
  final static int HEIGHT = 20;
  final static int WIDTH = 10;
  private volatile boolean  Refresh;
  private volatile boolean  Finish;
  private Figure[] twoFigures;

  Client(KeyListener KL) {
    t = new Thread(this, "client");
    this.MG = new MainGraph();
    this.BD = new Board();
    this.GD = new GameData();
    this.KeyListner = KL;
    MG.addKeyListener(this.KeyListner);
    this.Refresh = false;
    this.Finish = false;
    twoFigures = new Figure[2];
    t.start();
  }

  synchronized public void finish() {
    this.Finish = true;
  }

  synchronized public void fresh() {
    this.Refresh = true;
    System.out.println(this.Refresh);
  }

  synchronized public boolean freshFigureMustStopLeft() {
    return BD.freshFigureMustStopLeft();
  }

  synchronized public void freshFigureLeft() {
    BD.freshFigureLeft();
  }

  synchronized public boolean freshFigureMustStopRight() {
    return BD.freshFigureMustStopRight();
  }

  synchronized public void freshFigureRight() {
    BD.freshFigureRight();
  }

  synchronized public boolean freshFigureMustStopDown() {
    return BD.freshFigureMustStopDown();
  }

  synchronized public void freshFigureDown() {
    BD.freshFigureDown();
  }

  synchronized public void rotate() {
    BD.getFreshFigure().rotate(Board.getBlackWhite());
  }

  synchronized public void cleanLinesIfNeeded() {
    BD.cleanLinesIfNeeded();
  }

  synchronized public void putDownBlocksIfNeeded() {
    BD.putDownBlocksIfNeeded();
  }

  synchronized public boolean verifyWin() {
    return BD.verifyWin();
  }

  public long getMiliseconds() {
    return GD.getMiliseconds();
  }

  synchronized public void addPoints() {
    GD.addPoints();
  }

  synchronized public void dataRefresh() {
    GD.dataRefresh();
  }

  synchronized public void addFigure(Figure n, Figure z) {
    twoFigures[0] = n;
    twoFigures[1] = z;
    BD.addFigure(twoFigures[1]);
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

  public void run() {
    while (this.Finish == false) {
      while (this.Refresh == false) 
       try {
          t.wait();
        } catch (InterruptedException e) {
          System.out.println("exception catched!");
        }
      
      System.out.println("refreshed!");
      MG.getInfoPanel().getScorePanel().refreshScore(GD.getPoints());
      MG.getInfoPanel().getLevelPanel().setLevel(GD.getLevel());
      MG.getInfoPanel().getNextFigurePanel().refreshNextFigure(twoFigures[0]);
      displayBoard();
      this.Refresh = false; 
      System.out.println(this.Refresh);
    }
    JOptionPane.showMessageDialog(MG,
        "The end \n your score is: " + GD.getPoints() + "\n your level is: " + GD.getLevel(),
        "", JOptionPane.PLAIN_MESSAGE);
  }  
}
