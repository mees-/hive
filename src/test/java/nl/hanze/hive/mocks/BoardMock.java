package nl.hanze.hive.mocks;

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
}
