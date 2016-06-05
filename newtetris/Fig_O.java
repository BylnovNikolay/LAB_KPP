package Figure;

public class Fig_O extends Figure {

  public Fig_O() {
    super(4, 0, 5, 0, 4, 1, 5, 1, 3);

    super.getBlock(0).setTextureImage(3);
    super.getBlock(1).setTextureImage(3);
    super.getBlock(2).setTextureImage(3);
    super.getBlock(3).setTextureImage(3);
  }

  @Override
  public void rotate(boolean[][] xRay) {

  }

}
