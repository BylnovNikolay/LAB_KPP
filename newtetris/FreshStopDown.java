package Board_Tetris;


import java.util.ArrayList;
import Figure.Block;
import Figure.Figure;

public class FreshStopDown {

  private static int uniqueXs;
  private static boolean endIfs;
  private static final int ROW = 19; 
  public static boolean mustStopDown(ArrayList<Block> stopBlocks, ArrayList<Block> uniqueY0, 
		   							  ArrayList<Block> uniqueY1, ArrayList<Block> uniqueY2,
		   							  ArrayList<Block> uniqueY3, boolean[][] blackWhite, 
		   							  Figure freshFigure,  int x, int y) {

    x = 0;
    y = 0;
    stopBlocks.removeAll(stopBlocks);      
    uniqueXs = 0;
    endIfs = false;

    // IF IT'S ON THE BOTTOM
    // check if any of the boxes coordinates y-1 is 19
    for (int i = 0; i < freshFigure.getNrOfBlocks(); i++) {      
      y = freshFigure.getBlockY(i);
      if (y == ROW) {         
        return true;
      }
    }
    // SHOULD CHECK THE LOWEST Y WITH THE SAME X, all blocks
    // AFTER THAT, ADD THEM TO THE stopBlocks ArrayList     
    // The are actually uniqueX0, uniqueX1, uniqueX2, uniqueX3
           
    uniqueY0.clear();
    uniqueY1.clear();
    uniqueY2.clear();
    uniqueY3.clear();

    uniqueY0.add(new Block(freshFigure.getBlockX(0),
    		freshFigure.getBlockY(0), 0)); // -working Block 0
    uniqueXs++;

    if (freshFigure.getBlockX(1) == uniqueY0.get(0).getPosX()) {
      // -working Block 1
      uniqueY0.add(new Block(freshFigure.getBlockX(1), 
    		freshFigure.getBlockY(1), 0));
      } else {     
          uniqueY1.add(new Block(freshFigure.getBlockX(1),
        	freshFigure.getBlockY(1), 0));
          uniqueXs++;
      }
      /////////////////////////////////////////////////////////////////////////////////
      if (freshFigure.getBlockX(2) == uniqueY0.get(0).getPosX()) {
        // -working Block 2
        uniqueY0.add(new Block(freshFigure.getBlockX(2),
        	freshFigure.getBlockY(2), 0));
        endIfs = true;
      }
      if (uniqueY1.size() == 0 && !endIfs) {
        // if the array1 is empty - put the block there
        uniqueY1.add(new Block(freshFigure.getBlockX(2),
            freshFigure.getBlockY(2), 0));
        uniqueXs++;
        endIfs = true;
      }
      if (uniqueY1.size() > 0 && !endIfs) {
        // if it's not empty - verify if it corresponds and put it there
        if (freshFigure.getBlockX(2) == uniqueY1.get(0).getPosX()) {         
          uniqueY1.add(new Block(freshFigure.getBlockX(2),
              freshFigure.getBlockY(2), 0));
          endIfs = true;
        }
      }
      if (!endIfs) { // MORE EXPLICIT? // the problem - here wasn't the uniqueYs++!
        // otherwise put in a different array
        uniqueY2.add(new Block(freshFigure.getBlockX(2),
        	  freshFigure.getBlockY(2), 0));
        endIfs = true;
        uniqueXs++;
      }      
      // Would be good make a logic tree in astah and verify line by line;
      /////////////////////////////////////////////////////////////////////////////////      
      endIfs = false;
      
      if (freshFigure.getBlockX(3) == uniqueY0.get(0).getPosX()) {
        // -working Block 3
        uniqueY0.add(new Block(freshFigure.getBlockX(3),
        	  freshFigure.getBlockY(3), 0));
        endIfs = true;
      }

      if (uniqueY1.size() <= 0 && !endIfs) {
        // verify if array1 is empty
        uniqueY1.add(new Block(freshFigure.getBlockX(3),
        		freshFigure.getBlockY(3), 0));
         uniqueXs++;
         endIfs = true;
      }
      if (uniqueY1.size() > 0 && !endIfs) {
        // if it's full - verify if it corresponds
        if (freshFigure.getBlockX(3) == uniqueY1.get(0).getPosX()) {         
          uniqueY1.add(new Block(freshFigure.getBlockX(3),
        		freshFigure.getBlockY(3), 0));
            endIfs = true;
        }
      }

      if (uniqueY2.size() <= 0 && !endIfs) {
        // verify if array2 is empty
        uniqueY2.add(new Block(freshFigure.getBlockX(3),
        	    freshFigure.getBlockY(3), 0));
        uniqueXs++;
        endIfs = true;
      }
      if (uniqueY2.size() > 0 && !endIfs) {      
        if (freshFigure.getBlockX(3) == uniqueY2.get(0).getPosX()) {
          // if it's full - verify if it corresponds
          uniqueY2.add(new Block(freshFigure.getBlockX(3),
        		freshFigure.getBlockY(3), 0));
          endIfs = true;
        }
      }
      if(!endIfs) {
        // else put it in a different array - basically a I figure standing
        // vertically
        uniqueY3.add(new Block(freshFigure.getBlockX(3),
        		freshFigure.getBlockY(3), 0));
        uniqueXs++;
      }

      // Based on the number of unique Y's, add the ones with the smallest X to
      // the stopBlocks ArrayList; (LEFT = boolean left; if true - smallest x,
      // if false - biggest x)
      // Based on the number of unique Y's, add the ones with the smallest X to
      // the stopBlocks ArrayList;
      
      // first add the one;
      stopBlocks.add(biggestYB(uniqueY0));

      // if there are more unique Y's;
      if (uniqueXs > 1) {      
        stopBlocks.add(biggestYB(uniqueY1));
        if (uniqueXs > 2) {         
          stopBlocks.add(biggestYB(uniqueY2));
          if (uniqueXs > 3) {            
            stopBlocks.add(biggestYB(uniqueY3));
          }
        }
      }

      // CHECKING WITH THE X-RAY
      for (int i = 0; i < stopBlocks.size(); i++) {      
        x = stopBlocks.get(i).getPosX();
        y = stopBlocks.get(i).getPosY();
        if (xRay(y + 1, x, blackWhite)) {         
          return true;
        }
      }
      return false;
    }   
    // METHOD THAT RETURNS THE BLOCK WITH THE SMALLEST/BIGGEST X IN AN ARRAY LIST
    // OF BLOCKS  
  private static Block biggestYB(ArrayList<Block> arrayLBlocks) {
    return Aggregate.biggestYBlock(arrayLBlocks);
  }   
  private static boolean xRay(int Y, int X, boolean[][] blackWhite) {   
    return blackWhite[Y][X];
  }
}
