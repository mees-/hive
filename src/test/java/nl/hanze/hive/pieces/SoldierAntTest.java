package nl.hanze.hive.pieces;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.hanze.hive.Position;
import nl.hanze.hive.mocks.BoardMock;
import nl.hanze.hive.Hive.Player;

public class SoldierAntTest {
  private BoardMock standardBoard;

  @BeforeEach()
  public void setUp() {
    standardBoard = new BoardMock();

    standardBoard.putPiece(new QueenBee(Player.WHITE), new Position(0, 0));
    standardBoard.putPiece(new QueenBee(Player.BLACK), new Position(0, 1));
    standardBoard.putPiece(new Beetle(Player.WHITE), new Position(1, -1));
    standardBoard.putPiece(new Beetle(Player.BLACK), new Position(1, 1));
    standardBoard.putPiece(new Spider(Player.WHITE), new Position(2, -1));
  }

  // REQ: 9a
  @Test()
  public void validMove() {
    Piece piece = new SoldierAnt(Player.BLACK);
    standardBoard.putPiece(piece, new Position(3, -1));
    assertTrue(piece.isValidMove(standardBoard, new Position(3, -1), new Position(-1, 0)));
  }

  @Test()
  public void invalidMove() {
    Piece piece = new SoldierAnt(Player.BLACK);
    standardBoard.putPiece(piece, new Position(3, -1));
    assertFalse(piece.isValidMove(standardBoard, new Position(3, -1), new Position(1, 0)));
  }

  // REQ: 9b
  @Test()
  public void mustMoveToDifferentSquare() {
    Piece piece = new SoldierAnt(Player.BLACK);
    standardBoard.putPiece(piece, new Position(3, -1));
    assertFalse(piece.isValidMove(standardBoard, new Position(3, -1), new Position(3, -1)));
  }

  // REQ: 9c
  @Test()
  public void cantStack() {
    Piece piece = new SoldierAnt(Player.BLACK);
    standardBoard.putPiece(piece, new Position(3, -1));
    assertFalse(piece.isValidMove(standardBoard, new Position(3, -1), new Position(2, -1)));
  }
}
