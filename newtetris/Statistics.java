import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Statistics {
  private LinkedList<ReplayCharacteristics> ReplayList;  
  private File[] FileList;
  private String INFO_FOLDER = "./Info/";
  private String BASE_FOLDER = "./Replay/";  
  private File REPLAY_FOLDER = new File(BASE_FOLDER + INFO_FOLDER);
  private int NUM_OF_LABELS = 7;
  private final int FRAME_WIDTH = 420;
  private final int FRAME_HIGHT = 800;
  private replay RP;
  private int[] replayStatistic;
  Font FONT = new Font("Modern No. 20", Font.CENTER_BASELINE, 30);
  private final Color LABELS_COLOR = new Color(0, 0, 0); 
  JFrame replaysStatisticFrame;
  JLabel[] statisticLabel;
  String[] label_text = {"Number of I_Shape : ",
	      "Number of J_Shape: ",
	      "Number of L_Shape: ",
	      "Number of O_Shape: ",
	      "Number of S_Shape: ",
	      "Number of T_Shape: ",
	      "Number of Z_Shape: "
	      };	   
  
  Statistics() throws IOException {
    replayStatistic = new int[NUM_OF_LABELS];
	replaysStatisticFrame = new JFrame("Statistic");
	replaysStatisticFrame.setLayout(new GridLayout(NUM_OF_LABELS, 1));
	ReplayList = new LinkedList<> ();
	RP = new replay();
	FigureStatistic();
    //getStatisticfile();
    replaysStatisticFrame.setSize(FRAME_WIDTH, FRAME_HIGHT);
    replaysStatisticFrame.setLocationRelativeTo(null);
    replaysStatisticFrame.setResizable(false);
    replaysStatisticFrame.setVisible(true);    
  }
  
  public void createLabels() {
    statisticLabel = new JLabel[NUM_OF_LABELS];
	statisticLabel[0] = new JLabel(label_text[0] +
	Integer.toString(FileList.length));
	for(int i = 1; i < NUM_OF_LABELS; i++) {
	  statisticLabel[i] = new JLabel(label_text[i] + replayStatistic[i - 1]);
	  statisticLabel[i].setFont(FONT);
	  statisticLabel[i].setForeground(LABELS_COLOR);
	  replaysStatisticFrame.add(statisticLabel[i]);
	}
  }    
  
  public void Labels() {
	    statisticLabel = new JLabel[NUM_OF_LABELS];
		//statisticLabel[0] = new JLabel(label_text[0] +
		//Integer.toString(FileList.length));
		for(int i = 0; i < NUM_OF_LABELS; i++) {
		  statisticLabel[i] = new JLabel(label_text[i] + replayStatistic[i]+"\n");
		  statisticLabel[i].setFont(FONT);
		  statisticLabel[i].setForeground(LABELS_COLOR);
		  replaysStatisticFrame.add(statisticLabel[i]);
		}
	  } 
  
  
  public void getStatisticfile() throws FileNotFoundException {
    FileList = REPLAY_FOLDER.listFiles();
	for (int i = 0; i < FileList.length; i++) {         
	  ReplayList.add(new ReplayCharacteristics(FileList[i].getName()));
	  replayStatistic[0] += ReplayList.get(i).getMovement();
      replayStatistic[1] += ReplayList.get(i).getTime();
      replayStatistic[2] += ReplayList.get(i).getFigure();
      replayStatistic[3] += ReplayList.get(i).getScore();     
	}
	createLabels();
  }
  
  public void FigureStatistic() {
    int mas[] = RP.ReadFigures(); 
    FileList = REPLAY_FOLDER.listFiles();
    Algorithm obj = new Algorithm();
    replayStatistic[0] = obj.countIShape(mas);
    replayStatistic[1] = obj.countJShape(mas);
    replayStatistic[2] = obj.countLShape(mas);
    replayStatistic[3] = obj.countOShape(mas);
    replayStatistic[4] = obj.countSShape(mas);
    replayStatistic[5] = obj.countTShape(mas);
    replayStatistic[6] = obj.countZShape(mas);
    Labels();
  }
  
  public void ActionStatistic() {
	String[] act = RP.ReadAction();  
    Algorithm obj = new Algorithm();
    System.out.println(obj.findMaxRepeatedSeq(act));
  }
  
}


