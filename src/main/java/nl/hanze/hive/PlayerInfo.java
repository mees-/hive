package nl.hanze.hive;

import nl.hanze.hive.Hive.IllegalMove;
import nl.hanze.hive.Hive.Player;

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

  public void takeTurn() throws IllegalMove {
    movesTaken++;
    if (mustPlayQueen()) {
      throw new IllegalMove("You must play the queen bee");
    }
  }

  private boolean mustPlayQueen() {
    return movesTaken >= 3 && inventory.hasPiece(Hive.Tile.QUEEN_BEE);
  }
}
