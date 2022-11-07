package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.pieces.Piece;
import nl.hanze.hive.pieces.QueenBee;

public class BoardTest {
  @Test()
  public void putPiece() {
    Board board = new Board();
    Piece piece = new QueenBee(Player.BLACK);
    Position position = new Position(0, 0);
    try {
      board.putPiece(piece, position);
    } catch (Hive.IllegalMove e) {
      fail("Board.putPiece() threw an exception");
    }
    assertEquals(true, board.hasPiece(position));
  }

  @Test()
  public void takePiece() {
    Board board = new Board();
    Piece piece = new QueenBee(Player.BLACK);
    Position position = new Position(0, 0);
    try {
      board.putPiece(piece, position);
    } catch (Hive.IllegalMove e) {
      fail("Board.putPiece() threw an exception");
    }
    assertEquals(piece, board.takePiece(position));
    assertEquals(false, board.hasPiece(position));
  }

  @Test()
  public void hasPiece() {
    Board board = new Board();
    Piece piece = new QueenBee(Player.BLACK);
    Position position = new Position(0, 0);
    try {
      board.putPiece(piece, position);
    } catch (Hive.IllegalMove e) {
      fail("Board.putPiece() threw an exception");
    }
    assertTrue(board.hasPiece(position));
    assertFalse(board.hasPiece(new Position(1, 0)));
  }
}
