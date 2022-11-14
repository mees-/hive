package nl.hanze.hive.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.hanze.hive.mocks.BoardMock;
import nl.hanze.hive.Path;
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
    assertFalse(piece.isValidMove(standardBoard, new Position(3, -1), new Position(1, 1)));
  }

  // REQ: 11b
  @Test()
  public void invalidMoveToCurrentPosition() {
    Piece piece = standardBoard.getPiece(new Position(3, -1));
    assertFalse(piece.isValidMove(standardBoard, new Position(3, -1), new Position(3, -1)));
  }

  // REQ: 11c
  @Test()
  public void mustPassOverPiece() {
    Piece piece = standardBoard.takePiece(new Position(3, -1));
    standardBoard.putPiece(piece, new Position(3, -2));
    assertFalse(piece.isValidMove(standardBoard, new Position(3, -2), new Position(1, -2)));
  }

  @Test()
  public void validPathFromStraightLineOnQAxisPositive() {
    Position from = new Position(0, 0);
    Position to = new Position(0, 2);
    Path path = Grasshopper.pathFromStraightLine(from, to);
    assertEquals(new Path(List.of(new Position(0, 0), new Position(0, 1), new Position(0, 2))), path);
  }

  @Test()
  public void validPathFromStraightLineOnRAxisPositive() {
    Position from = new Position(0, 0);
    Position to = new Position(2, 0);
    Path path = Grasshopper.pathFromStraightLine(from, to);
    assertEquals(new Path(List.of(new Position(0, 0), new Position(1, 0), new Position(2, 0))), path);
  }

  @Test()
  public void validPathFromStraightLineOffAxisPositive() {
    Position from = new Position(0, 0);
    Position to = new Position(2, -2);
    Path path = Grasshopper.pathFromStraightLine(from, to);
    assertEquals(new Path(List.of(new Position(0, 0), new Position(1, -1), new Position(2, -2))), path);
  }

  @Test()
  public void validPathFromStraightLineOnQAxisNegative() {
    Position from = new Position(0, 0);
    Position to = new Position(0, -2);
    Path path = Grasshopper.pathFromStraightLine(from, to);
    assertEquals(new Path(List.of(new Position(0, 0), new Position(0, -1), new Position(0, -2))), path);
  }

  @Test()
  public void validPathFromStraightLineOnRAxisNegative() {
    Position from = new Position(0, 0);
    Position to = new Position(-2, 0);
    Path path = Grasshopper.pathFromStraightLine(from, to);
    assertEquals(new Path(List.of(new Position(0, 0), new Position(-1, 0), new Position(-2, 0))), path);
  }

  @Test()
  public void validPathFromStraightLineOffAxisNegative() {
    Position from = new Position(0, 0);
    Position to = new Position(-2, 2);
    Path path = Grasshopper.pathFromStraightLine(from, to);
    assertEquals(new Path(List.of(new Position(0, 0), new Position(-1, 1), new Position(-2, 2))), path);
  }
}
