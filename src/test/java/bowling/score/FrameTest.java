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
import static org.junit.jupiter.api.Assertions.assertFalse;
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

  @Test
  void aSpareShouldTakeAnExtraScore() {
    frame.addPinFall(3);
    frame.addPinFall(7);
    frame.addPinFall(5);

    assertEquals(15, frame.getScore());
    assertEquals(3, frame.getFirst());
    assertEquals(7, frame.getSecond());
    assertTrue(frame.isSpare());
  }

  @Test
  void aStrikeShouldTake2ExtraScores() {
    frame.addPinFall(10);
    frame.addPinFall(7);
    frame.addPinFall(2);

    assertEquals(19, frame.getScore());
    assertEquals(10, frame.getFirst());
    assertNull(frame.getSecond());
    assertTrue(frame.isStrike());
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

  @Test
  void shouldReturnFilledWhenFrameIsStrike() {
    frame.addPinFall(10);

    assertTrue(frame.isFilled());
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

  private List<Integer> toArray(String str) {
    return Arrays.stream(str.split(","))
        .map(Integer::valueOf).collect(Collectors.toList());
  }

}
