package nl.hanze.hive.mocks;

import java.util.HashMap;
import java.util.Stack;

import nl.hanze.hive.Board;
import nl.hanze.hive.Position;
import nl.hanze.hive.pieces.Piece;

public class BoardMock extends Board {
  public BoardMock() {
    super();
  }

  public void putPiece(Piece piece, Position position) {
    super.putPiece(piece, position);
  }

  public HashMap<Position, Stack<Piece>> getBoardMap() {
    return boardMap;
  }
}
