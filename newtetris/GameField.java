package GUI_Tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GameField extends JPanel {  
  private static final long serialVersionUID = 1L;
  private static final int HEIGHT = 20;
  private static final int WEIGHT = 10;
  private static final int SIZE = 45;
  private JPanel[][] gameF;
  private static Color[] colorz;
  
  public GameField() {          
    GridLayout layout = new GridLayout(HEIGHT, WEIGHT);      
    setLayout(layout);
    gameF = new JPanel[HEIGHT][WEIGHT];
    initializeGF();
    addGF();
    // writing paths to images
    colorz = new Color[7];
    colorz[0] = Color.BLUE;
    colorz[1] = Color.CYAN;
    colorz[2] = Color.GREEN;
    colorz[3] = Color.MAGENTA;
    colorz[4] = Color.ORANGE;
    colorz[5] = Color.RED;
    colorz[6] = Color.YELLOW;
  }
  
  private void initializeGF() {  
    for (int i = 0; i < HEIGHT; i++) {      
      for (int j = 0; j < WEIGHT; j++) {        
        gameF[i][j] = new JPanel() {
	      private static final long serialVersionUID = 1L;
	    
	      @Override public Dimension getPreferredSize() {
            return new Dimension(SIZE, SIZE);
          }
        };
        gameF[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
      }
    }
  }

  private void addGF() {   
    for (int i = 0; i < HEIGHT; i++) {      
      for (int j = 0; j < WEIGHT; j++) {         
        add(gameF[i][j]);
      }
    }   
  }

  public void colorGFPanelSq(int x, int y, int c) {   
    this.gameF[x][y].setBackground(colorz[c]);
  }
  
  public void uncolorGFPanelSq(int x, int y) {
    this.gameF[x][y].setBackground(Color.WHITE);
  }
  
  public static Color[] colors() {
    return colorz;
  }
}
