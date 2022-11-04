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

  public boolean hasNeighbours(Position position) {
    return position.getNeighbours().stream().anyMatch(p -> this.hasPiece(position));
  }

  public void playPiece(Piece piece, Position position, boolean firstMoveException) throws IllegalMove {
    if (board.size() == 0) {
      putPiece(piece, position);
    } else if (hasPiece(position)) {
      throw new IllegalMove("You can't play next to an enemy piece");
    } else {
      boolean neighbourFound = false;
      for (Position neighbour : position.getNeighbours()) {
        if (hasPiece(neighbour)) {
          neighbourFound = true;
          if (!firstMoveException && getPiece(neighbour).player != piece.player) {
            throw new IllegalMove("You can't play next to an enemy piece");
          }
        }
      }
      if (neighbourFound) {
        putPiece(piece, position);
      } else {
        throw new IllegalMove("You can't play a piece that is not next to another piece");
      }
    }
  }

  public void movePiece(Position from, Position to) throws IllegalMove {
    Piece piece = getPiece(from);
    if (piece == null) {
      throw new IllegalMove("There is no piece at the given position");
    }
    if (piece.isValidMove(this, from, to)) {
      board.get(from).pop();
      if (board.get(from).size() == 0) {
        board.remove(from);
      }
      putPiece(piece, to);
    } else {
      throw new IllegalMove("The piece can't move to the given position");
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
