package Figure;

public class Block {
  private int posX;
  private int posY;
  private int textureImage;

  public Block(int posX, int posY, int textureImage) {
    this.posX = posX;
    this.posY = posY;
    this.textureImage = textureImage;
  }

  public int getPosX() {
    return posX;
  }

  public void setPosX(int posX) {
    this.posX = posX;
  }

  public int getPosY() {
    return posY;
  }

  public void setPosY(int posY) {
    this.posY = posY;
  }

  public int getTextureImage() {
    return textureImage;
  }

  public void setTextureImage(int textureImage) {
    this.textureImage = textureImage;
  }

  public void setXY(int x, int y) {
    this.posX = x;
    this.posY = y;
  }

}
