package bowling.score;

import bowling.exceptions.ExtraScoreException;
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
    updateFrames(pinFall);
    if (frames[currentFrame].isFilled() && currentFrame != 9) currentFrame++;
  }

  private void updateFrames(int pinFall) {
    Frame frame = getCurrentFrame(pinFall);
    frame.addPinFall(pinFall);
    handleExtraScoreCases(pinFall);
  }

  private Frame getCurrentFrame(int pinfall) {
    Frame frame = FrameFactory.createFrameFromPinball(pinfall, frames, currentFrame);
    setCurrentFrame(frame);
    return frames[currentFrame];
  }

  private void handleExtraScoreCases(int pinFall) {
    try {
      int lastFrame = currentFrame - 1;
      frames[lastFrame].addPinFall(pinFall);
      frames[lastFrame - 1].addPinFall(pinFall);
    } catch (ExtraScoreException | ArrayIndexOutOfBoundsException ignored) {
    }
  }

  private void setCurrentFrame(Frame frame) {
    if (frames[currentFrame] == null)
      frames[currentFrame] = frame;
  }

  public int getScore() {
    return Arrays.stream(frames)
        .map(frame -> frame == null ? 0 : frame.getScore())
        .mapToInt(Integer::intValue).sum();
  }

}
