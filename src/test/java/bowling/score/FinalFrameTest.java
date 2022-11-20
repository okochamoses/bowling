package bowling.score;

import bowling.exceptions.ExtraScoreException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FinalFrameTest {

  @Test
  void shouldThrowExceptionWhenAThirdValueIsAddedAndExistingIsNotSpareOrStrike() {
    FinalFrame frame = new FinalFrame();
    frame.addPinFall(1);
    frame.addPinFall(1);

    assertThrows(ExtraScoreException.class, () -> frame.addPinFall(2));
  }

  @Test
  void shouldHandle3Values() {
    FinalFrame frame = new FinalFrame();
    frame.addPinFall(10);
    frame.addPinFall(1);

    assertDoesNotThrow(() -> frame.addPinFall(2));
  }

}