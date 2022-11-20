package bowling.score;

import bowling.exceptions.ExtraScoreException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
  void aSpareShouldTakeAnExtraScore() {
    SpareFrame spareFrame = new SpareFrame();
    spareFrame.addPinFall(3);
    spareFrame.addPinFall(7);
    spareFrame.addPinFall(5);

    assertEquals(15, spareFrame.getScore());
    assertEquals(3, spareFrame.getFirst());
    assertEquals(7, spareFrame.getSecond());
  }

  @Test
  void aStrikeShouldTake2ExtraScores() {
    StrikeFrame strikeFrame = new StrikeFrame();
    strikeFrame.addPinFall(10);
    strikeFrame.addPinFall(7);
    strikeFrame.addPinFall(2);

    assertEquals(19, strikeFrame.getScore());
    assertEquals(10, strikeFrame.getFirst());
    assertNull(strikeFrame.getSecond());
  }

  @ParameterizedTest
  @CsvSource(value = {
      "'7,3,4,5'", // spare
      "'10,5,3,6,8'", // strike
      "'2,2,6'" // normal
  })
  void shouldThrowExceptionIfAdditionalScoreIsAdded(String values) {
    assertThrows(ExtraScoreException.class, () -> toArray(values).forEach(frame::addPinFall));
  }

  private List<Integer> toArray(String str) {
    return Arrays.stream(str.split(","))
        .map(Integer::valueOf).collect(Collectors.toList());
  }

  @Test
  void shouldReturnFilledWhenFrameIsStrike() {
    StrikeFrame strikeFrame = new StrikeFrame();
    strikeFrame.addPinFall(10);

    assertTrue(strikeFrame.isFilled());
  }

  @Test
  void shouldReturnFilledWhenBothFramePositionsHaveBeenFilled() {
    frame.addPinFall(3);
    frame.addPinFall(6);

    assertTrue(frame.isFilled());

  }

  @Test
  void shouldNotAffectFrameScoreWhenFoulIsAdded() {
    frame.addPinFall(-1);
    frame.addPinFall(6);

    assertEquals(6, frame.getScore());
    assertEquals(6, frame.getScore());
  }

}
