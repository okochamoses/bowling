package bowling.readers;

/**
 * Read data from IO
 * @param <T> argument type
 */
public interface ScoreReader<T> {
  String readScores(T keyData);

}
