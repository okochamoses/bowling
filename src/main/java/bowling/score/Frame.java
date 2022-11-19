package bowling.score;

import bowling.exceptions.ExtraScoreException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Frame {
  private static final int FOUL = -1;
  private Integer first;
  private Integer second;
  private int score;
  private Integer spareExtraPinFall;

  public void addPinFall(int pinFall) {
    addToScore(pinFall);

    if (isSpare()) {
      addToSpareExtraPinFall(pinFall);
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
    return positionsFilled();
  }

  public boolean isUnhandledSpare() {
    return this.isSpare() && getSpareExtraPinFall() == null;
  }

}
