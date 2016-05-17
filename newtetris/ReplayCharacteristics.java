import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ReplayCharacteristics implements Comparable<ReplayCharacteristics> {
	private String INFO_FOLDER = "Info\\";
	private String BASE_FOLDER = "Replay\\";	
	private int Movement;
	private int Figure;
	private int Time;
	private String FileName;
	private int Score;
	private static int SortVariant;
	
	public int getMovement() {
      return this.Movement;
	}
	
	public void setMovement(int Movement) {
	  this.Movement = Movement;
	}
	
	public int getFigure() {
	  return this.Figure;
	}
	
	public void setFigure(int Figure) {
	  this.Figure = Figure;
	}
	
	public int getTime() {
      return this.Time;
	}
	
	public void setTime(int Time) {
	  this.Time = Time;
	}
	
	public String getFileName() {
		return this.FileName;
	}
	
	public void setFileName(String FileName) {
	  this.FileName = FileName;	
	}
	
	public int getScore() {
	  return this.Score;
	}
	
	public void setScore(int Score) {
	  this.Score = Score;
	}
	
	ReplayCharacteristics(String fileName) throws FileNotFoundException {
	  this.FileName = fileName;
	  readReplayCharacteristics();
	}
	
	ReplayCharacteristics readReplayCharacteristics() throws FileNotFoundException {
      File temp = new File(BASE_FOLDER + INFO_FOLDER + this.FileName);
	  @SuppressWarnings("resource")
	  Scanner scannerfile = new Scanner(temp);	 
	  if(scannerfile.hasNextInt()) {
	    this.Movement = scannerfile.nextInt();
	  }
	  if(scannerfile.hasNextInt()) {
	    this.Time = scannerfile.nextInt();
	  }
	  if(scannerfile.hasNextInt()) {
	    this.Figure = scannerfile.nextInt();
	  }
	  if(scannerfile.hasNextInt()) {
	    this.Score = scannerfile.nextInt();
	  }	       
	  return this;	  
	}
	
	@Override
	  public int compareTo(ReplayCharacteristics replay) {
	    int resultVariant = 0;
	    switch (SortVariant) {
	      case 0: {
	        resultVariant = FileName.compareTo(replay.FileName);
	        break;
	      }
	      case 1: {
	        resultVariant = Integer.compare(Movement, 
	            replay.Movement);
	        break;
	      }
	      case 2: {
	        resultVariant = Integer.compare(Time, 
	            replay.Time);
	        break;
	      }
	      case 3: {
	        resultVariant = Integer.compare(Figure, 
	            replay.Figure);
	        break;
	      }
	      case 4: {
	        resultVariant = Integer.compare(Score, 
	            replay.Score);
	        break;
	      }	     
	    }
	    return resultVariant;
	  }
	
  public static int getSortVariant() {
    return SortVariant;
  }

  public static void setSortVariant(int sortVariant) {
    ReplayCharacteristics.SortVariant = sortVariant;
  }	
}

