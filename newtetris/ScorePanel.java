package GUI_Tetris;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
  private static final long serialVersionUID = 1L;

  private JLabel textScore;
  private JLabel actualScore;
  private BoxLayout layout;

  public ScorePanel() {
    textScore = new JLabel("Score: ");

    actualScore = new JLabel("0");

    textScore.setFont(TetrisFont.getFont());
    actualScore.setFont(TetrisFont.getFont());

    textScore.setForeground(TetrisFont.getColor());
    actualScore.setForeground(TetrisFont.getColor());

    layout = new BoxLayout(this, BoxLayout.Y_AXIS);
    setLayout(layout);

    add(textScore);
    add(actualScore);
  }

  public void refreshScore(int nr) {

    actualScore.setText(String.valueOf(nr));

    actualScore.setVisible(false);
    actualScore.setVisible(true);
  }
}
