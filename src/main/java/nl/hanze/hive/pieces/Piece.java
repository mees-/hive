package nl.hanze.hive.pieces;

import nl.hanze.hive.Board;
import nl.hanze.hive.Path;
import nl.hanze.hive.Position;
import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public abstract class Piece {
  public final Tile tile;
  public final Player player;

  protected Piece(Tile tile, Player player) {
    this.player = player;
    this.tile = tile;
  }

  public abstract boolean isValidMove(Board board, Position from, Position to);

  /**
   * Check if the piece can be moved in any way
   * 
   * @param board the board with complete state
   * @param from  the position of the piece
   * @return true if the piece can be moved, false otherwise
   */
  public abstract boolean canMove(Board board, Position from);

  protected boolean cantStack(Board board, Position to) {
    return !board.hasPiece(to);
  }

  protected boolean MoveDistance(Path path, int distance) {
    return path.distance == distance;
  }
}
