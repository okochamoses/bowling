package bowling.score;

import bowling.exceptions.ExtraScoreException;

public class FinalFrame extends Frame {
  private static final int FRAME_LENGTH = 3;

  public FinalFrame() {
    super(FRAME_LENGTH);
  }

  @Override
  public void addPinFall(int pinFall) {
    if (!isStrike() && !isSpare() && getTrack() > 1) throw new ExtraScoreException();
    super.addPinFall(pinFall);
  }

  private boolean isStrike() {
    return getFirst() != null && getFirst() == 10;
  }

  private boolean isSpare() {
    return isFilled() && getFirst() + getSecond() == 10;
  }

  public Integer getThird() {
    return getValues()[2];
  }

}
