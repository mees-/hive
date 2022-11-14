package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import nl.hanze.hive.Hive.IllegalMove;
import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class PlayerInfoTest {
  // REQ: 4a
  @Test()
  public void canPlayUnspentPiece() {
    PlayerInfo playerInfo = new PlayerInfo(Player.WHITE);
    assertDoesNotThrow(() -> playerInfo.takeTurn(Tile.QUEEN_BEE));
  }

  // REQ: 4a
  @Test()
  public void cantPlaySpentPiece() throws IllegalMove {
    PlayerInfo playerInfo = new PlayerInfo(Player.WHITE);
    playerInfo.takeTurn(Tile.QUEEN_BEE);
    assertThrows(IllegalMove.class, () -> playerInfo.takeTurn(Tile.QUEEN_BEE));
  }
}
