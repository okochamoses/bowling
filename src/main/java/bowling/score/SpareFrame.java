package bowling.score;

import bowling.exceptions.ExtraScoreException;

public class SpareFrame extends Frame {

  private final Integer[] values = new Integer[3];
  private int track = 0;

  @Override
  public void addPinFall(int pinFall) {
    if (track > 2) {
      throw new ExtraScoreException();
    }

    addToScore(pinFall);
    values[track] = pinFall;
    track++;
  }

  @Override
  public Integer getSpareExtraPinFall() {
    return values[2];
  }

  @Override
  public boolean isFilled() {
    return getFirst() != null && getSecond() != null;
  }

  @Override
  public Integer getFirst() {
    return values[0];
  }

  @Override
  public Integer getSecond() {
    return values[1];
  }

}
