package nl.hanze.hive;

import nl.hanze.hive.Hive.IllegalMove;
import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class PlayerInfo {
  public final PlayerInventory inventory = new PlayerInventory();
  private int movesTaken = 0;
  public final Player color;

  public PlayerInfo(Player color) {
    this.color = color;
  }

  public int getMovesTaken() {
    return movesTaken;
  }

  /**
   * Take a turn and keep the inventory in sync.
   * 
   * Also check that the player plays the queen in time.
   * 
   * @param tile
   * @throws IllegalMove
   */
  public void takeTurn(Tile tile) throws IllegalMove {
    if (tile != null) {
      inventory.removePiece(tile);
    }
    movesTaken++;
    if (mustPlayQueen()) {
      throw new IllegalMove("You must play the queen bee");
    }
  }

  private boolean mustPlayQueen() {
    return movesTaken >= 3 && inventory.hasPiece(Hive.Tile.QUEEN_BEE);
  }
}
