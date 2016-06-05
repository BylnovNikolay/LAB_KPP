package HeadPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JPanel;

public class MainMenu extends JFrame {
	
  private static final long serialVersionUID = 1L;
  private JButton Play;
  private JButton AutoMode;
  private JButton Statistic;
  private GridBagLayout gbag;
  private GridBagConstraints gbc;
  private static BackGround back;
  private static JLabel newGameLabel;
  private static JLabel replayLabel;
  private static JLabel settingsLabel;
  private static JLabel exitLabel;
  private final int FRAME_WIDTH = 420;
  private final int FRAME_HIGHT = 625;
  protected static JFrame firstFrame;
  private final Color LABELS_COLOR = new Color( 255, 255, 255);
  private final Color MOUSE_ENTERED_COLOR = new Color(230, 190, 130); 
  Font FONT = new Font("Modern No. 20", Font.CENTER_BASELINE, 30);
  private static JLabel background;
  private GameThread m;
  
  public MainMenu() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(FRAME_WIDTH,FRAME_HIGHT);
    setResizable(false);
    setLocationRelativeTo(null);     
    gbag = new GridBagLayout();
    gbc = new GridBagConstraints();
    setLayout(gbag); 
  
    
    newGameLabel = new JLabel("New Game");
    replayLabel = new JLabel("Replay");
    settingsLabel = new JLabel("Statistics"); 
    exitLabel = new JLabel("Exit");
    
    newGameLabel.setFont(FONT);
    replayLabel.setFont(FONT);
    settingsLabel.setFont(FONT);
    exitLabel.setFont(FONT);   
    
    newGameLabel.setForeground(LABELS_COLOR);
    replayLabel.setForeground(newGameLabel.getForeground());
    settingsLabel.setForeground(newGameLabel.getForeground());
    exitLabel.setForeground(newGameLabel.getForeground());    
    
    back = new BackGround();
    add(back);
    back.setLayout(gbag);   
    createscene(); 
    setVisible(true);
  }
  
  class BackGround extends JLabel {
		 // public void paintComponent(Graphics g) {
	  	BackGround() {
		      Image im = null;	     
		      setSize( FRAME_WIDTH , FRAME_HIGHT);
		      try {
		        im = ImageIO.read(new File("3.jpg"));
		      } catch (IOException e) {
		      }
		      int ka1 = im.getWidth(null);
		      int ka2 = im.getHeight(null); 
		      int la1 = getWidth();
		      int la2 = getHeight()-1;	         
		        
		    
		       
		       float resX = ka1 / la2;
		       float resY = ka2 / la1; 
		       float scaleX1 = ka1 / resX;
		       float scaleY1 = ka2 / resY;
		       
		       int scaleX = (int) Math.round(scaleY1);
		       int scaleY = (int) Math.round(scaleX1);	             
		               
		         im.getScaledInstance(scaleX, scaleY, im.SCALE_DEFAULT);
		         Icon icon = new ImageIcon(im.getScaledInstance(scaleX, scaleY, im.SCALE_DEFAULT));	         
		    
		        setIcon((icon));     
		  
		    }
	  } 
 
  
  public void createscene() {	 
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = GridBagConstraints.RELATIVE;
		gbc.gridy = GridBagConstraints.RELATIVE;
		JLabel tetris = new JLabel("Tetris!");	
		tetris.setForeground(newGameLabel.getForeground());
		tetris.setFont(FONT);
		gbc.insets = new Insets(20, 0, 0, 300);
	    gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbag.setConstraints(tetris, gbc);
		//back.add(tetris);	      
		gbag.setConstraints(newGameLabel, gbc);
		gbc.insets = new Insets(20, 0, 0, 300);
		back.add(newGameLabel);
		gbc.insets = new Insets(20, 0, 0, 300);
		gbag.setConstraints(replayLabel, gbc);
		back.add(replayLabel);
		gbc.insets = new Insets(20, 0, 0, 300);
		gbag.setConstraints(settingsLabel , gbc);   
		back.add(settingsLabel);  
		gbc.insets = new Insets(20, 0, 0, 300);
		gbag.setConstraints(exitLabel, gbc);   
		back.add(exitLabel);  
		replayLabel.addMouseListener(new ReplayLabelListener());
	    settingsLabel.addMouseListener(new SettingsLabelListener());		   
		exitLabel.addMouseListener(new ExitLabelListener());
		newGameLabel.addMouseListener(new NewGameLabelListener());
  }  
  
  public class ExitLabelListener implements MouseListener {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	      setVisible(false);
	      System.exit(0);
	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {
	      exitLabel.setForeground(MOUSE_ENTERED_COLOR);
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
	      exitLabel.setForeground(LABELS_COLOR);
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {}

	    @Override
	    public void mouseReleased(MouseEvent e) {}
	  }
  
	  public class SettingsLabelListener implements MouseListener {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	    	try {
				new Statistics();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {
	      settingsLabel.setForeground(MOUSE_ENTERED_COLOR);
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
	      settingsLabel.setForeground(LABELS_COLOR);
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {}

	    @Override
	    public void mouseReleased(MouseEvent e) {}
	  }
	  
	  public class ReplayLabelListener implements MouseListener {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	    	 dispose();	   	  
	   	      try {
				ReplayCollection m = new ReplayCollection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	   	 
	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {
	      replayLabel.setForeground(MOUSE_ENTERED_COLOR);
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
	      replayLabel.setForeground(LABELS_COLOR);
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {}

	    @Override
	    public void mouseReleased(MouseEvent e) {}
	  }
	
	  public class NewGameLabelListener implements MouseListener {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	    	if(m !=null) {
	    		System.out.println("killed!!");	    		
	    	}
	        dispose();	        
	        m = new GameThread(1, "action");	     
	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {
	      newGameLabel.setForeground(MOUSE_ENTERED_COLOR);
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
	      newGameLabel.setForeground(LABELS_COLOR);
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {}

	    @Override
	    public void mouseReleased(MouseEvent e) {}
	  }

}



