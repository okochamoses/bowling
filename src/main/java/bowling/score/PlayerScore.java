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
    updateFrames(pinFall);
    if (getCurrentFrame(pinFall).isFilled() && currentFrame != 9) currentFrame++;
  }

  private void updateFrames(int pinFall) {
    Frame frame = getCurrentFrame(pinFall);
    frame.addPinFall(pinFall);
    handleSpareScoring(pinFall);
    handleStrikeScoring(pinFall);
  }

  private Frame getCurrentFrame(int pinfall) {
    Frame frame = FrameFactory.createFrameFromPinball(pinfall, frames, currentFrame);
    setCurrentFrame(frame);
    return frames[currentFrame];
  }

  private void setCurrentFrame(Frame frame) {
    if(frames[currentFrame] == null)
      frames[currentFrame] = frame;
  }

  private void handleSpareScoring(int pinFall) {
    int lastFrame = currentFrame - 1;
    if (lastFrame < 0 || !frames[lastFrame].isUnhandledSpare()) return;

    frames[lastFrame].addPinFall(pinFall);
  }

  private void handleStrikeScoring(int pinFall) {
    boolean removeStrikeFrame = false;

    for (Frame frame : strikeFrames) {
      StrikeFrame strikeFrame = (StrikeFrame) frame;
      strikeFrame.addPinFall(pinFall);
      if(!strikeFrame.isUnhandledStrike())
        removeStrikeFrame = true;
    }

    if (removeStrikeFrame) strikeFrames.poll();

    addStrikesToStrikeFrame();
  }

  private void addStrikesToStrikeFrame() {
    if (!(frames[currentFrame] instanceof StrikeFrame) || frames[currentFrame] instanceof ThreeValueFrame) {
      return;
    }
    strikeFrames.add(frames[currentFrame]);
  }

  public int getScore() {
    return Arrays.stream(frames)
        .map(frame -> frame == null ? 0 : frame.getScore())
        .mapToInt(Integer::intValue).sum();
  }

}
