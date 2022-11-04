package nl.hanze.hive;

import java.util.HashMap;
import java.util.Stack;

import nl.hanze.hive.Hive.IllegalMove;
import nl.hanze.hive.pieces.Piece;

public class Board {
  private HashMap<Position, Stack<Piece>> board = new HashMap<Position, Stack<Piece>>();

  public Board() {
    // do nothing
  }

  public void putPiece(Piece piece, Position position) throws IllegalMove {
    if (board.containsKey(position)) {
      board.get(position).push(piece);
    } else {
      Stack<Piece> stack = new Stack<Piece>();
      stack.push(piece);
      board.put(position, stack);
    }
  }

  public boolean hasPiece(Position position) {
    return board.containsKey(position) && board.get(position).size() > 0;
  }

  public boolean canHoldPiece(Position position) {
    return !board.containsKey(position) || board.get(position).size() < 2;
  }
}
