package bowling.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}