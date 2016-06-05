package Figure;

public class Fig_L extends Figure {

  private int x0a;
  private int y0a;
  private int x1a;
  private int y1a;

  private int x0A;
  private int y0A;
  private int x1A;
  private int y1A;

  private int x2;
  private int y2;

  public Fig_L() {
    super(4, 0, 4, 1, 4, 2, 5, 2, 2);

    super.getBlock(0).setTextureImage(2);
    super.getBlock(1).setTextureImage(2);
    super.getBlock(2).setTextureImage(2);
    super.getBlock(3).setTextureImage(2);

    x0a = 0;
    y0a = 0;
    x1a = 0;
    y1a = 0;
    x0A = 0;
    y0A = 0;
    x1A = 0;
    y1A = 0;
    x2 = 0;
    y2 = 0;
  }


  @Override
  public void rotate(boolean[][] xRay) {

    if (super.getRotationPhase() == 0 && !super.getRotationSet()) {// R.P.
                                                                   // =======================================================
                                                                   // 0;
      // possible block points
      x0a = super.getBlockX(0) - 1;
      y0a = super.getBlockY(0);
      x1a = super.getBlockX(1) - 1;
      y1a = super.getBlockY(1);

      x0A = super.getBlockX(0) + 1;
      y0A = super.getBlockY(0);
      x1A = super.getBlockX(1) + 1;
      y1A = super.getBlockY(1);

      x2 = super.getBlockX(2) - 1;
      y2 = super.getBlockY(2);

      FigUtil.removeFigFromXRay(this, xRay);

      if (FigUtil.pInRange(x0a, y0a) && FigUtil.pInRange(x1a, y1a) && FigUtil.pInRange(x0A, y0A)
          && FigUtil.pInRange(x1A, y1A) && FigUtil.pInRange(x2, y2)) {
        // in range

        if (!xRay[y0a][x0a] && !xRay[y1a][x1a] && !xRay[y0A][x0A] && !xRay[y1A][x1A]
            && !xRay[y2][x2]) {
          // no obstacles

          super.getBlock(0).setXY((super.getBlockX(0) + 1), (super.getBlockY(0) + 1));
          super.getBlock(2).setXY((super.getBlockX(2) - 1), (super.getBlockY(2) - 1));
          super.getBlock(3).setXY((super.getBlockX(3) - 2), (super.getBlockY(3)));

          super.setRotationPhase(1);//
          super.setRotationSet(true);
        }
      }

      FigUtil.putFigOnXRay(this, xRay);
    }

    if (super.getRotationPhase() == 1 && !super.getRotationSet()) {// R.P.
                                                                   // =======================================================
                                                                   // 1;
      // possible block points
      x0a = super.getBlockX(0);
      y0a = super.getBlockY(0) - 1;
      x1a = super.getBlockX(1);
      y1a = super.getBlockY(1) - 1;

      x0A = super.getBlockX(0);
      y0A = super.getBlockY(0) + 1;
      x1A = super.getBlockX(1);
      y1A = super.getBlockY(1) + 1;

      x2 = super.getBlockX(2);
      y2 = super.getBlockY(2) - 1;

      FigUtil.removeFigFromXRay(this, xRay);

      if (FigUtil.pInRange(x0a, y0a) && FigUtil.pInRange(x1a, y1a) && FigUtil.pInRange(x0A, y0A)
          && FigUtil.pInRange(x1A, y1A) && FigUtil.pInRange(x2, y2)) {
        // in range

        if (!xRay[y0a][x0a] && !xRay[y1a][x1a] && !xRay[y0A][x0A] && !xRay[y1A][x1A]
            && !xRay[y2][x2]) {
          // no obstacles

          super.getBlock(0).setXY((super.getBlockX(0) - 1), (super.getBlockY(0) + 1));
          super.getBlock(2).setXY((super.getBlockX(2) + 1), (super.getBlockY(2) - 1));
          super.getBlock(3).setXY((super.getBlockX(3)), (super.getBlockY(3) - 2));

          super.setRotationPhase(2);//
          super.setRotationSet(true);
        }
      }

      FigUtil.putFigOnXRay(this, xRay);
    }

    if (super.getRotationPhase() == 2 && !super.getRotationSet()) {// R.P.
                                                                   // =======================================================
                                                                   // 2;
      // possible block points
      x0a = super.getBlockX(0) + 1;
      y0a = super.getBlockY(0);
      x1a = super.getBlockX(1) + 1;
      y1a = super.getBlockY(1);

      x0A = super.getBlockX(0) - 1;
      y0A = super.getBlockY(0);
      x1A = super.getBlockX(1) - 1;
      y1A = super.getBlockY(1);

      x2 = super.getBlockX(2) + 1;
      y2 = super.getBlockY(2);

      FigUtil.removeFigFromXRay(this, xRay);

      if (FigUtil.pInRange(x0a, y0a) && FigUtil.pInRange(x1a, y1a) && FigUtil.pInRange(x0A, y0A)
          && FigUtil.pInRange(x1A, y1A) && FigUtil.pInRange(x2, y2)) {
        // in range

        if (!xRay[y0a][x0a] && !xRay[y1a][x1a] && !xRay[y0A][x0A] && !xRay[y1A][x1A]
            && !xRay[y2][x2]) {
          // no obstacles

          super.getBlock(0).setXY((super.getBlockX(0) - 1), (super.getBlockY(0) - 1));
          super.getBlock(2).setXY((super.getBlockX(2) + 1), (super.getBlockY(2) + 1));
          super.getBlock(3).setXY((super.getBlockX(3) + 2), (super.getBlockY(3)));

          super.setRotationPhase(3);//
          super.setRotationSet(true);
        }
      }

      FigUtil.putFigOnXRay(this, xRay);
    }

    if (super.getRotationPhase() == 3 && !super.getRotationSet()) {// R.P.
                                                                   // =======================================================
                                                                   // 3;
      // possible block points
      x0a = super.getBlockX(0);
      y0a = super.getBlockY(0) + 1;
      x1a = super.getBlockX(1);
      y1a = super.getBlockY(1) + 1;

      x0A = super.getBlockX(0);
      y0A = super.getBlockY(0) - 1;
      x1A = super.getBlockX(1);
      y1A = super.getBlockY(1) - 1;

      x2 = super.getBlockX(2);
      y2 = super.getBlockY(2) + 1;

      FigUtil.removeFigFromXRay(this, xRay);

      if (FigUtil.pInRange(x0a, y0a) && FigUtil.pInRange(x1a, y1a) && FigUtil.pInRange(x0A, y0A)
          && FigUtil.pInRange(x1A, y1A) && FigUtil.pInRange(x2, y2)) {
        // in range

        if (!xRay[y0a][x0a] && !xRay[y1a][x1a] && !xRay[y0A][x0A] && !xRay[y1A][x1A]
            && !xRay[y2][x2]) {
          // no obstacles

          super.getBlock(0).setXY((super.getBlockX(0) + 1), (super.getBlockY(0) - 1));
          super.getBlock(2).setXY((super.getBlockX(2) - 1), (super.getBlockY(2) + 1));
          super.getBlock(3).setXY((super.getBlockX(3)), (super.getBlockY(3) + 2));

          super.setRotationPhase(0);//
          super.setRotationSet(true);
        }
      }

      FigUtil.putFigOnXRay(this, xRay);
    }

    //
    super.setRotationSet(false);// End!
  }

}
