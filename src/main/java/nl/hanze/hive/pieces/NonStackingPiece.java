package nl.hanze.hive.pieces;

import nl.hanze.hive.Board;
import nl.hanze.hive.Position;
import nl.hanze.hive.Hive.Tile;
import nl.hanze.hive.Hive.Player;

public abstract class NonStackingPiece extends Piece {
  protected NonStackingPiece(Tile tile, Player player) {
    super(tile, player);
  }

  protected boolean followsMoveRules(Board board, Position from, Position to) {
    return super.followsMoveRules(board, from, to) && !board.hasPiece(to);
  }
}
