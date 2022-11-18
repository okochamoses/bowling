package bowling.readers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class LineParserTest {
  private final LineParser lineParser = new LineParser("\t");

  @Test
  void shouldParseValidLineSuccessfully() {
    String givenFileString = "John\t10";

    Map<String, List<Integer>> scores = lineParser.parse(givenFileString);

    assertEquals(1, scores.get("John").size());
    assertTrue(scores.containsKey("John"));
    assertEquals(10, scores.get("John").get(0));
  }

  @Test
  void shouldReadFoulAsMinusOne() {
    String givenFileString = "John\tF";

    Map<String, List<Integer>> scores = lineParser.parse(givenFileString);

    assertEquals(-1, scores.get("John").get(0));
  }

  @ParameterizedTest
  @CsvSource(value = {"John", "John10", "John\tMoses", "''"})
  void shouldThrowExceptionForInvalidInput(String givenFileString) {
    assertThrows(IllegalArgumentException.class, () -> lineParser.parse(givenFileString));
  }

}