package bowling.score;

import bowling.exceptions.ExtraScoreException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Frame {
  private static final int FOUL = -1;
  private Integer first;
  private Integer second;
  private int score;
  private Integer spareExtraPinFall;

  public void addPinFall(int pinFall) {
    updateNormalFrameScore(pinFall);
    addToScore(pinFall);
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

  protected void addToScore(int pinFall) {
    score += pinFall == FOUL ? 0 : pinFall;
  }

  protected boolean positionsFilled() {
    return first != null && second != null;
  }

  public int getScore() {
    return score;
  }

  public boolean isFilled() {
    return positionsFilled();
  }

}
