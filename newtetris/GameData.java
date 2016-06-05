package GameData;

public class GameData {

  private static Score score;
  private static Speed speed;
  private static Level level;
  private static boolean changedScore;
  private static int fl;
  
  public GameData() {
    score = new Score();
    speed = new Speed();
    level = new Level();

    changedScore = false;
    fl = 0;
  }

  public void addPoints() {
    score.addPoints();
    changedScore = false;
  }

  public int getPoints() {
    return score.getScore();
  }

  public void dataRefresh() {
    upLevel(40, 1); // level 2
    upLevel(80, 2); // level 3
    upLevel(150, 3); // level 4
    upLevel(240, 4); // level 5
    upLevel(350, 5); // level 6
    upLevel(480, 6); // level 7
    upLevel(600, 7); // level 8
    upLevel(700, 8); // level 9
  }

  public static void upLevel(int points, int previousLevel) {

    if (score.getScore() >= points && level.getLevelNr() == previousLevel) {
      if (!changedScore) {
        level.levelUp();
        speed.levelUp();
        changedScore = true;
      }
    }
  }
  public static void level() { 
    if(fl<2) {
      level.level3();
      speed.level3();
      fl++;
      changedScore = true;
    }    
  }
  
  public long getMiliseconds() {
    return speed.getSpeed();
  }

  public boolean changedLevel() {
    return changedScore;
  }

  public String getLevel() {
    return level.getLevel();
  }
}
