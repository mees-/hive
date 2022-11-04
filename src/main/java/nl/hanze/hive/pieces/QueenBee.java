package nl.hanze.hive.pieces;

import nl.hanze.hive.Board;
import nl.hanze.hive.Position;
import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class QueenBee extends NonStackingPiece {

  public QueenBee(Player player) {
    super(Tile.QUEEN_BEE, player);
  }

  @Override
  public boolean isValidMove(Board board, Position from, Position to) {
    return followsMoveRules(board, from, to) && from.distanceTo(to) == 1;
  }
}
