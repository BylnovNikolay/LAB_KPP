import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class ReplayCollection {
  private final String[] HEADERS =
      {"Replay's Name", "Number of blocks", "Number of movements", "Time", "Score"};
  private final int FRAME_HIGHT = 550;
  private final int FRAME_WIDTH = 600;
  private final int COLUMN = 5;
  private final int CELL_HIEGHT = 16;
  private String INFO_FOLDER = "Info\\";
  private String BASE_FOLDER = "Replay\\";  
  private int RpNum;
  private replay RP;
  private JFrame ReplayFrame;
  private JTable ReplayTable;
  private String ReplayInfo[][];
  private LinkedList<ReplayCharacteristics> ReplayList;
  private File REPLAY_FOLDER = new File(BASE_FOLDER + INFO_FOLDER);
  private File[] FileList;
  
  
  ReplayCollection() throws IOException {
	ReplayList = new LinkedList<> ();
    ReplayFrame = new JFrame("Choose Replay");
    ReplayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ReplayFrame.setAlwaysOnTop(true);
    RP = new replay();
    getfile();
    try {
      addPanel();
    } catch (InterruptedException e) {     
      e.printStackTrace();
    }
    ReplayFrame.setSize(FRAME_WIDTH, FRAME_HIGHT);
    ReplayFrame.setLocationRelativeTo(null);
    ReplayFrame.setVisible(true);
  }
  public void getfile() throws FileNotFoundException {
     FileList = REPLAY_FOLDER.listFiles();
     for (int i = 0; i < FileList.length; i++) {         
         ReplayList.add(new ReplayCharacteristics(
        		 FileList[i].getName()));
     }
  }
  
  public void sortArray() {
	    long sortStartTime = 0;
	    long sortEndTime = 0;
	    long sortTraceTime = 0;
	    ScalaFunctions scalaObject = new ScalaFunctions();
	    
	    int[] array = new int[ReplayList.size()];
	    for (int i = 0; i < ReplayList.size(); i++) {
	      array[i] = ReplayList.get(i).getScore();
	    }
	    sortEndTime = System.nanoTime();

	    scalaObject.sort(array);
	    sortEndTime = System.nanoTime();
	    sortTraceTime = sortEndTime - sortStartTime;
	    System.out.println("Time of sorting in Scala: " + sortTraceTime/1000000000);

	    sortStartTime = System.nanoTime();	   
		Arrays.sort(array);
	    sortEndTime = System.nanoTime();
	    sortTraceTime = sortEndTime - sortStartTime;
	    System.out.println("Time of sorting in Java: " + sortTraceTime);
  }
  
  public void addPanel() throws IOException, InterruptedException {
    RpNum = RP.stat();
    if (RpNum != -1) {
      ReplayInfo = new String[RpNum][COLUMN];
      ReplayInfo = RP.GetInformation();
    }
    ReplayTable = new JTable(ReplayInfo, HEADERS);
    ReplayTable.setCellSelectionEnabled(true);
    ReplayTable.setEnabled(false);
    ReplayTable.addMouseListener(new ReplayTableListener());
    ReplayTable.getTableHeader().addMouseListener(new HeaderTableListener());
    JScrollPane scroll = new JScrollPane(ReplayTable);
    ReplayFrame.add(scroll);
  }
  
  public void updateContent() {
    for (int i = 0; i < RP.stat(); i++) {
    	ReplayInfo[i][0] = ((ReplayList.get(i).getFileName()));
    	ReplayInfo[i][1] = (Integer.toString(ReplayList.
	          get(i).getMovement()));
    	ReplayInfo[i][2] = (Integer.toString(ReplayList.
	          get(i).getTime()));
    	ReplayInfo[i][3] = (Integer.toString(ReplayList.
	          get(i).getFigure()));
    	ReplayInfo[i][4] =	(Integer.toString(ReplayList.
    		  get(i).getScore()));	      
	    }
	  }
  
  
  class HeaderTableListener implements MouseListener {

	    @Override
	    public void mouseClicked(MouseEvent event) {
	      sortArray();
	      ReplayCharacteristics.setSortVariant(ReplayTable.
	          columnAtPoint(event.getPoint()));
	      System.out.println(ReplayTable.columnAtPoint(event.getPoint()));
	      ReplayList.sort(ReplayCharacteristics::compareTo);
	      updateContent();
	      ReplayTable.updateUI();
	    }

	    @Override
	    public void mouseEntered(MouseEvent arg0) {

	    }

	    @Override
	    public void mouseExited(MouseEvent arg0) {

	    }

	    @Override
	    public void mousePressed(MouseEvent arg0) {

	    }

	    @Override
	    public void mouseReleased(MouseEvent arg0) {

	    }

	  }
  
  
  class ReplayTableListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent event) {

      String fileName = new String(Integer.toString(event.getY() / CELL_HIEGHT + 1));
      System.out.println(fileName);     
      ReplayFrame.dispose();
      new New(0, fileName);
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {

    }

    @Override
    public void mouseExited(MouseEvent arg0) {

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    	
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }
  }
}

class New implements Runnable {
	  Thread t; 
	  String fileName;
	  int mode;
	  New(int mode, String fileName) {
	    t = new Thread(this, "son");
	    t.start();
	    this.fileName = fileName;
	    this.mode = mode;
	  }

	  public void run() {
	    try {
	      Controler cont = new Controler(mode,fileName);
	      cont.start();
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	  }
	}






