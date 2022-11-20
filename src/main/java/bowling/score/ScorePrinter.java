package bowling.score;

import bowling.readers.Parser;
import bowling.readers.ScoreReader;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ScorePrinter<T> {
  private final Parser parser;
  private final ScoreReader<T> reader;

  public void printScore(T resource) {
    System.out.println(getScoreBoard(resource));
  }

  public String getScoreBoard(T resource) {
    Collection<PlayerScore> playerScores = allocateScores(resource);
    return getScoreString(playerScores);
  }

  private Collection<PlayerScore> allocateScores(T resource) {
    HashMap<String, PlayerScore> playerScores = new HashMap<>();
    Map<String, List<Integer>> map = parser.parse(reader.readScores(resource));

    map.keySet().forEach(playerName -> {
      playerScores.putIfAbsent(playerName, new PlayerScore(playerName));
      map.get(playerName).forEach(score -> playerScores.get(playerName).addPinFall(score));
    });

    return playerScores.values();
  }

  private String getScoreString(Collection<PlayerScore> playerScores) {
    StringBuilder sb = new StringBuilder();
    sb.append(getHeader());

    for (PlayerScore playerScore : playerScores) {
      sb.append(printPlayerScore(playerScore)).append("\n");
    }

    return sb.toString();
  }

  private String getHeader() {
    return "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n";
  }

  private String printPlayerScore(PlayerScore playerScore) {
    StringBuilder sb = new StringBuilder();

    printFrames(playerScore, sb);

    sb.append("\n");
    sb.append("Score\t\t");

    int scoreSoFar = 0;
    for (Frame frame : playerScore.getFrames()) {
      scoreSoFar += frame.getScore();
      sb.append(scoreSoFar)
          .append("\t\t");
    }
    return String.format("%s\n" +
            "Pinfalls\t%s"
        , playerScore.getPlayer().getName(), sb);

  }

  private void printFrames(PlayerScore playerScore, StringBuilder sb) {
    for (Frame frame : playerScore.getFrames()) {
      if (frame instanceof FinalFrame) {
        printLastFrame(sb, (FinalFrame) frame);
      } else {
        if (frame instanceof StrikeFrame) {
          printStrike(sb, frame);
        } else if (frame instanceof SpareFrame) {
          printSpare(sb, frame);
        } else {
          printNormalFrame(sb, frame);
        }
      }
    }
  }

  private void printLastFrame(StringBuilder sb, FinalFrame finalFrame) {
    sb.append(processData(finalFrame.getFirst()))
        .append("\t")
        .append(processData(finalFrame.getSecond()))
        .append("\t")
        .append(finalFrame.getThird() == null ? "" : processData(finalFrame.getThird()))
        .append("\t");
  }

  private void printStrike(StringBuilder sb, Frame frame) {
    sb.append("\t")
        .append(processData(frame.getFirst()))
        .append("\t");
  }

  private void printSpare(StringBuilder sb, Frame frame) {
    sb.append(processData(frame.getFirst()))
        .append("\t/\t");
  }

  private void printNormalFrame(StringBuilder sb, Frame frame) {
    sb.append(processData(frame.getFirst()))
        .append("\t")
        .append(processData(frame.getSecond()))
        .append("\t");
  }

  private String processData(int number) {
    if (number == -1) {
      return "F";
    }
    if (number == 10) {
      return "X";
    }
    return String.valueOf(number);
  }

}
