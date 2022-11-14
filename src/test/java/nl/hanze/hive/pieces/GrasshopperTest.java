package nl.hanze.hive.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.hanze.hive.mocks.BoardMock;
import nl.hanze.hive.Position;
import nl.hanze.hive.Hive.Player;

public class GrasshopperTest {

  private BoardMock standardBoard;

  @BeforeEach()
  public void setUp() {
    standardBoard = new BoardMock();

    standardBoard.putPiece(new QueenBee(Player.WHITE), new Position(0, 0));
    standardBoard.putPiece(new QueenBee(Player.BLACK), new Position(0, 1));
    standardBoard.putPiece(new Beetle(Player.WHITE), new Position(1, -1));
    standardBoard.putPiece(new Beetle(Player.BLACK), new Position(1, 1));
    standardBoard.putPiece(new Spider(Player.WHITE), new Position(2, -1));
    standardBoard.putPiece(new Grasshopper(Player.BLACK), new Position(3, -1));
  }

  // REQ: 11a
  @Test()
  public void validMove() {
    Piece piece = standardBoard.getPiece(new Position(3, -1));
    assertTrue(piece.isValidMove(standardBoard, new Position(3, -1), new Position(0, -1)));
  }

  @Test()
  public void invalidMoveNonStraightLine() {
    Piece piece = standardBoard.getPiece(new Position(3, -1));
    assertFalse(piece.isValidMove(standardBoard, new Position(3, -1), new Position(0, 0)));
  }

  @Test()
  public void validMovePastPiece() {
    Piece piece = standardBoard.getPiece(new Position(3, -1));
    assertTrue(piece.isValidMove(standardBoard, new Position(3, -1), new Position(1, -1)));
  }

  @Test()
  public void invalidMoveNotPastPiece() {
    Piece piece = standardBoard.getPiece(new Position(3, -1));
    assertFalse(piece.isValidMove(standardBoard, new Position(3, -1), new Position(2, 0)));
  }

}
