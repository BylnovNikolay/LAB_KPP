package HeadPackage;


import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

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
  private int fl = 0;
  private GameData GD;
  static KeyEvent k;
  private int a;
  private int b;
  private int c;
  private volatile int flag;
  private String ReplayFile;
  private boolean save;
  private boolean start = true;
  private int[] key = {37, 39, 40, 16, 32, 37, 39, 37, 39};
  private long nowTime;
  private long nexTime;
  private long cur;
  private long prev;
  private replay RP;
  private long z;
  private KeyListener keyListener;
  private int keyCode; 
  public Controler(int indicator, String s) throws IOException {

    this.loose = false;
    this.fig = new RandomFigureGenerator();
    this.flag = indicator;
    this.save = false;
    this.ReplayFile = new String();
    this.ReplayFile = s; 
    twoFigures = new Figure[2];
    GD = new GameData();
    RP = new replay();

    // KEY LISTENERS
    keyListener = new KeyListener() {
      @Override
      public void keyPressed(KeyEvent e) {
        keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
          if (!BD.freshFigureMustStopLeft()) {
            if (flag == 1) {
              RP.SaveAction(7);
              time();
            }
            BD.freshFigureLeft();
            displayBoard();
          }
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
          if (!BD.freshFigureMustStopRight()) {
            if (flag == 1) {
              RP.SaveAction(8);
              time();
            }
            BD.freshFigureRight();
            displayBoard();
          }
        }

        if (keyCode == KeyEvent.VK_DOWN) {
          if (!BD.freshFigureMustStopDown()) {
            if (flag == 1) {
              RP.SaveAction(9);
              time();
            }
            BD.freshFigureDown();
            displayBoard();
          }
        }

        if (keyCode == KeyEvent.VK_SHIFT) {
          while (!BD.freshFigureMustStopDown()) {
            BD.freshFigureDown();
          }
          if (flag == 1) {
            RP.SaveAction(10);
            time();
          }
          displayBoard();
        }

        if (keyCode == KeyEvent.VK_SPACE) {
          BD.getFreshFigure().rotate(Board.getBlackWhite());
          if (flag == 1) {
            RP.SaveAction(11);
            time();
          }
          displayBoard();
        }
        if (keyCode == KeyEvent.VK_ALT) {
          if (fl == 0)
            fl = 1;
          else
            fl = 0;
        }
        if (keyCode == KeyEvent.VK_L) {
          GameData.level();
        }
        if (keyCode == KeyEvent.VK_E) {
          flag = 1;
        }
        if (keyCode == KeyEvent.VK_R) {
          flag = 0;
          System.out.println("R pressed!");
        }
        if (keyCode == KeyEvent.VK_S) {
          save = true;
          System.out.println("save == true");
        }
        e.consume();
      }

      @Override
      public void keyReleased(KeyEvent arg0) {}

      @Override
      public void keyTyped(KeyEvent arg0) {}
    };


  }

  @SuppressWarnings({"serial", "deprecation"})
  public void start() throws FileNotFoundException, IOException {
    this.MG = new MainGraph();
    this.BD = new Board();
    MG.addKeyListener(keyListener);

    while (start == true) {
      if (flag == 1 || flag == 0) {
        break;
      } else {
        System.out.println(flag);
      }
    }

    if (flag == 1) {
      twoFigures[0] = fig.giveRandomFigure();
      RP.SaveFigure(fig.getnmb());
      twoFigures[1] = fig.giveRandomFigure();
      RP.SaveFigure(fig.getnmb());
    }
    if (flag == 0) {
      RP.ReadFile(ReplayFile);
      twoFigures[0] = FigureOut();
      twoFigures[1] = FigureOut();
      new NewThread(keyListener, ReplayFile);
    }
    // ADDING A NEW FIGURE TO THE BOARD
    BD.addFigure(twoFigures[1]);
   

    // SET TO DISPLAY NEXT FIGURE - twoFigures[0];
    MG.getInfoPanel().getNextFigurePanel().refreshNextFigure(twoFigures[0]);

    // TIMER
    nowTime = 0;
    nexTime = 0;
    z = 0;
    a = 0;
    b = 0;
    c = 0;
    k = new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, z, 0, KeyEvent.VK_SHIFT);
    displayBoard();
    // GAME LOOP
    while (loose == false) {
      nowTime = System.currentTimeMillis();
      while (nexTime - GD.getMiliseconds() < nowTime) {
        nexTime = System.currentTimeMillis();
      }
      if (fl == 1) {
        bot();
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
        if (flag == 1) {
          twoFigures[1] = twoFigures[0];
          twoFigures[0] = fig.giveRandomFigure();
          RP.SaveFigure(fig.getnmb());
        }
        if (flag == 0) {
          twoFigures[1] = twoFigures[0];
          twoFigures[0] = FigureOut();
        }
        BD.addFigure(twoFigures[1]);

        MG.getInfoPanel().getNextFigurePanel().refreshNextFigure(twoFigures[0]);

        GD.dataRefresh();
        System.out.println(GD.changedLevel());
        if (GD.changedLevel()) {
          MG.getInfoPanel().getLevelPanel().setLevel(GD.getLevel());
        }
      }
      displayBoard();
      if (BD.verifyWin()) {
        loose = true;
        if (save == true && flag == 1) {
          RP.WriteFile(GD.getPoints());
          System.out.println("saved!");
        }
        JOptionPane.showMessageDialog(MG,
            "The end \n your score is: " + GD.getPoints() + "\n your level is: " + GD.getLevel(),
            "", JOptionPane.PLAIN_MESSAGE);
        MG.dispose();
        new MainMenu();
      }
      // Board.printXRay();
    }
  }
  
  public void startreplay() {
    k.setKeyCode(82);
    keyListener.keyPressed(k);
    System.out.println("**************pressed!&&&&&&&&&&&&&&&&&&&&");
  }
  private void bot() {
    int i = 0;
    Random r = new Random();
    i = r.nextInt(9);
    // System.out.println(i);
    k.setKeyCode(key[i]);
    keyListener.keyPressed(k);
    return;
  }

  private void time() {
    long res;
    if (prev == 0) {
      prev = System.currentTimeMillis();
    }
    cur = System.currentTimeMillis();
    res = Math.abs(cur - prev);
    prev = cur;
    RP.SaveTime(res);
  }

  private Figure FigureOut() {
    return fig.giveFigure(RP.GetFigure());
  }

  public void SetReplay() {
    this.flag = 0;
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


class NewThread implements Runnable {
  Thread t;
  private KeyListener KL;
  private replay RP;
  static KeyEvent k;
  private String ReplayFile;

  @SuppressWarnings({"deprecation", "serial"})
  NewThread(KeyListener Key, String ReplayFile) {
    t = new Thread(this, "son");
    t.start();
    this.KL = Key;
    this.ReplayFile = ReplayFile;
    k = new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_SHIFT);
  }

  public void run() {
    try {
      RP = new replay();
      RP.ReadFile(ReplayFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < RP.GetNum(); i++) {
      int act;
      long delay = RP.GetTime();
      try {
        Thread.sleep(delay);
      } catch (InterruptedException e) {
        // Handle exception
      }
      act = RP.GetAction();
      // left
      if (act == 7) {
        k.setKeyCode(37);
        KL.keyPressed(k);
      }
      // right
      if (act == 8) {
        k.setKeyCode(39);
        KL.keyPressed(k);
      }
      if (act == 9) {
        k.setKeyCode(40);
        KL.keyPressed(k);
      }
      if (act == 10) {
        k.setKeyCode(16);
        KL.keyPressed(k);
      }
      if (act == 11) {
        k.setKeyCode(32);
        KL.keyPressed(k);
      }
      // System.out.println(i);
    }
  }
}

