package nl.hanze.hive.pieces;

import java.util.List;

import nl.hanze.hive.Board;
import nl.hanze.hive.Path;
import nl.hanze.hive.Position;
import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class QueenBee extends Piece {

  public QueenBee(Player player) {
    super(Tile.QUEEN_BEE, player);
  }

  @Override
  public boolean isValidMove(Board board, Position from, Position to) {
    // this checks the distance as well since all positions in a path need to be
    // adjacent
    try {
      Path path = new Path(List.of(from, to));
      return board.validatePath(path) && cantStack(board, to);
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  @Override
  public boolean canMove(Board board, Position from) {
    return from.getNeighbours().stream().anyMatch(to -> isValidMove(board, from, to));
  }
}
