package HeadPackage;


import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class replay {  
  private String ACTION_FOLDER = "./Action/";
  private String FIGURE_FOLDER = "./Figure/";
  private String TIME_FOLDER = "./Time/";
  private String INFO_FOLDER = "./Info/";
  private String BASE_FOLDER = "./Replay/";  
  private final int COLUMN = 5; 
  private final int LEFT = 7;
  private final int RIGHT = 8;
  private final int DOWN = 9;
  private final int DROP = 10;
  private final int ROTATE = 11;
  private LinkedList<Integer> action;
  private LinkedList<Integer> figure;
  private LinkedList<Long> timer;
  private int Actiter;
  private int Timeiter;
  private int Figiter;
  private int k;
  private long alltime;
  

  public replay() throws IOException {    
    Actiter = 0;
    Timeiter = 0;
    Figiter = 0;
    action = new LinkedList<Integer>();
    timer = new LinkedList<Long>();
    figure = new LinkedList<Integer>();
    k = 0;
    alltime = 0;
  }

  public void SaveAction(int n) {
    action.add(Actiter, n);
    Actiter++;
  }

  public void SaveTime(long n) {
    timer.add(Timeiter, n);
    Timeiter++;
    alltime += n;
  }

  public void SaveFigure(int n) {
    figure.add(Figiter, n);
    Figiter++;
  }

  public int GetAction() {
    int res = action.get(Actiter++);
    return res;
  }

  public long GetTime() {
    long res = timer.get(Timeiter++);
    return res;
  }

  public int GetFigure() {
    int res = figure.get(Figiter++);
    return res;
  }

  public void WriteFile(int score) {
    String s = saveReplay();
    File act = new File(BASE_FOLDER + ACTION_FOLDER + s);
    File time = new File(BASE_FOLDER + TIME_FOLDER + s);
    File fig = new File(BASE_FOLDER + FIGURE_FOLDER + s);
    File info = new File(BASE_FOLDER + INFO_FOLDER + s);
    try (FileWriter filewriter = new FileWriter(act);
        FileWriter fw = new FileWriter(time);
        FileWriter figures = new FileWriter(fig);
        FileWriter infos = new FileWriter(info);) {
      for (int i = 0; i < Actiter; i++) {
        filewriter.write(action.get(i) + " ");
        filewriter.flush();
      }
      for (int i = 0; i < Timeiter; i++) {
        fw.write(timer.get(i) + " ");
        fw.flush();
      }
      for (int i = 0; i < Figiter; i++) {
        figures.write(figure.get(i) + " ");
        figures.flush();
      }
      infos.write(Actiter + " ");
      infos.flush();
      int sec = (int) (alltime / 1000);
      infos.write(sec + " ");
      infos.flush();
      infos.write(Figiter + " ");
      infos.flush();
      infos.write(score + " ");
      infos.flush();
      
      fw.close();
      figures.close();
      infos.close();
      filewriter.close();
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }    
  }
  
  public int stat() {
    File folder = new File(BASE_FOLDER + ACTION_FOLDER);
    String[] fileNames = folder.list();
    if (fileNames == null) {
      return -1;
    }
    return fileNames.length;    
  }

  public String saveReplay() {
    File folder = new File(BASE_FOLDER + ACTION_FOLDER);
    String[] fileNames = folder.list();
    int max = 0;
    if (fileNames != null) {
      try {
        for (int i = 0; i < fileNames.length; i++) {
          Integer temp = new Integer(fileNames[i]);
          if (temp > max) {
            max = temp;
          }
        }
        max++;
      } catch (NumberFormatException ex) {
        System.out.println(ex.getMessage());
      }
    } else {
      max = 1;
    }
    System.out.println(Integer.toString(max));
    return Integer.toString(max);
  }

  public String[][] GetInformation() throws IOException {
    File folder = new File(BASE_FOLDER + INFO_FOLDER);
    String[] fileNames = folder.list();

    if (fileNames == null)
      return null;
    String info[][] = new String[fileNames.length][COLUMN];
    for (int i = 0; i < fileNames.length; i++) {
      File temp = new File(BASE_FOLDER + INFO_FOLDER + fileNames[i]);
      @SuppressWarnings("resource")
      Scanner scannerfile = new Scanner(temp);
      int j = 0;
      info[i][j++] = new String(fileNames[i]);
      while (scannerfile.hasNextInt()) {
        info[i][j++] = new String(Integer.toString(scannerfile.nextInt()));
      }
    }
    return info;
  }

  public void GetFile(String fileNames, String output[]) throws IOException {
    int i = 0;
    try (Scanner scannerfile = new Scanner(BASE_FOLDER + INFO_FOLDER + fileNames);) {
      output[i++] = fileNames;
      while (scannerfile.hasNextInt()) {
        output[i++] = Integer.toString(scannerfile.nextInt());
      }
    }
  }

  public void ReadFile(String s) throws FileNotFoundException, IOException {
	File act = new File(BASE_FOLDER + ACTION_FOLDER + s);
    File time = new File(BASE_FOLDER + TIME_FOLDER + s);
    File fig = new File(BASE_FOLDER + FIGURE_FOLDER + s);
    try (Scanner scannerfile = new Scanner(act);
        Scanner sf = new Scanner(time);
        Scanner figures = new Scanner(fig);) {
      while (scannerfile.hasNextInt()) {
        action.add(scannerfile.nextInt());
      }
      while (sf.hasNextLong()) {
        timer.add(sf.nextLong());
        k++;
      }
      while (figures.hasNextInt()) {
        figure.add(figures.nextInt());
      }
      scannerfile.close();
      sf.close();
      figures.close();
    }
  }
  
  public int[] ReadFigures() {
    File folder = new File(BASE_FOLDER + ACTION_FOLDER);
	  String[] fileNames = folder.list();
	  ArrayList<Integer> info = new ArrayList<Integer>();
      if (fileNames == null) {
        return null;
      }
      for(int i = 0; i < fileNames.length; i++) {
    	File fig = new File(BASE_FOLDER + FIGURE_FOLDER + fileNames[i]);	    	
    	try(Scanner figures = new Scanner(fig);) {
          while (figures.hasNextInt()) {
            info.add(figures.nextInt());
	      }
	      figures.close();	          
    	} catch (FileNotFoundException e) {	
	    	  
		}	    	
      }
      int mas[] = new int [info.size()];
      for(int i = 0; i < info.size(); i++) {
        mas[i] = info.get(i);
      }
      return mas;
  }

  public String[] ReadAction() {
    File folder = new File(BASE_FOLDER + ACTION_FOLDER);
    String[] fileNames = folder.list();
    if (fileNames == null) {
        return null;
    }
    String[] act = new String[fileNames.length];     
    for(int i = 0; i < fileNames.length; i++) {
  	  File fig = new File(BASE_FOLDER + ACTION_FOLDER + fileNames[i]);	    	
  	  try(Scanner figures = new Scanner(fig);) {  
  		act[i] = new String();
        while (figures.hasNextInt()) {
          int buf = figures.nextInt();
          char movement = 'N';
          if (buf == LEFT) movement = 'L';
          if (buf == RIGHT) movement = 'R';
          if (buf == DOWN) movement = 'D';
          if (buf == DROP) movement = 'P';
          if (buf == ROTATE) movement = 'V';
          act[i]+=movement;
	      }
	      figures.close();	          
  	  } catch (FileNotFoundException e) {	    	  
	 }	    	
    }
    return act;
	  
  }
  
  public int GetNum() {
    return this.k;
  }
}


