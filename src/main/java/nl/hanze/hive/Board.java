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

  public Piece getPiece(Position position) {
    if (board.containsKey(position)) {
      return board.get(position).peek();
    } else {
      return null;
    }
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

  // TODO: implement first two turns rule
  public boolean canPlayPiece(Piece piece, Position position) {
    if (board.size() == 0) {
      return true;
    } else if (hasPiece(position)) {
      return false;
    } else {
      boolean neighbourFound = false;
      for (Position neighbour : position.getNeighbours()) {
        if (hasPiece(neighbour) && getPiece(neighbour).player != piece.player) {
          neighbourFound = true;
          return false;
        }
      }
      return neighbourFound;
    }
  }

  public boolean hasPiece(Position position) {
    return board.containsKey(position) && board.get(position).size() > 0;
  }

  public boolean canHoldPiece(Position position) {
    return !board.containsKey(position) || board.get(position).size() < 2;
  }

  public boolean isTopMostPiece(Piece piece, Position position) {
    return board.get(position).peek() == piece;
  }
}
