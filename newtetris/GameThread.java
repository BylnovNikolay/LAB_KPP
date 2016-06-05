package HeadPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameThread implements Runnable {
	 Thread t; 
	  String fileName;
	  int mode;
	  public GameThread(int mode, String fileName) {		
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
		    System.out.println("Hello");
		  } 	 
}

