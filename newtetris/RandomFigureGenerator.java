package GameData;

import java.util.Random;

import Figure.Fig_I;
import Figure.Fig_J;
import Figure.Fig_L;
import Figure.Fig_O;
import Figure.Fig_S;
import Figure.Fig_T;
import Figure.Fig_Z;
import Figure.Figure;

public class RandomFigureGenerator {
  private int[] numbers;
  private Random random;
  private int index;
  private int nmb;

  public RandomFigureGenerator() {
    numbers = new int[7];
    random = new Random();
    index = 0;
    nmb = 0;

    fillArrayWithRandomNumbers();

  }

  public int getnmb() {
    // System.out.println(this.nmb);
    return this.nmb;
  }

  public void fillArrayWithRandomNumbers() {

    fillArrayWithMinusOne(); // cleaning the array before filling;
    index = 0; // restarting the index;

    for (int i = 0; i < 6; i++) {
      nmb = random.nextInt(7);

      while (theNumberIsInArray(nmb)) {
        nmb = random.nextInt(7);
      }

      numbers[i] = nmb;
    }
  }

  public boolean theNumberIsInArray(int nr) {
    for (int i = 0; i < numbers.length; i++) {
      if (nr == numbers[i]) {
        return true;
      }
    }

    return false;
  }

  public void fillArrayWithMinusOne() {
    for (int i = 0; i < 6; i++) {
      numbers[i] = -1;
    }
  }

  public Figure giveFigure(int i) {
    if (i == 0) {
      return new Fig_I();
    }
    if (i == 1) {
      return new Fig_J();
    }
    if (i == 2) {
      return new Fig_L();
    }
    if (i == 3) {
      return new Fig_O();
    }
    if (i == 4) {
      return new Fig_S();
    }
    if (i == 5) {
      return new Fig_T();
    }
    if (i == 6) {
      return new Fig_Z();
    }
    return null;
  }


  public Figure giveRandomFigure() {
    if (index > 6) {
      fillArrayWithRandomNumbers();
    }

    nmb = numbers[index];
    index++;

    getnmb();

    if (nmb == 0) {
      return new Fig_I();
    }
    if (nmb == 1) {
      return new Fig_J();
    }
    if (nmb == 2) {
      return new Fig_L();
    }
    if (nmb == 3) {
      return new Fig_O();
    }
    if (nmb == 4) {
      return new Fig_S();
    }
    if (nmb == 5) {
      return new Fig_T();
    }
    if (nmb == 6) {
      return new Fig_Z();
    }
    return null;
  }

}
