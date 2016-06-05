package GUI_Tetris;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class InfoPanel extends JPanel {
  private static final long serialVersionUID = 1L;

  private GridLayout layout;
  private ScorePanel scorePanel;
  private NextFigurePanel nextFigPanel;
  private LevelPanel levelPanel;
  private InstructionPanel instr;

  private Dimension panelDimension;

  public InfoPanel() {

    layout = new GridLayout(4, 1);

    scorePanel = new ScorePanel();
    nextFigPanel = new NextFigurePanel();
    levelPanel = new LevelPanel();
    instr = new InstructionPanel();

    panelDimension = new Dimension(120, 100);

    setLayout(layout);

    add(scorePanel);
    add(nextFigPanel);
    add(instr);
    add(levelPanel);


  }

  public ScorePanel getScorePanel() {
    return this.scorePanel;
  }

  public NextFigurePanel getNextFigurePanel() {
    return this.nextFigPanel;
  }

  public LevelPanel getLevelPanel() {
    return this.levelPanel;
  }

  @Override
  public Dimension getPreferredSize() {
    return this.panelDimension;
  }


}
