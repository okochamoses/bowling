package bowling.score;

import lombok.Getter;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

@Getter
public class PlayerScore {
  private final Player player;
  private final Frame[] frames;
  private final Deque<Frame> strikeFrames;
  private int currentFrame;

  public PlayerScore(String playerName) {
    this.player = new Player(playerName);
    this.frames = new Frame[10];
    this.strikeFrames = new LinkedList<>();
    this.currentFrame = 0;
  }

  public void addPinFall(int pinFall) {
    updatePinFall(pinFall);
    if (getCurrentFrame().isFilled()) currentFrame++;
  }

  private void updatePinFall(int pinFall) {
    Frame frame = getCurrentFrame();
    frame.addPinFall(pinFall);
    handleSpareScoring(pinFall);
    handleStrikeScoring(pinFall);
  }

  private void handleSpareScoring(int pinFall) {
    int lastFrame = currentFrame - 1;

    if (lastFrame >= 0 && frames[lastFrame].isUnhandledSpare()) {
      frames[lastFrame].addPinFall(pinFall);
    }
  }

  private void handleStrikeScoring(int pinFall) {
    boolean removeStrikeFrame = false;

    for (Frame frame : strikeFrames) {
      frame.addPinFall(pinFall);
      removeStrikeFrame = !frame.isUnhandledStrike();
    }

    if (removeStrikeFrame) strikeFrames.poll();

    addStrikesToStrikeFrame();
  }

  private void addStrikesToStrikeFrame() {
    if(frames[currentFrame].isStrike())
      strikeFrames.add(frames[currentFrame]);
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
