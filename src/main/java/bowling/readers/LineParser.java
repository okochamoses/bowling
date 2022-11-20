package bowling.readers;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class LineParser implements Parser {

  private final String delimiter;

  /**
   * @param data the accepted format for a line is "name \t score"
   *             where name is a String, and score is an int or "F"(foul)
   * @return return the score
   * @throws IllegalArgumentException this is thrown if the supplied line does not math the accepted format
   */
  public Map<String, List<Integer>> parse(String data) throws IllegalArgumentException {
    try {
      Map<String, List<Integer>> map = new HashMap<>();

      String[] lines = data.replace("\r", "").split("\n");

      for(String line : lines) {
        String[] values = line.split(delimiter);
        map.putIfAbsent(values[0], new ArrayList<>());
        map.get(values[0]).add(parsePoint(values[1]));
      }

      return map;
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid argument -> " + e.getMessage());
    }
  }

  private int parsePoint(String point) {
    int pinFall = isFoulScore(point) ? -1 : Integer.parseInt(point);

    if(pinFall < -1 || pinFall > 10) {
      throw new NumberFormatException("For input string: " + point);
    }

    return pinFall;
  }

  private boolean isFoulScore(String point) {
    return point.equals("F");
  }

}
