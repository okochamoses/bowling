package bowling.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

  @Test
  void gettingAStrikeShouldUseNextFrameForNextScore() {
    playerScore.addPinFall(10);
    playerScore.addPinFall(5);

    Frame frame = playerScore.getFrames()[1];
    assertEquals(5, frame.getFirst());
  }

  @Test
  void aFrameWithASpareAddsTheNextPinFallToItsScore() {
    playerScore.addPinFall(3);
    playerScore.addPinFall(7);
    playerScore.addPinFall(5);
    playerScore.addPinFall(3);

    Frame frame = playerScore.getFrames()[0];
    assertEquals(15, frame.getScore());
  }

  @Test
  void aFrameWithAStrikeAddsTheNextTwoScoresToItsScore() {
    playerScore.addPinFall(10);
    playerScore.addPinFall(7);
    playerScore.addPinFall(5);

    Frame frame = playerScore.getFrames()[0];

    assertEquals(22, frame.getScore());
  }

  @Test
  void aStrikeShouldAddValueTheNextTwoPinFallsToItsScore() {
    playerScore.addPinFall(10);
    playerScore.addPinFall(5);
    playerScore.addPinFall(4);

    assertEquals(19, playerScore.getFrames()[0].getScore());
    assertEquals(28, playerScore.getScore());
  }

  @Test
  void lastFrameIsAThreeValueFrame() {
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);

    assertTrue(playerScore.getFrames()[9] instanceof ThreeValueFrame);
  }

  @Test
  void lastStrikeGets2AdditionalRolls() {
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);

    ThreeValueFrame frame = (ThreeValueFrame) playerScore.getFrames()[9];

    assertEquals(10, frame.getFirst());
    assertEquals(30, frame.getScore());
    assertEquals(300, playerScore.getScore());
  }

  @Test
  void lastSpareGetsAdditionalRoll() {
    playerScore.addPinFall(10);
    playerScore.addPinFall(7);
    playerScore.addPinFall(3);
    playerScore.addPinFall(9);
    playerScore.addPinFall(0);
    playerScore.addPinFall(10);
    playerScore.addPinFall(0);
    playerScore.addPinFall(8);
    playerScore.addPinFall(8);
    playerScore.addPinFall(2);
    playerScore.addPinFall(0);
    playerScore.addPinFall(6);
    playerScore.addPinFall(10);
    playerScore.addPinFall(10);
    playerScore.addPinFall(2);
    playerScore.addPinFall(8);
    playerScore.addPinFall(1);

    ThreeValueFrame frame = (ThreeValueFrame) playerScore.getFrames()[9];

    assertEquals(2, frame.getFirst());
    assertEquals(8, frame.getSecond());
    assertEquals(1, frame.getThird());
  }
}