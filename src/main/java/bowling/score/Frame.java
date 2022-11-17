package bowling.score;

import lombok.Getter;

@Getter
public class Frame {
  private Integer first;
  private Integer second;
  private int score;

  public void addPinFall(int pinFall) {
    if (first == null) {
      first = pinFall;
    } else {
      second = pinFall;
    }
    score += pinFall;
  }

  public int getScore() {
    return score;
  }

  public boolean isSpare() {
    return score == 10 && first != null && second != null;
  }

  public boolean isStrike() {
    return first == 10;
  }

}
