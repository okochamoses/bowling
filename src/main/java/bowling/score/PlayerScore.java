package bowling.score;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class PlayerScore {
  private final Player player;
  private final Frame[] frames;
  private int currentFrame;

  public PlayerScore(String playerName) {
    this.player = new Player(playerName);
    this.frames = new Frame[10];
    this.currentFrame = 0;
  }

  public void addPinFall(int pinFall) {
    updatePinFall(pinFall);
    if (getCurrentFrame().isFilled()) currentFrame++;
  }

  private void updatePinFall(int pinFall) {
    Frame frame = getCurrentFrame();
    int lastFrame = currentFrame - 1;

    if (lastFrame >= 0
        && (frames[lastFrame].isUnhandledSpare()
        || frames[lastFrame].isUnhandledStrike())
    ) {
      frames[lastFrame].addPinFall(pinFall);
    }

    frame.addPinFall(pinFall);
  }

  private Frame getCurrentFrame() {
    if (frames[currentFrame] == null) {
      frames[currentFrame] = new Frame();
    }
    return frames[currentFrame];
  }

  public int getScore() {
    return Arrays.stream(frames)
        .map(frame -> frame == null ? 0 : frame.getScore())
        .mapToInt(Integer::intValue).sum();
  }

}
