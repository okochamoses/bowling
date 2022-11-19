package bowling.score;

public class FrameFactory {
  public static Frame createFrameFromPinball(int pinfall, Frame[] frames, int currentFrame) {
    Frame frame = frames[currentFrame];

    if (frame != null &&
        frame.getFirst() != null &&
        frame.getFirst() + pinfall == 10
    ) {
      return new SpareFrame();
    }

    if (frame != null) {
      return frame;
    }

    if (currentFrame == 9) {
      return new ThreeValueFrame();
    }

    if (pinfall == 10) {
      return new StrikeFrame();
    }

    return new Frame();
  }

}
