package bowling.score;

import bowling.exceptions.ExtraScoreException;
import lombok.Getter;

@Getter
public class StrikeFrame extends Frame {

  private final Integer[] strikeExtraPinFalls = new Integer[2];

  @Override
  public void addPinFall(int pinFall) {
    if (getFirst() == null) {
      this.setFirst(10);
    } else {
      updateExtras(pinFall);
    }
    this.addToScore(pinFall);
  }

  private void updateExtras(int pinFall) {
    if (strikeExtraPinFalls[0] != null && strikeExtraPinFalls[1] != null)
      throw new ExtraScoreException();

    if (strikeExtraPinFalls[0] == null) {
      strikeExtraPinFalls[0] = pinFall;
    } else {
      strikeExtraPinFalls[1] = pinFall;
    }
  }

  @Override
  public boolean isFilled() {
    return true;
  }

  public boolean isUnhandledStrike() {
    return getStrikeExtraPinFalls()[0] == null || getStrikeExtraPinFalls()[1] == null;
  }

}
