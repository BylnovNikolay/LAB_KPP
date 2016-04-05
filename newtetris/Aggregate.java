package Board_Tetris;


import java.util.ArrayList;

import Figure.Block;

public class Aggregate {
  private static int indexOfNeededBlock;
  private static int valueOfX;

  public static Block smallestXBlock(ArrayList<Block> arrayLBlocks, boolean smallest) {

    indexOfNeededBlock = 0;
    valueOfX = 0;

    if (smallest) {
      valueOfX = arrayLBlocks.get(0).getPosX();
      for (int i = 1; i < arrayLBlocks.size(); i++) {
        if (arrayLBlocks.get(i).getPosX() < valueOfX) {
          indexOfNeededBlock = i;
        }
        valueOfX = arrayLBlocks.get(i).getPosX();
      }
    } else {
      valueOfX = arrayLBlocks.get(0).getPosX();
      for (int i = 1; i < arrayLBlocks.size(); i++) {
        if (arrayLBlocks.get(i).getPosX() > valueOfX) {
          indexOfNeededBlock = i;
        }
        valueOfX = arrayLBlocks.get(i).getPosX();
      }
    }
    return arrayLBlocks.get(indexOfNeededBlock);
  }

  public static Block biggestYBlock(ArrayList<Block> arrayLBlocks) {
    indexOfNeededBlock = 0;
    valueOfX = arrayLBlocks.get(0).getPosY(); // actually value of Y

    for (int i = 1; i < arrayLBlocks.size(); i++) {
      if (arrayLBlocks.get(i).getPosY() > valueOfX) {
        indexOfNeededBlock = i;
      }
      valueOfX = arrayLBlocks.get(i).getPosY();
    }
    return arrayLBlocks.get(indexOfNeededBlock);
  }
}
