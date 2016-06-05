package GameData;

public class Speed {

  private long[] gears;
  private int level;

  public Speed() {
    gears = new long[10];
    level = 1;

    gears[1] = 1000L;
    gears[2] = 900L;
    gears[3] = 800L;
    gears[4] = 700L;
    gears[5] = 600L;
    gears[6] = 500L;
    gears[7] = 250L;
    gears[8] = 200L;
    gears[9] = 150L;
  }

  public long getSpeed() {
    return gears[this.level];
  }

  public void levelUp() {
    this.level++;
  }
  public void level3() {
    for (int i = 0 ; i < 3 ; i++) {
      this.level++;
    }
  }
}
