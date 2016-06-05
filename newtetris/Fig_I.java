package Figure;

public class Fig_I extends Figure {

  private int x0A;
  private int y0A;
  private int x0B;
  private int y0B;

  private int x1A;
  private int y1A;
  private int x1B;
  private int y1B;

  private int x2A;
  private int y2A;
  private int x2B;
  private int y2B;

  private int x3;
  private int y3;

  public Fig_I() {
    super(3, 0, 4, 0, 5, 0, 6, 0, 0);
    super.getBlock(0).setTextureImage(0);
    super.getBlock(1).setTextureImage(0);
    super.getBlock(2).setTextureImage(0);
    super.getBlock(3).setTextureImage(0);

    x0A = 0;
    y0A = 0;
    x0B = 0;
    y0B = 0;
    x1A = 0;
    y1A = 0;
    x1B = 0;
    y1B = 0;
    x2A = 0;
    y2A = 0;
    x2B = 0;
    y2B = 0;
    x3 = 0;
    y3 = 0;
  }


  public void rotate(boolean[][] xRay) {

    if (super.getRotationPhase() == 0 && !super.getRotationSet()) {// R.P.
                                                                   // =======================================================
                                                                   // 0;
      // possible block points
      x0A = super.getBlockX(0);
      y0A = super.getBlockY(0) - 1;
      x0B = super.getBlockX(0);
      y0B = super.getBlockY(0) - 2;

      x1A = super.getBlockX(1);
      y1A = super.getBlockY(1) - 1;
      x1B = super.getBlockX(1);
      y1B = super.getBlockY(1) - 2;

      x2A = super.getBlockX(2);
      y2A = super.getBlockY(2) - 1;
      x2B = super.getBlockX(2);
      y2B = super.getBlockY(2) - 2;

      x3 = super.getBlockX(2);
      y3 = super.getBlockY(2) + 1;

      FigUtil.removeFigFromXRay(this, xRay);

      if (FigUtil.pInRange(x0A, y0A) && FigUtil.pInRange(x0B, y0B) && FigUtil.pInRange(x1A, y1A)
          && FigUtil.pInRange(x1B, y1B) && FigUtil.pInRange(x2A, y2A) && FigUtil.pInRange(x2B, y2B)
          && FigUtil.pInRange(x3, y3)) {// in range

        if (!xRay[y0A][x0A] && !xRay[y0B][x0B] && !xRay[y1A][x1A] && !xRay[y1B][x1B]
            && !xRay[y2A][x2A] && !xRay[y2B][x2B] && !xRay[y3][x3]) { // no obstacles

          super.getBlock(0).setXY((super.getBlockX(0) + 2), (super.getBlockY(0) - 2));
          super.getBlock(1).setXY((super.getBlockX(1) + 1), (super.getBlockY(1) - 1));
          super.getBlock(3).setXY((super.getBlockX(3) - 1), (super.getBlockY(3) + 1));

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
      x0A = super.getBlockX(0) + 1;
      y0A = super.getBlockY(0);
      x0B = super.getBlockX(0) + 2;
      y0B = super.getBlockY(0);

      x1A = super.getBlockX(1) + 1;
      y1A = super.getBlockY(1);
      x1B = super.getBlockX(1) + 2;
      y1B = super.getBlockY(1);

      x2A = super.getBlockX(2) + 1;
      y2A = super.getBlockY(2);
      x2B = super.getBlockX(2) + 2;
      y2B = super.getBlockY(2);

      x3 = super.getBlockX(2) - 1;
      y3 = super.getBlockY(2);

      FigUtil.removeFigFromXRay(this, xRay);

      if (FigUtil.pInRange(x0A, y0A) && FigUtil.pInRange(x0B, y0B) && FigUtil.pInRange(x1A, y1A)
          && FigUtil.pInRange(x1B, y1B) && FigUtil.pInRange(x2A, y2A) && FigUtil.pInRange(x2B, y2B)
          && FigUtil.pInRange(x3, y3)) {// in range

        if (!xRay[y0A][x0A] && !xRay[y0B][x0B] && !xRay[y1A][x1A] && !xRay[y1B][x1B]
            && !xRay[y2A][x2A] && !xRay[y2B][x2B] && !xRay[y3][x3]) { // no obstacles

          super.getBlock(0).setXY((super.getBlockX(0) + 2), (super.getBlockY(0) + 2));
          super.getBlock(1).setXY((super.getBlockX(1) + 1), (super.getBlockY(1) + 1));
          super.getBlock(3).setXY((super.getBlockX(3) - 1), (super.getBlockY(3) - 1));

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
      x0A = super.getBlockX(0);
      y0A = super.getBlockY(0) + 1;
      x0B = super.getBlockX(0);
      y0B = super.getBlockY(0) + 2;

      x1A = super.getBlockX(1);
      y1A = super.getBlockY(1) + 1;
      x1B = super.getBlockX(1);
      y1B = super.getBlockY(1) + 2;

      x2A = super.getBlockX(2);
      y2A = super.getBlockY(2) + 1;
      x2B = super.getBlockX(2);
      y2B = super.getBlockY(2) + 2;

      x3 = super.getBlockX(2);
      y3 = super.getBlockY(2) - 1;

      FigUtil.removeFigFromXRay(this, xRay);

      if (FigUtil.pInRange(x0A, y0A) && FigUtil.pInRange(x0B, y0B) && FigUtil.pInRange(x1A, y1A)
          && FigUtil.pInRange(x1B, y1B) && FigUtil.pInRange(x2A, y2A) && FigUtil.pInRange(x2B, y2B)
          && FigUtil.pInRange(x3, y3)) {// in range

        if (!xRay[y0A][x0A] && !xRay[y0B][x0B] && !xRay[y1A][x1A] && !xRay[y1B][x1B]
            && !xRay[y2A][x2A] && !xRay[y2B][x2B] && !xRay[y3][x3]) { // no obstacles

          super.getBlock(0).setXY((super.getBlockX(0) - 2), (super.getBlockY(0) + 2));
          super.getBlock(1).setXY((super.getBlockX(1) - 1), (super.getBlockY(1) + 1));
          super.getBlock(3).setXY((super.getBlockX(3) + 1), (super.getBlockY(3) - 1));

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
      x0A = super.getBlockX(0) - 1;
      y0A = super.getBlockY(0);
      x0B = super.getBlockX(0) - 2;
      y0B = super.getBlockY(0);

      x1A = super.getBlockX(1) - 1;
      y1A = super.getBlockY(1);
      x1B = super.getBlockX(1) - 2;
      y1B = super.getBlockY(1);

      x2A = super.getBlockX(2) - 1;
      y2A = super.getBlockY(2);
      x2B = super.getBlockX(2) - 2;
      y2B = super.getBlockY(2);

      x3 = super.getBlockX(2) + 1;
      y3 = super.getBlockY(2);

      FigUtil.removeFigFromXRay(this, xRay);

      if (FigUtil.pInRange(x0A, y0A) && FigUtil.pInRange(x0B, y0B) && FigUtil.pInRange(x1A, y1A)
          && FigUtil.pInRange(x1B, y1B) && FigUtil.pInRange(x2A, y2A) && FigUtil.pInRange(x2B, y2B)
          && FigUtil.pInRange(x3, y3)) {// in range

        if (!xRay[y0A][x0A] && !xRay[y0B][x0B] && !xRay[y1A][x1A] && !xRay[y1B][x1B]
            && !xRay[y2A][x2A] && !xRay[y2B][x2B] && !xRay[y3][x3]) { // no obstacles

          super.getBlock(0).setXY((super.getBlockX(0) - 2), (super.getBlockY(0) - 2));
          super.getBlock(1).setXY((super.getBlockX(1) - 1), (super.getBlockY(1) - 1));
          super.getBlock(3).setXY((super.getBlockX(3) + 1), (super.getBlockY(3) + 1));

          super.setRotationPhase(0);//
          super.setRotationSet(true);
        }
      }

      FigUtil.putFigOnXRay(this, xRay);
    }

    super.setRotationSet(false);// END!
  }

}
