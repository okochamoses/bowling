package bowling.score;

public class SpareFrame extends Frame {

  private static final int FRAME_LENGTH = 3;

  public SpareFrame() {
    super(FRAME_LENGTH);
  }

  @Override
  public boolean isFilled() {
    return getFirst() != null && getSecond() != null;
  }

}
