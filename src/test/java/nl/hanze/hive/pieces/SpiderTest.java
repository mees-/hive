package nl.hanze.hive.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.xml.stream.events.StartDocument;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.hanze.hive.*;
import nl.hanze.hive.mocks.BoardMock;
import nl.hanze.hive.Hive.Player;

public class SpiderTest {

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

  // REQ: 10a
  @Test()
  public void mustMoveThreeSteps() {
    Piece piece = standardBoard.getPiece(new Position(2, -1));
    assertTrue(piece.isValidMove(standardBoard, new Position(2, -1), new Position(0, -1)));
  }

  @Test()
  public void cantMoveTwoSteps() {
    Piece piece = standardBoard.getPiece(new Position(2, -1));
    assertFalse(piece.isValidMove(standardBoard, new Position(2, -1), new Position(1, -2)));
  }

  // REQ: 10b
  @Test()
  public void mustMoveToDifferentSquare() {
    standardBoard.putPiece(new Spider(Player.BLACK), new Position(2, 1));
    standardBoard.putPiece(new Spider(Player.WHITE), new Position(3, 0));
    standardBoard.putPiece(new Beetle(Player.BLACK), new Position(3, -1));

    Piece piece = standardBoard.getPiece(new Position(2, -1));
    assertFalse(piece.isValidMove(standardBoard, new Position(2, -1), new Position(2, -1)));
  }

  // REQ: 10c
  @Test()
  public void cantStack() {
    Piece piece = standardBoard.getPiece(new Position(2, -1));
    assertFalse(piece.isValidMove(standardBoard, new Position(2, -1), new Position(0, 1)));
  }

  // REQ: 10d
  @Test()
  public void cantRepeatSteps() {
    Piece piece = standardBoard.getPiece(new Position(2, -1));
    assertFalse(piece.isValidMove(standardBoard, new Position(2, -1), new Position(2, -2)));
  }
}
