package GUI_Tetris;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import HeadPackage.GameThread;
import HeadPackage.MainMenu;
public class MainGraph extends JFrame {

  private static final long serialVersionUID = 1L;
  private JMenuBar menu;
  private JMenu gameMenu;
  private GameField GF;
  private InfoPanel INF;
  private GameThread m; 
  public MainGraph() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    JMenuBar menu = new JMenuBar();
    JMenu gameMenu = new JMenu("Game");
    JMenuItem newGameMenuItem = new JMenuItem("New Game");
    JMenuItem backToMenuItem = new JMenuItem("Back to menu");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    
    //gameMenu.add(newGameMenuItem);
    gameMenu.add(backToMenuItem);
    gameMenu.add(exitMenuItem);
    menu.add(gameMenu);
    setJMenuBar(menu);
    
    newGameMenuItem.addActionListener(new NewGameMenuItemListener());
    backToMenuItem.addActionListener(new BackToMenuItemListener());
    exitMenuItem.addActionListener(new ExitMenuItemListener());
    
    setSize(420, 625);
    setTitle("Tetris");
    setResizable(false);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());
    

    GF = new GameField();
    add(GF, BorderLayout.CENTER);

    INF = new InfoPanel();

    add(INF, BorderLayout.EAST);

    setFocusable(true);

    setVisible(true);
  }

  public GameField getMyGF() {
    return this.GF;
  }

  public void colorGFPanel(int x, int y, int i) {
    GF.colorGFPanelSq(x, y, i);
  }

  public void unColorGFPanel(int x, int y) {
    GF.uncolorGFPanelSq(x, y);
  }

  public InfoPanel getInfoPanel() {
    return this.INF;
  }
  
  class NewGameMenuItemListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0) {
	    	 dispose();	    	
		     m = new GameThread(1,"action");   
	    }
	  }

	
	 class ExitMenuItemListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0) {
	      setVisible(false);
	      System.exit(0);
	    }
	  }
	 class BackToMenuItemListener implements ActionListener {
		    public void actionPerformed(ActionEvent arg0) {	
		     		      
		    }
		  }
  
}
