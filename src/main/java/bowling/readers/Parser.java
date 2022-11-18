package bowling.readers;


import java.util.List;
import java.util.Map;

public interface Parser {
  Map<String, List<Integer>> parse(String key);

}
