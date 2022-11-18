package bowling.score;

import bowling.exceptions.ExtraScoreException;
import lombok.Getter;

@Getter
public class ThreeValueFrame extends Frame {
  private Integer third;

  @Override
  public void addPinFall(int pinFall) {
    if (third != null) throw new ExtraScoreException();

    addToScore(pinFall);

    if (isSpare()) {
      third = pinFall;
    } else if (isStrike()) {
      updateStrikePinFalls(pinFall);
    } else {
      updateNormalFrameScore(pinFall);
    }
  }

  private void updateStrikePinFalls(int pinFall) {
    if (this.getSecond() == null) {
      this.setSecond(pinFall);
    } else {
      this.third = pinFall;
    }
  }

}
