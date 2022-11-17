package bowling.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrameTest {

  Frame frame;

  @BeforeEach
  void setUp() {
    frame = new Frame();
  }

  @Test
  void shouldAddFirstPinFallToFrameFirstPosition() {
    frame.addPinFall(7);

    assertEquals(7, frame.getFirst());
    assertNull(frame.getSecond());
  }

  @Test
  void shouldAddSecondPinFallToFrameSecondPositionPosition() {
    frame.addPinFall(7);
    frame.addPinFall(2);

    assertEquals(7, frame.getFirst());
    assertEquals(2, frame.getSecond());
  }

  @Test
  void shouldHaveScoreValue() {
    frame.addPinFall(7);
    frame.addPinFall(2);

    assertEquals(9, frame.getScore());
  }

  @Test
  void frameWithTwoPinFallTotalling10IsASpare() {
    frame.addPinFall(7);
    frame.addPinFall(3);

    assertTrue(frame.isSpare());
  }

  @Test
  void frameWithFirstScoreAdditionAs10ShouldBeAStrike() {
    frame.addPinFall(10);

    assertTrue(frame.isStrike());
  }

  @Test
  void aStrikeCanBeDifferentiatedFromASpare() {
    frame.addPinFall(10);

    assertTrue(frame.isStrike());
    assertFalse(frame.isSpare());
  }

}