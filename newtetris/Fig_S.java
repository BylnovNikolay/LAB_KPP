package Figure;

public class Fig_S extends Figure {

  private int x0a;
  private int y0a;
  private int x0b;
  private int y0b;
  private int x1;
  private int y1;

  public Fig_S() {
    super(4, 0, 4, 1, 5, 1, 5, 2, 4);

    super.getBlock(0).setTextureImage(4);
    super.getBlock(1).setTextureImage(4);
    super.getBlock(2).setTextureImage(4);
    super.getBlock(3).setTextureImage(4);

    x0a = 0;
    y0a = 0;
    x0b = 0;
    y0b = 0;
    x1 = 0;
    y1 = 0;
  }

  @Override
  public void rotate(boolean[][] xRay) {

    if (super.getRotationPhase() == 0 && !super.getRotationSet()) {// R.P. ==================== 0;
      // possible block points
      x0a = super.getBlockX(0) + 1;
      y0a = super.getBlockY(0);
      x0b = super.getBlockX(0) + 2;
      y0b = super.getBlockY(0);

      x1 = super.getBlockX(1);
      y1 = super.getBlockY(1) + 1;

      FigUtil.removeFigFromXRay(this, xRay);

      if (FigUtil.pInRange(x0a, y0a) && FigUtil.pInRange(x0b, y0b) && FigUtil.pInRange(x1, y1)) {
        // in range

        if (!xRay[y0a][x0a] && !xRay[y0b][x0b] && !xRay[y1][x1]) {
          // no obstacles

          super.getBlock(0).setXY((super.getBlockX(0) + 2), (super.getBlockY(0)));
          super.getBlock(1).setXY((super.getBlockX(1) + 1), (super.getBlockY(1) - 1));
          super.getBlock(3).setXY((super.getBlockX(3) - 1), (super.getBlockY(3) - 1));

          super.setRotationPhase(1);//
          super.setRotationSet(true);
        }
      }

      FigUtil.putFigOnXRay(this, xRay);
    }

    if (super.getRotationPhase() == 1 && !super.getRotationSet()) {// R.P. ===================== 1;
      // possible block points
      x0a = super.getBlockX(0);
      y0a = super.getBlockY(0) + 1;
      x0b = super.getBlockX(0);
      y0b = super.getBlockY(0) + 2;

      x1 = super.getBlockX(1) - 1;
      y1 = super.getBlockY(1);

      FigUtil.removeFigFromXRay(this, xRay);

      if (FigUtil.pInRange(x0a, y0a) && FigUtil.pInRange(x0b, y0b) && FigUtil.pInRange(x1, y1)) {
        // in range

        if (!xRay[y0a][x0a] && !xRay[y0b][x0b] && !xRay[y1][x1]) {
          // no obstacles

          super.getBlock(0).setXY((super.getBlockX(0)), (super.getBlockY(0) + 2));
          super.getBlock(1).setXY((super.getBlockX(1) + 1), (super.getBlockY(1) + 1));
          super.getBlock(3).setXY((super.getBlockX(3) + 1), (super.getBlockY(3) - 1));

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
      x0a = super.getBlockX(0) - 1;
      y0a = super.getBlockY(0);
      x0b = super.getBlockX(0) - 2;
      y0b = super.getBlockY(0);

      x1 = super.getBlockX(1);
      y1 = super.getBlockY(1) - 1;

      FigUtil.removeFigFromXRay(this, xRay);

      if (FigUtil.pInRange(x0a, y0a) && FigUtil.pInRange(x0b, y0b) && FigUtil.pInRange(x1, y1)) {
        // in range

        if (!xRay[y0a][x0a] && !xRay[y0b][x0b] && !xRay[y1][x1]) {
          // no obstacles

          super.getBlock(0).setXY((super.getBlockX(0) - 2), (super.getBlockY(0)));
          super.getBlock(1).setXY((super.getBlockX(1) - 1), (super.getBlockY(1) + 1));
          super.getBlock(3).setXY((super.getBlockX(3) + 1), (super.getBlockY(3) + 1));

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
      y0a = super.getBlockY(0) - 1;
      x0b = super.getBlockX(0);
      y0b = super.getBlockY(0) - 2;

      x1 = super.getBlockX(1) + 1;
      y1 = super.getBlockY(1);

      FigUtil.removeFigFromXRay(this, xRay);

      if (FigUtil.pInRange(x0a, y0a) && FigUtil.pInRange(x0b, y0b) && FigUtil.pInRange(x1, y1)) {
        // in range

        if (!xRay[y0a][x0a] && !xRay[y0b][x0b] && !xRay[y1][x1]) {
          // no obstacles

          super.getBlock(0).setXY((super.getBlockX(0)), (super.getBlockY(0) - 2));
          super.getBlock(1).setXY((super.getBlockX(1) - 1), (super.getBlockY(1) - 1));
          super.getBlock(3).setXY((super.getBlockX(3) - 1), (super.getBlockY(3) + 1));

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
