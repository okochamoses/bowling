package bowling.score;

import bowling.exceptions.ExtraScoreException;
import lombok.Getter;

@Getter
public class StrikeFrame extends Frame {

  private final Integer[] values = new Integer[3];
  private int track = 0;

  @Override
  public void addPinFall(int pinFall) {
    if (track >= values.length) {
      throw new ExtraScoreException();
    }

    values[track] = pinFall;
    track++;
    this.addToScore(pinFall);
  }

  @Override
  public Integer getFirst() {
    return values[0];
  }

  @Override
  public boolean isFilled() {
    return true;
  }

}
