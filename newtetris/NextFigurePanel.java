package GUI_Tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import Figure.*;

public class NextFigurePanel extends JPanel {
  private static final long serialVersionUID = 1L;

  private JPanel[][] boxes;
  private JPanel bigBox;
  private Color color;

  public NextFigurePanel() {

    color = new Color(333);

    bigBox = new JPanel();

    boxes = new JPanel[4][4];

    for (int i = 0; i < boxes.length; i++) {
      for (int j = 0; j < boxes[0].length; j++) {
        boxes[i][j] = new JPanel() {
          private static final long serialVersionUID = 1L;

          @Override
          public Dimension getPreferredSize() {
            return new Dimension(20, 20);
          }
        };

        boxes[i][j].setBackground(Color.LIGHT_GRAY);
      }
    }

    bigBox.setLayout(new GridLayout(5, 6));

    for (int i = 0; i < boxes.length; i++) {
      for (int j = 0; j < boxes[0].length; j++) {
        bigBox.add(boxes[i][j]);
      }
    }

    add(bigBox);
  }

  public void refreshNextFigure(Figure figure) {

    color = GameField.colors()[figure.getBlock(0).getTextureImage()];

    for (int i = 0; i < boxes.length; i++) {
      for (int j = 0; j < boxes[0].length; j++) {
        boxes[i][j].setBackground(Color.LIGHT_GRAY);
      }
    }

    if (figure instanceof Fig_I) {
      boxes[2][0].setBackground(color);
      boxes[2][1].setBackground(color);
      boxes[2][2].setBackground(color);
      boxes[2][3].setBackground(color);
    }
    if (figure instanceof Fig_J) {
      boxes[1][2].setBackground(color);
      boxes[2][2].setBackground(color);
      boxes[3][2].setBackground(color);
      boxes[3][1].setBackground(color);
    }
    if (figure instanceof Fig_L) {
      boxes[1][1].setBackground(color);
      boxes[2][1].setBackground(color);
      boxes[3][1].setBackground(color);
      boxes[3][2].setBackground(color);
    }
    if (figure instanceof Fig_O) {
      boxes[2][1].setBackground(color);
      boxes[2][2].setBackground(color);
      boxes[3][1].setBackground(color);
      boxes[3][2].setBackground(color);
    }
    if (figure instanceof Fig_S) {
      boxes[1][1].setBackground(color);
      boxes[2][1].setBackground(color);
      boxes[2][2].setBackground(color);
      boxes[3][2].setBackground(color);
    }
    if (figure instanceof Fig_T) {
      boxes[1][2].setBackground(color);
      boxes[2][1].setBackground(color);
      boxes[2][2].setBackground(color);
      boxes[3][2].setBackground(color);
    }
    if (figure instanceof Fig_Z) {
      boxes[1][2].setBackground(color);
      boxes[2][1].setBackground(color);
      boxes[2][2].setBackground(color);
      boxes[3][1].setBackground(color);
    }

    setVisible(false);
    setVisible(true);
  }

}
