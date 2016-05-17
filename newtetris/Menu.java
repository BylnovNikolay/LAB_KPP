import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends JFrame {
	
  private static final long serialVersionUID = 1L;
  private JButton Play;
  private JButton AutoMode;
  private JButton Statistic;
  private GridBagLayout gbag;
  private GridBagConstraints gbc;
  Font FONT = new Font("Modern No. 20", Font.CENTER_BASELINE, 30);
  
  public Menu() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(420, 625);
    setResizable(false);
    setLocationRelativeTo(null);
    gbag = new GridBagLayout();
    gbc = new GridBagConstraints();
    setLayout(gbag);
    Play = new JButton("PlaY");
    
    AutoMode = new JButton("ReplaY");
    
    Statistic = new JButton("Statistic");   
    createButtons();
    buttons();    
    setVisible(true);
  }
  
  public void createButtons() {
    Play.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent le) {
	    dispose();	
	    new New(1, "action");
	  }
	});	  
	AutoMode.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent le) {
	  dispose();	
	  try {
	      ReplayCollection m = new ReplayCollection();
	  } catch (IOException e) {			
	    e.printStackTrace();
	  }
	  }
	});
	Statistic.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent le) {
	    dispose();	
	    try {
	      Statistics stat = new Statistics();
	    } catch (IOException e) {			
		    e.printStackTrace();
	    }
	    }
	  });
  }
  
  public void buttons() {
    Dimension btnDim = new Dimension(200, 80);
	gbc.anchor = GridBagConstraints.NORTH;
	gbc.fill = GridBagConstraints.NONE;
	gbc.gridheight = 1;
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbc.gridx = GridBagConstraints.RELATIVE;
	gbc.gridy = GridBagConstraints.RELATIVE;
	JLabel tetris = new JLabel("Tetris!");	
	tetris.setFont(FONT);
	gbc.insets = new Insets(20, 0, 0, 0);
    gbc.ipadx = 0;
	gbc.ipady = 0;
	gbc.weightx = 0;
	gbc.weighty = 0;
	gbag.setConstraints(tetris, gbc);
	add(tetris);
    Play.setPreferredSize(btnDim);
	AutoMode.setPreferredSize(btnDim);
	Statistic.setPreferredSize(btnDim);   
	gbag.setConstraints(Play, gbc);
	gbc.insets = new Insets(20, 0, 0, 0);
    add(Play);
	gbc.insets = new Insets(20, 0, 0, 0);
	gbag.setConstraints(AutoMode, gbc);
	add(AutoMode);
	gbc.insets = new Insets(20, 0, 0, 0);
	gbag.setConstraints(Statistic, gbc);    add(Statistic);  
  }
}
