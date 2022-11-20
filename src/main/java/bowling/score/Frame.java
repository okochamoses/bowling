package bowling.score;

import bowling.exceptions.ExtraScoreException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@ToString
public class Frame {
  private static final int FOUL = -1;
  private static final int DEFAULT_FRAME_LENGTH = 2;
  private int score;
  private Integer[] values;
  private int track = 0;

  public Frame() {
    this(DEFAULT_FRAME_LENGTH);
  }

  public Frame(int frameLength) {
    values = new Integer[frameLength];
  }

  public void addPinFall(int pinFall) {
    if (track >= values.length) {
      throw new ExtraScoreException();
    }

    values[track] = pinFall;
    track++;
    addToScore(pinFall);
  }

  public Integer getFirst() {
    return values[0];
  }

  public Integer getSecond() {
    return values[1];
  }

  protected void addToScore(int pinFall) {
    score += pinFall == FOUL ? 0 : pinFall;
  }

  public int getScore() {
    return score;
  }

  public boolean isFilled() {
     return getFirst() != null && getSecond() != null;
  }

}
