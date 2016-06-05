package Board_Tetris;

import java.util.ArrayList;

import Figure.Block;
import Figure.Figure;

public class FreshStopSides {
	
  private static int uniqueYs;
  private static boolean endIfs;
  private final static int SIDE = 4;
  private final static int COLUMN = 9;
  public static boolean mustStopL(boolean LEFT, ArrayList<Block> stopBlocks, ArrayList<Block> uniqueY0,
		  ArrayList<Block> uniqueY1, ArrayList<Block> uniqueY2, ArrayList<Block> uniqueY3,
          boolean[][] blackWhite, Figure freshFigure, int x, int y) {
    x = 0;
    y = 0;
    stopBlocks.removeAll(stopBlocks);
    uniqueYs = 0;
    endIfs = false;
      
    // Check if the figure is on the side
    for (int i = 0; i < SIDE; i++) {      
      x = freshFigure.getBlockX(i);
      if (x == 0 && LEFT == true) {         
        return true;
      }
      if (x == COLUMN && LEFT == false) {        
        return true;
      }
    }

    // SORT THE BLOCKS BY UNIQUE Y's IN SEPARATE ARRAYLISTS, keeping a counter
    // for the ArrayLists needed (uniqueYs)
    uniqueY0.clear();
    uniqueY1.clear();
    uniqueY2.clear();
    uniqueY3.clear();

    uniqueY0.add(new Block(freshFigure.getBlockX(0),
          freshFigure.getBlockY(0), 0)); // -working Block 0
    uniqueYs++;

    if (freshFigure.getBlockY(1) == uniqueY0.get(0).getPosY()) {
      // -working Block 1
      uniqueY0.add(new Block(freshFigure.getBlockX(1), freshFigure
               .getBlockY(1), 0));
    } else {    
        uniqueY1.add(new Block(freshFigure.getBlockX(1), freshFigure
               .getBlockY(1), 0));
       uniqueYs++;
    }
    
    if (freshFigure.getBlockY(2) == uniqueY0.get(0).getPosY()) {
      // -working Block 2
      uniqueY0.add(new Block(freshFigure.getBlockX(2), freshFigure
               .getBlockY(2), 0));
      endIfs = true;    
    }
    if (uniqueY1.size() == 0 && !endIfs) {
      // if the array1 is empty - put the block there
      uniqueY1.add(new Block(freshFigure.getBlockX(2), freshFigure
               .getBlockY(2), 0));
      uniqueYs++;
      endIfs = true;    
    }
    if (uniqueY1.size() > 0 && !endIfs) {
      // if it's not empty - verify if it corresponds and put it there
      if (freshFigure.getBlockY(2) == uniqueY1.get(0).getPosY()) {         
        uniqueY1.add(new Block(freshFigure.getBlockX(2), freshFigure
                .getBlockY(2), 0));
        endIfs = true;      
      }
    }
    if (!endIfs) {
      // otherwise put in a different array
      uniqueY2.add(new Block(freshFigure.getBlockX(2), freshFigure
               .getBlockY(2), 0));
      endIfs = true;
      uniqueYs++;     
    }
    endIfs = false;   
      
    if (freshFigure.getBlockY(3) == uniqueY0.get(0).getPosY()) {
      // -working Block 3
      uniqueY0.add(new Block(freshFigure.getBlockX(3), freshFigure
             .getBlockY(3), 0));
      endIfs = true;
    }
    
    if (uniqueY1.size() <= 0 && !endIfs) {
      // verify if array1 is empty
      uniqueY1.add(new Block(freshFigure.getBlockX(3), freshFigure
               .getBlockY(3), 0));
      uniqueYs++;
      endIfs = true;
    }
    if (uniqueY1.size() > 0 && !endIfs) {
      // if it's full - verify if it corresponds
      if (freshFigure.getBlockY(3) == uniqueY1.get(0).getPosY()) {         
        uniqueY1.add(new Block(freshFigure.getBlockX(3), freshFigure
                  .getBlockY(3), 0));
        endIfs = true;
      }
    }

    if (uniqueY2.size() <= 0 && !endIfs) {
      // verify if array2 is empty
      uniqueY2.add(new Block(freshFigure.getBlockX(3), freshFigure
               .getBlockY(3), 0));
      uniqueYs++;
      endIfs = true;
    }
    if (uniqueY2.size() > 0 && !endIfs) {      
      if (freshFigure.getBlockY(3) == uniqueY2.get(0).getPosY()) {
        // if it's full - verify if it corresponds
        uniqueY2.add(new Block(freshFigure.getBlockX(3), freshFigure
                  .getBlockY(3), 0));
        endIfs = true;
      }
    }
    if(!endIfs) {
      // else put it in a different array - basically a I figure standing
       // vertically
       uniqueY3.add(new Block(freshFigure.getBlockX(3), freshFigure
               .getBlockY(3), 0));
       uniqueYs++;
    }
    
    // Based on the number of unique Y's, add the ones with the smallest X to
    // the stopBlocks ArrayList; (LEFT = boolean left; if true - smallest x,
    // if false - biggest x)

    // first add the one;
    stopBlocks.add(smallBigXBlock(uniqueY0, LEFT));

    // if there are more unique Y's;
    if (uniqueYs > 1) {      
      stopBlocks.add(smallBigXBlock(uniqueY1, LEFT));
      if (uniqueYs > 2) {         
        stopBlocks.add(smallBigXBlock(uniqueY2, LEFT));
        if (uniqueYs > 3) {            
          stopBlocks.add(smallBigXBlock(uniqueY3, LEFT));
        }
      }
    }
    // CHECKING WITH THE X-RAY
    for (int i = 0; i < stopBlocks.size(); i++) {      
      x = stopBlocks.get(i).getPosX();
      y = stopBlocks.get(i).getPosY();
      
      if (LEFT) {       
        if (xRay(y, x - 1, blackWhite)) {            
          return true;
        }
      }
      if (!LEFT) {
        if (xRay(y, x + 1, blackWhite)) {            
          return true;
        }
      }
    }
    return false;
  }
  // METHOD THAT RETURNS THE BLOCK WITH THE SMALLEST/BIGGEST X IN AN ARRAY LIST
  // OF BLOCKS
  private static Block smallBigXBlock(ArrayList<Block> arrayLBlocks,
         boolean smallest) {   
    return Aggregate.smallestXBlock(arrayLBlocks, smallest);
  }
  private static boolean xRay(int Y, int X, boolean[][] blackWhite) {   
    return blackWhite[Y][X];
  }
}
