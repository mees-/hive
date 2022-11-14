package nl.hanze.hive;

import nl.hanze.hive.Hive.IllegalMove;
import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class PlayerInfo {
  public final PlayerInventory inventory = new PlayerInventory();
  private int piecesPlayed = 0;
  public final Player color;

  public PlayerInfo(Player color) {
    this.color = color;
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
    if (piecesPlayed == 3 && inventory.hasPiece(Tile.QUEEN_BEE) && tile != Tile.QUEEN_BEE) {
      throw new IllegalMove("You must play the queen bee");
    }
    if (tile != null) {
      inventory.removePiece(tile);
      piecesPlayed++;
    }
  }
}
