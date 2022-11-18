package bowling.score;

import bowling.exceptions.ExtraScoreException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Frame {
  private static final int FOUL = -1;
  private final Integer[] strikeExtraPinFalls = new Integer[2];
  private Integer first;
  private Integer second;
  private int score;
  private Integer spareExtraPinFall;

  public void addPinFall(int pinFall) {
    addToScore(pinFall);

    if (isSpare()) {
      addToSpareExtraPinFall(pinFall);
    } else if (isStrike()) {
      addToStrikeExtraPinFalls(pinFall);
    } else {
      updateNormalFrameScore(pinFall);
    }
  }

  protected void addToScore(int pinFall) {
    score += pinFall == FOUL ? 0 : pinFall;
  }

  public boolean isSpare() {
    return positionsFilled() && first + second == 10;
  }

  private void addToSpareExtraPinFall(int pinFall) {
    if (spareExtraPinFall != null)
      throw new ExtraScoreException();
    spareExtraPinFall = pinFall;
  }

  public boolean isStrike() {
    return first != null && first == 10;
  }

  private void addToStrikeExtraPinFalls(int pinFall) {
    if (strikeExtraPinFalls[0] != null && strikeExtraPinFalls[1] != null)
      throw new ExtraScoreException();

    if (strikeExtraPinFalls[0] == null) {
      strikeExtraPinFalls[0] = pinFall;
    } else {
      strikeExtraPinFalls[1] = pinFall;
    }
  }

  protected void updateNormalFrameScore(int pinFall) {
    if (positionsFilled())
      throw new ExtraScoreException();

    if (first == null) {
      first = pinFall;
    } else {
      second = pinFall;
    }
  }

  private boolean positionsFilled() {
    return first != null && second != null;
  }

  public int getScore() {
    return score;
  }

  public boolean isFilled() {
    return isStrike() || positionsFilled();
  }

  public boolean isUnhandledSpare() {
    return this.isSpare() && getSpareExtraPinFall() == null;
  }

  public boolean isUnhandledStrike() {
    return isStrike() && (getStrikeExtraPinFalls()[0] == null || getStrikeExtraPinFalls()[1] == null);
  }

}
