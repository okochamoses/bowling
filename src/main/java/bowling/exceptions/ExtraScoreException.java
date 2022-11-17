package bowling.exceptions;

public class ExtraScoreException extends RuntimeException {
  public ExtraScoreException() {
    super("Frame cannot add extra score");
  }

}
