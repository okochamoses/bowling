package bowling.readers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileScoreReaderTest {

  private final ScoreReader<String> scoreReader = new FileScoreReader();
  private static final String EXPECTED ="Jeff\t10\nJohn\t3\nJohn\t7\nJeff\t7";

  @Test
  void shouldReadValidScoresFromFile() {
    String fileString = scoreReader.readScores("src/test/resources/unit/basic.txt");

    assertEquals(EXPECTED, fileString);
  }

}