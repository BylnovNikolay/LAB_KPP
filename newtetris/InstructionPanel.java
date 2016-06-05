package GUI_Tetris;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class InstructionPanel extends JPanel {
  private static final long serialVersionUID = 1L;

  private JLabel text1 = new JLabel("use arrow keys");
  private JLabel text2 = new JLabel("space bar");
  private JLabel text3 = new JLabel("and shift");

  public InstructionPanel() {
    add(text1);
    add(text2);
    add(text3);
  }
}
