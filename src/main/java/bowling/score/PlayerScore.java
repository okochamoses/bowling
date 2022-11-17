package bowling.score;

import lombok.Getter;

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
    Frame frame = getCurrentFrame();
    frame.addPinFall(pinFall);
    if(frame.isFilled()) currentFrame++;
  }

  private Frame getCurrentFrame() {
    if(frames[currentFrame] == null) {
      frames[currentFrame] = new Frame();
    }
    return frames[currentFrame];
  }

}
