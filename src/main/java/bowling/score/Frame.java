package bowling.score;

import bowling.exceptions.ExtraScoreException;
import lombok.Getter;

@Getter
public class Frame {
  private static final int FOUL = -1;
  private final Integer[] strikeExtraPinFalls = new Integer[2];
  private Integer first;
  private Integer second;
  private int score;
  private Integer spareExtraPinFall;

  public void addPinFall(int pinFall) {
    if(pinFall != FOUL) {
      score += pinFall;
    }

    if (isSpare()) {
      addToSpareExtraPinFall(pinFall);
    } else if (isStrike()) {
      addToStrikeExtraPinFalls(pinFall);
    } else {
      updateNormalFrameScore(pinFall);
    }
  }

  public boolean isSpare() {
    return first != null && second != null && first + second == 10;
  }

  private void addToSpareExtraPinFall(int pinFall) {
    if (spareExtraPinFall != null)
      throw new ExtraScoreException("Frame cannot add extra score");
    spareExtraPinFall = pinFall;
  }

  public boolean isStrike() {
    return first != null && first == 10;
  }

  private void addToStrikeExtraPinFalls(int pinFall) {
    if (strikeExtraPinFalls[0] != null && strikeExtraPinFalls[1] != null)
      throw new ExtraScoreException("Frame cannot add extra score");

    if (strikeExtraPinFalls[0] == null) {
      strikeExtraPinFalls[0] = pinFall;
    } else {
      strikeExtraPinFalls[1] = pinFall;
    }
  }

  private void updateNormalFrameScore(int pinFall) {
    if (first != null && second != null)
      throw new ExtraScoreException("Frame cannot add extra score");

    if (first == null) {
      first = pinFall;
    } else {
      second = pinFall;
    }
  }

  public int getScore() {
    return score;
  }

  public boolean isFilled() {
    return isStrike() || (first != null  && second != null);
  }

  public boolean isUnhandledSpare() {
    return this.isSpare() && getSpareExtraPinFall() == null;
  }

  public boolean isUnhandledStrike() {
    return isStrike() && (getStrikeExtraPinFalls()[0] == null || getStrikeExtraPinFalls()[1] == null);
  }

}
