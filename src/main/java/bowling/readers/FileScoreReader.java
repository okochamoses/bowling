package bowling.readers;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RequiredArgsConstructor
public class FileScoreReader implements ScoreReader<String> {

  @Override
  public String readScores(String filePath) {
    try {
      return Files.readString(Path.of(filePath));
    } catch (IOException e) {
      throw new IllegalArgumentException(String.format("Invalid file format: %s", e.getMessage()));
    }
  }
}
