package bowling.score;

public class FrameFactory {
  private FrameFactory() {
  }

  public static Frame createFrameFromPinball(int pinfall, Frame[] frames, int currentFrame) {
    Frame frame = frames[currentFrame];

    if (currentFrame == 9 && frame == null) {
      return new ThreeValueFrame();
    }
    if (currentFrame == 9) {
      return frame;
    }

    if (pinfall == 10) {
      return new StrikeFrame();
    }

    if (frame != null &&
        frame.getFirst() != null &&
        frame.getFirst() + pinfall == 10
    ) {
      SpareFrame spareFrame = new SpareFrame();
      spareFrame.addPinFall(frame.getFirst());
      frames[currentFrame] = spareFrame;
      return spareFrame;
    }

    if (frame != null) {
      return frame;
    }

    return new Frame();
  }

}
