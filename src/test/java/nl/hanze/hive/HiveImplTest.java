package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.IllegalMove;
import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class HiveImplTest {
  @Test()
  public void isWinner() throws IllegalMove {
    HiveImpl hive = new HiveImpl();
    hive.play(Tile.QUEEN_BEE, 0, 0);
    hive.play(Tile.QUEEN_BEE, 0, 1);

    assertFalse(hive.isDraw());
    assertFalse(hive.isWinner(Player.BLACK));
    assertFalse(hive.isWinner(Player.WHITE));

    hive.play(Tile.BEETLE, 0, -1);
    hive.play(Tile.BEETLE, 0, 2);

    assertFalse(hive.isDraw());
    assertFalse(hive.isWinner(Player.BLACK));
    assertFalse(hive.isWinner(Player.WHITE));

    hive.play(Tile.BEETLE, 1, -1);
    hive.play(Tile.BEETLE, -1, 2);

    assertFalse(hive.isDraw());
    assertFalse(hive.isWinner(Player.BLACK));
    assertFalse(hive.isWinner(Player.WHITE));

    hive.move(1, -1, 1, 0);
    hive.move(-1, 2, -1, 1);

    // TODO: implement this test further
    fail("Not yet implemented");
  }
}
