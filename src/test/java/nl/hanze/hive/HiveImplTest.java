package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.IllegalMove;
import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class HiveImplTest {

  // REQ: 3c
  @Test()
  public void isWinnerWhiteIncompleteGame() throws IllegalMove {
    HiveImpl hive = new HiveImpl();
    hive.play(Tile.QUEEN_BEE, 0, 0);
    hive.play(Tile.QUEEN_BEE, 0, 1);

    assertFalse(hive.isWinner(Player.WHITE));
  }

  @Test()
  public void isWinnerBlackIncompleteGame() throws IllegalMove {
    HiveImpl hive = new HiveImpl();
    hive.play(Tile.QUEEN_BEE, 0, 0);
    hive.play(Tile.QUEEN_BEE, 0, 1);

    assertFalse(hive.isWinner(Player.BLACK));
  }

  @Test()
  public void isDrawIncompleteGame() throws IllegalMove {
    HiveImpl hive = new HiveImpl();
    hive.play(Tile.QUEEN_BEE, 0, 0);
    hive.play(Tile.QUEEN_BEE, 0, 1);

    assertFalse(hive.isDraw());
  }

  @Test()
  public void isWinnerWhiteCompleteGameWonByWhite() throws IllegalMove {

    HiveImpl hive = new HiveImpl();
    hive.play(Tile.QUEEN_BEE, 0, 0);
    hive.play(Tile.QUEEN_BEE, 0, 1);

    hive.play(Tile.BEETLE, 0, -1);
    hive.play(Tile.BEETLE, 1, 1);

    hive.play(Tile.BEETLE, 1, -1);
    hive.play(Tile.BEETLE, -1, 2);

    hive.play(Tile.SPIDER, -1, 0);
    hive.move(-1, 2, -1, 1);

    hive.play(Tile.SPIDER, 0, -2);
    hive.move(1, 1, 1, 0);

    assertFalse(hive.isWinner(Player.WHITE));
  }

  @Test()
  public void isWinnerBlackCompleteGameWonByWhite() throws IllegalMove {

    HiveImpl hive = new HiveImpl();
    hive.play(Tile.QUEEN_BEE, 0, 0);
    hive.play(Tile.QUEEN_BEE, 0, 1);

    hive.play(Tile.BEETLE, 0, -1);
    hive.play(Tile.BEETLE, 1, 1);

    hive.play(Tile.BEETLE, 1, -1);
    hive.play(Tile.BEETLE, -1, 2);

    hive.play(Tile.SPIDER, -1, 0);
    hive.move(-1, 2, -1, 1);

    hive.play(Tile.SPIDER, 0, -2);
    hive.move(1, 1, 1, 0);

    assertTrue(hive.isWinner(Player.BLACK));
  }

  @Test()
  public void isDrawCompleteGameWonByWhite() throws IllegalMove {

    HiveImpl hive = new HiveImpl();
    hive.play(Tile.QUEEN_BEE, 0, 0);
    hive.play(Tile.QUEEN_BEE, 0, 1);

    hive.play(Tile.BEETLE, 0, -1);
    hive.play(Tile.BEETLE, 1, 1);

    hive.play(Tile.BEETLE, 1, -1);
    hive.play(Tile.BEETLE, -1, 2);

    hive.play(Tile.SPIDER, -1, 0);
    hive.move(-1, 2, -1, 1);

    hive.play(Tile.SPIDER, 0, -2);
    hive.move(1, 1, 1, 0);

    assertFalse(hive.isDraw());
  }

  // REQ: 4e
  @Test()
  public void mustPlayQueenBeeBeforeTurnFour() throws IllegalMove {
    HiveImpl hive = new HiveImpl();

    hive.play(Tile.BEETLE, 0, 0);
    hive.play(Tile.BEETLE, 0, 1);

    hive.play(Tile.BEETLE, 1, -1);
    hive.play(Tile.BEETLE, 1, 1);

    hive.play(Tile.SPIDER, 2, -1);
    hive.play(Tile.SPIDER, 2, 1);

    assertThrows(IllegalMove.class, () -> hive.play(Tile.SPIDER, -1, 0));
  }

  // REQ: 5a
  @Test()
  public void canMoveOwnPiece() throws IllegalMove {
    HiveImpl hive = new HiveImpl();
    hive.play(Tile.QUEEN_BEE, 0, 0);
    hive.play(Tile.QUEEN_BEE, 0, 1);

    hive.play(Tile.BEETLE, 1, -1);
    hive.play(Tile.BEETLE, 1, 1);

    assertDoesNotThrow(() -> hive.move(1, -1, 0, -1));
  }

  @Test()
  public void cantMoveEnemyPiece() throws IllegalMove {
    HiveImpl hive = new HiveImpl();
    hive.play(Tile.QUEEN_BEE, 0, 0);
    hive.play(Tile.QUEEN_BEE, 0, 1);

    hive.play(Tile.BEETLE, 1, -1);
    hive.play(Tile.BEETLE, 1, 1);

    assertThrows(IllegalMove.class, () -> hive.move(1, 1, 0, 2));
  }

  // TODO: write test for REQ 3d

  /*
   * this test is aimed at manual testing. This test does not fit the requirements
   * for unit-tests
   */
  @Test()
  public void playAGame() throws IllegalMove {
    HiveImpl hive = new HiveImpl();
    hive.play(Tile.QUEEN_BEE, 0, 0);
    hive.play(Tile.QUEEN_BEE, 0, 1);

    assertFalse(hive.isDraw());
    assertFalse(hive.isWinner(Player.BLACK));
    assertFalse(hive.isWinner(Player.WHITE));

    hive.play(Tile.BEETLE, 0, -1);
    hive.play(Tile.BEETLE, 1, 1);

    assertFalse(hive.isDraw());
    assertFalse(hive.isWinner(Player.BLACK));
    assertFalse(hive.isWinner(Player.WHITE));

    hive.play(Tile.BEETLE, 1, -1);
    hive.play(Tile.BEETLE, -1, 2);

    assertFalse(hive.isDraw());
    assertFalse(hive.isWinner(Player.BLACK));
    assertFalse(hive.isWinner(Player.WHITE));

    hive.play(Tile.SPIDER, -1, 0);
    hive.move(-1, 2, -1, 1);

    assertFalse(hive.isDraw());
    assertFalse(hive.isWinner(Player.BLACK));
    assertFalse(hive.isWinner(Player.WHITE));

    hive.move(-1, 0, 1, -2);
    hive.move(1, 1, 0, 2);

  }
}
