package bowling;

import bowling.readers.FileScoreReader;
import bowling.readers.LineParser;
import bowling.readers.Parser;
import bowling.readers.ScoreReader;
import bowling.score.ScorePrinter;

import java.util.Scanner;

public class BowlingRunner {

  public static void main(String[] args) {
    Parser parser = new LineParser("\t");
    ScoreReader reader = new FileScoreReader();
    ScorePrinter<String> scorePrinter = new ScorePrinter<>(parser, reader);

    Scanner scanner = new Scanner(System.in);  // Create a Scanner object
    System.out.print("Enter file path: ");

    String filePath = scanner.nextLine();

    scorePrinter.printScore(filePath);
  }
}
