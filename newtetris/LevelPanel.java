package GUI_Tetris;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelPanel extends JPanel {
  private static final long serialVersionUID = 1L;

  private JLabel text;
  private JLabel level;
  private BoxLayout layout;

  public LevelPanel() {
    text = new JLabel("Level:");
    level = new JLabel("I");

    text.setFont(TetrisFont.getFont());
    level.setFont(TetrisFont.getFont());

    text.setForeground(TetrisFont.getColor());
    level.setForeground(TetrisFont.getColor());

    layout = new BoxLayout(this, BoxLayout.Y_AXIS);

    setLayout(layout);

    add(text);
    add(level);
  }

  public void setLevel(String level) {
    this.level.setText(level);
    this.level.setVisible(false);
    this.level.setVisible(true);
  }
}
