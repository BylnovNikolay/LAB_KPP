package Figure;

public class FigUtil {

  public static boolean pInRange(int x, int y) {
    if (x >= 0 && x < 10 && y >= 0 && y < 20) {
      return true;
    }
    return false;
  }

  public static void removeFigFromXRay(Figure fig, boolean[][] ar) {
    for (int i = 0; i < 4; i++) {
      ar[fig.getBlockY(i)][fig.getBlockX(i)] = false;
    }
  }

  public static void putFigOnXRay(Figure fig, boolean[][] ar) {
    for (int i = 0; i < 4; i++) {
      ar[fig.getBlockY(i)][fig.getBlockX(i)] = true;
    }
  }
}
