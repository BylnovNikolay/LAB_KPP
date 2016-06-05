package GameData;

public class Level {

  private String[] levels;
  private int currentLevel;

  public Level() {
    levels = new String[10];
    currentLevel = 1;

    levels[0] = "";
    levels[1] = "I";
    levels[2] = "II";
    levels[3] = "III";
    levels[4] = "IV";
    levels[5] = "V";
    levels[6] = "VI";
    levels[7] = "VII";
    levels[8] = "VIII";
    levels[9] = "IX";
  }

  public String getLevel() {
    return this.levels[currentLevel];
  }

  public void levelUp() {
    this.currentLevel++;
  }
  
  public void level3() {
    for (int i = 0 ; i < 3 ; i++) {
      this.currentLevel++;
    }
  }
  public int getLevelNr() {
    return this.currentLevel;
  }
}
