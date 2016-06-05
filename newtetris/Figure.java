package Figure;

import java.util.ArrayList;

public class Figure {

  private ArrayList<Block> blocks;
  private int rotationPhase;
  private boolean rotationSet;

  public Figure() {}

  public Figure(int oneX, int oneY, int twoX, int twoY, int threeX, int threeY, int fourX,
      int fourY, int textureImg) {
    blocks = new ArrayList<Block>();

    blocks.add(0, new Block(oneX, oneY, textureImg));
    blocks.add(1, new Block(twoX, twoY, textureImg));
    blocks.add(2, new Block(threeX, threeY, textureImg));
    blocks.add(3, new Block(fourX, fourY, textureImg));

    rotationPhase = 0;
    rotationSet = false;
  }

  public int getBlockX(int i) {
    return blocks.get(i).getPosX();
  }

  public int getBlockY(int i) {
    return blocks.get(i).getPosY();
  }

  public int getBlockImg(int i) {
    return blocks.get(i).getTextureImage();
  }

  public void setBlockX(int xPos, int nr) {
    blocks.get(nr).setPosX(xPos);
  }

  public void setBlockY(int yPos, int nr) {
    blocks.get(nr).setPosY(yPos);
  }

  public int getNrOfBlocks() {
    return this.blocks.size();
  }

  public int getRotationPhase() {
    return rotationPhase;
  }

  public void setRotationPhase(int rotationPhase) {
    this.rotationPhase = rotationPhase;
  }

  public Block getBlock(int i) {
    return this.blocks.get(i);
  }

  public void rotate(boolean xRay[][]) {

  }

  public boolean getRotationSet() {
    return rotationSet;
  }

  public void setRotationSet(boolean rotationSet) {
    this.rotationSet = rotationSet;
  }

  public void removeBlock(int index) {
    this.blocks.remove(index);
  }

  public boolean isEqualTo(Figure figure) {
    int a = 0, b = 0, c = 0;

    if (blocks.size() == figure.getNrOfBlocks()) {
      for (int i = 0; i < blocks.size(); i++) {
        if (figure.getBlockX(i) == blocks.get(i).getPosX()) {
          a++;
        }
        if (figure.getBlockY(i) == blocks.get(i).getPosY()) {
          b++;
        }
        if (figure.getBlockImg(i) == blocks.get(i).getTextureImage()) {
          c++;
        }
      }
    }
    if (a == b && a == c && b == c && a == blocks.size() && a != 0) {
      return true;
    }
    return false;
  }
}
