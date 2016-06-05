package HeadPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

public class Statistics {
  private LinkedList<ReplayCharacteristics> ReplayList;  
  private File[] FileList;
  private final String[] HEADERS =
      {"Statistic","Numbers"};
  private String INFO_FOLDER = "./Info/";
  private String BASE_FOLDER = "./Replay/";  
  private File REPLAY_FOLDER = new File(BASE_FOLDER + INFO_FOLDER);
  private int NUM_OF_LABELS = 8;
  private final int FRAME_WIDTH = 300;
  private final int FRAME_HIGHT = 155;
  private final int COLUMN = 2;
  private replay RP;
  private JTable StatisticTable;
  private String StatisticInfo[][];
  private final char LEFT = 'L';
  private final int RIGHT = 'R';
  private final int DOWN = 'D';
  private final int DROP = 'P';
  private final int ROTATE = 'V';
  private int[] replayStatistic;
  Font FONT = new Font("Courier",  Font.ITALIC, 30);
  private final Color LABELS_COLOR = new Color(0, 0, 0); 
  JFrame replaysStatisticFrame;
  JLabel[] statisticLabel;
  String[] label_text = {"Number of I_Shape : ",
	      "Number of J_Shape: ",
	      "Number of L_Shape: ",
	      "Number of O_Shape: ",
	      "Number of S_Shape: ",
	      "Number of T_Shape: ",
	      "Number of Z_Shape: ",
	      "Sequence"
	      };	   
  
  Statistics() throws IOException {
    replayStatistic = new int[NUM_OF_LABELS];
	replaysStatisticFrame = new JFrame("Statistic");
	replaysStatisticFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//replaysStatisticFrame.setLayout(new GridLayout(NUM_OF_LABELS, 1));
	ReplayList = new LinkedList<> ();
	RP = new replay();
	//FigureStatistic();
    try {
	  addPanel();
	} catch (InterruptedException e) {     
	  e.printStackTrace();
	}
    replaysStatisticFrame.add(StatisticTable);
    replaysStatisticFrame.setSize(FRAME_WIDTH, FRAME_HIGHT);
    replaysStatisticFrame.setLocationRelativeTo(null);
    replaysStatisticFrame.setResizable(false);
    replaysStatisticFrame.setBackground(Color.WHITE);
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
  
  public void addPanel() throws IOException, InterruptedException {	    
    StatisticInfo = GetInformation();	
	    
    StatisticTable = new JTable(StatisticInfo, HEADERS);
    StatisticTable.setCellSelectionEnabled(true);
    StatisticTable.setEnabled(false);	
  }
  
  public void Labels() {
    statisticLabel = new JLabel[NUM_OF_LABELS];
    //statisticLabel[0] = new JLabel(label_text[0] +
    //Integer.toString(FileList.length));
    for(int i = 0; i < NUM_OF_LABELS-1; i++) {
	  statisticLabel[i] = new JLabel(label_text[i] + replayStatistic[i]+"\n");
	  statisticLabel[i].setFont(FONT);
	  statisticLabel[i].setBackground(Color.WHITE);
	  statisticLabel[i].setForeground(LABELS_COLOR);
	  replaysStatisticFrame.add(statisticLabel[i]);
    }
    statisticLabel[NUM_OF_LABELS-1] = new JLabel("Sequence:" + ActionStatistic() + "\n");
    statisticLabel[NUM_OF_LABELS-1].setFont(FONT);
	statisticLabel[NUM_OF_LABELS-1].setForeground(Color.MAGENTA);
	statisticLabel[NUM_OF_LABELS-1].setBackground(Color.WHITE);
	replaysStatisticFrame.add(statisticLabel[NUM_OF_LABELS-1]);
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
  
  public int[] FigureStatistic() {
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
    return replayStatistic;
  }
  
  public String[][] GetInformation() throws IOException {    
	String info[][] = new String[label_text.length][COLUMN];
	int j = 0;
	int[] mas = FigureStatistic();	
	for (j = 0; j < label_text.length;j++) {	  
	  info[j][0] = new String(label_text[j]);	
	}
	for (j = 0; j < label_text.length-1;j++) {	  
	  info[j][1] = new String(Integer.toString(mas[j]));
	} 
	info[j][1] = ActionStatistic();
	return info;
  }
  
  public String performString(String note) {
	String info = new String();
    for(int i = 0; i < note.length(); i++) {
      char buf = note.charAt(i);
      if(buf == LEFT) {
        info += "LEFT ";
      }
      if(buf == RIGHT) {
        info += "RIGHT ";
      }
      if(buf == DOWN) {
    	info += "DOWN ";
      }
      if(buf == DROP) {
    	info += "DROP ";    		
      }
      if(buf == ROTATE) {
        info += "ROTATE ";
      }
    }
    return info;
  }
  
  public String ActionStatistic() {
	String[] act = RP.ReadAction();  
    Algorithm obj = new Algorithm();
    String info = performString(obj.findLine(act));
    return info;
  }  
}


