package bowling.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlayerScoreTest {
  PlayerScore playerScore;

  @BeforeEach
  void setUp() {
    playerScore = new PlayerScore("Moses");
  }

  @Test
  void shouldInitializeWithPlayerName() {
    assertNotNull(playerScore.getPlayer());
  }

  @Test
  void shouldContainFramesContainer() {
    assertNotNull(playerScore.getFrames());
  }
  @Test
  void shouldAddPinFallsSequentiallyssss() {
    playerScore.addPinFall(1);
    playerScore.addPinFall(5);

    Frame frame1 = playerScore.getFrames()[0];

    assertEquals(1, frame1.getFirst());
    assertEquals(5, frame1.getSecond());
  }

  @Test
  void shouldPopulateNextFrameWhenPreviousFrameIsFilled() {
    playerScore.addPinFall(1);
    playerScore.addPinFall(5);
    playerScore.addPinFall(3);

    Frame frame1 = playerScore.getFrames()[0];
    Frame frame2 = playerScore.getFrames()[1];

    assertEquals(1, frame1.getFirst());
    assertEquals(5, frame1.getSecond());
    assertEquals(3, frame2.getFirst());
  }

  @Test
  void shouldGetTotalOfAddedScores() {
    playerScore.addPinFall(2);
    playerScore.addPinFall(1);

    assertEquals(3, playerScore.getScore());
  }

  @Test
  void shouldTakeFoulAsZeroWhileGettingTotalScore() {
    playerScore.addPinFall(5);
    playerScore.addPinFall(-1);
    playerScore.addPinFall(3);
    playerScore.addPinFall(4);

    assertEquals(12, playerScore.getScore());
  }
}