package bowling.integrationtests;

import bowling.readers.FileScoreReader;
import bowling.readers.LineParser;
import bowling.readers.Parser;
import bowling.readers.ScoreReader;
import bowling.score.ScorePrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoresIT {
  ScorePrinter<String> scorePrinter;

  @BeforeEach
  void setUp() {
    Parser parser = new LineParser("\t");
    ScoreReader<String> reader = new FileScoreReader();
    scorePrinter = new ScorePrinter<>(parser, reader);
  }

  @Test
  void canPrintPerfectScore() {
    String expected =
        "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n" +
            "Carl\n" +
            "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX\t\n" +
            "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\t\t\n";


    String scoreBoard = scorePrinter.getScoreBoard("src/test/resources/positive/perfect.txt");

    assertEquals(expected, scoreBoard);
  }

  @Test
  void canHandleTwoPlayers() {
    String expected =
        "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n" +
            "Jeff\n" +
            "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1\t\n" +
            "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\t\t\nJohn\n" +
            "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\t\n" +
            "Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151\t\t\n";


    String scoreBoard = scorePrinter.getScoreBoard("src/test/resources/positive/scores.txt");

    assertEquals(expected, scoreBoard);
  }

  @Test
  void canHandleZeroScore() {
    String expected =
        "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n" +
            "Moses\n" +
            "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t\t\n" +
            "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t\n";


    String scoreBoard = scorePrinter.getScoreBoard("src/test/resources/positive/zero.txt");

    assertEquals(expected, scoreBoard);
  }

}
