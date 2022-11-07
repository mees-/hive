package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.IllegalMove;
import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.mocks.BoardMock;
import nl.hanze.hive.pieces.Beetle;
import nl.hanze.hive.pieces.Piece;
import nl.hanze.hive.pieces.QueenBee;
import nl.hanze.hive.pieces.Spider;

public class BoardTest {
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

  @Test()
  public void putPiece() {
    Board board = new Board();
    Piece piece = new QueenBee(Player.BLACK);
    Position position = new Position(0, 0);

    board.putPiece(piece, position);

    assertEquals(true, board.hasPiece(position));
  }

  @Test()
  public void takePiece() {
    Board board = new Board();
    Piece piece = new QueenBee(Player.BLACK);
    Position position = new Position(0, 0);

    board.putPiece(piece, position);

    assertEquals(piece, board.takePiece(position));
    assertEquals(false, board.hasPiece(position));
  }

  @Test()
  public void hasPiece() {
    Board board = new Board();
    Piece piece = new QueenBee(Player.BLACK);
    Position position = new Position(0, 0);

    board.putPiece(piece, position);

    assertTrue(board.hasPiece(position));
    assertFalse(board.hasPiece(new Position(1, 0)));
  }

  @Test()
  public void getPiece() {
    Board board = new Board();
    Piece piece = new QueenBee(Player.BLACK);
    Position position = new Position(0, 0);

    board.putPiece(piece, position);

    assertEquals(piece, board.getPiece(position));
  }

  @Test()
  public void moveToLockedSpot() {
    standardBoard.putPiece(new Spider(Player.WHITE), new Position(3, -2));
    assertThrows(IllegalMove.class, () -> standardBoard.movePiece(new Position(3, -2), new Position(1, 0)));

    standardBoard.putPiece(new Beetle(Player.BLACK), new Position(2, 0));
    assertThrows(IllegalMove.class, () -> standardBoard.movePiece(new Position(2, 0), new Position(1, 0)));

  }

  @Test()
  public void moveOntoStack() throws IllegalMove {
    standardBoard.putPiece(new Beetle(Player.BLACK), new Position(2, 0));
    standardBoard.movePiece(new Position(2, 0), new Position(2, -1));

  }

  @Test()
  public void moveFromStack() throws IllegalMove {
    standardBoard.putPiece(new Beetle(Player.BLACK), new Position(2, -1));
    standardBoard.movePiece(new Position(2, -1), new Position(2, 0));
  }

  @Test()
  public void moveToLockedSpotFromStack() throws IllegalMove {
    standardBoard.putPiece(new Beetle(Player.BLACK), new Position(2, -1));
    standardBoard.movePiece(new Position(2, -1), new Position(1, 0));
  }

  @Test()
  public void mustFollowEdge() {
    standardBoard.putPiece(new Beetle(Player.BLACK), new Position(2, 1));
    standardBoard.putPiece(new Beetle(Player.WHITE), new Position(3, -1));

    assertThrows(IllegalMove.class, () -> standardBoard.movePiece(new Position(3, -1), new Position(3, 0)));
  }
}
