package bowling.score;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PlayerScore {
  private final Player player;
  private final List<Frame> frames;

  public PlayerScore(String playerName) {
    this.player = new Player(playerName);
    this.frames = new ArrayList<>();
  }

}
