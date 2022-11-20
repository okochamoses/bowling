package bowling.score;

import bowling.exceptions.ExtraScoreException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ThreeValueFrameTest {

  @Test
  void shouldThrowExceptionWhenAThirdValueIsAddedAndExistingIsNotSpareOrStrike() {
    ThreeValueFrame frame = new ThreeValueFrame();
    frame.addPinFall(1);
    frame.addPinFall(1);

    assertThrows(ExtraScoreException.class, () -> frame.addPinFall(2));
  }

  @Test
  void shouldHandle3Values() {
    ThreeValueFrame frame = new ThreeValueFrame();
    frame.addPinFall(10);
    frame.addPinFall(1);

    assertDoesNotThrow(() -> frame.addPinFall(2));
  }

}