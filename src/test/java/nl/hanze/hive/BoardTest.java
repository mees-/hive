package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.IllegalMove;
import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;
import nl.hanze.hive.mocks.BoardMock;
import nl.hanze.hive.pieces.Beetle;
import nl.hanze.hive.pieces.Grasshopper;
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
  public void takePieceReturnsPiece() {
    Board board = new Board();
    Piece piece = new QueenBee(Player.BLACK);
    Position position = new Position(0, 0);

    board.putPiece(piece, position);

    assertEquals(piece, board.takePiece(position));
  }

  @Test()
  public void takePieceRemovesPiece() {
    Board board = new Board();
    Piece piece = new QueenBee(Player.BLACK);
    Position position = new Position(0, 0);

    board.putPiece(piece, position);

    assertEquals(false, board.hasPiece(position));
  }

  @Test()
  public void hasPiece() {
    Board board = new Board();
    Piece piece = new QueenBee(Player.BLACK);
    Position position = new Position(0, 0);

    board.putPiece(piece, position);

    assertTrue(board.hasPiece(position));
  }

  @Test()
  public void getPieceReturnsPiece() {
    Board board = new Board();
    Piece piece = new QueenBee(Player.BLACK);
    Position position = new Position(0, 0);

    board.putPiece(piece, position);

    assertEquals(piece, board.getPiece(position));
  }

  // REQ: 6b
  @Test()
  public void moveToLockedSpot() {
    standardBoard.putPiece(new Beetle(Player.BLACK), new Position(2, 0));
    assertThrows(IllegalMove.class, () -> standardBoard.movePiece(new Position(2, 0), new Position(1, 0)));

  }

  // REQ:7a
  @Test()
  public void moveOntoStack() {
    standardBoard.putPiece(new Beetle(Player.BLACK), new Position(2, 0));
    assertDoesNotThrow(() -> standardBoard.movePiece(new Position(2, 0), new Position(2, -1)));

  }

  // REQ:7a
  @Test()
  public void moveFromStack() {
    standardBoard.putPiece(new Beetle(Player.BLACK), new Position(2, -1));
    assertDoesNotThrow(() -> standardBoard.movePiece(new Position(2, -1), new Position(2, 0)));
  }

  // REQ:7a
  @Test()
  public void moveToLockedSpotFromStack() {
    standardBoard.putPiece(new Beetle(Player.BLACK), new Position(2, -1));
    assertDoesNotThrow(() -> standardBoard.movePiece(new Position(2, -1), new Position(1, 0)));
  }

  // REQ: 6c
  @Test()
  public void mustFollowEdge() {
    standardBoard.putPiece(new Beetle(Player.BLACK), new Position(2, 1));
    standardBoard.putPiece(new Beetle(Player.WHITE), new Position(3, -1));

    assertThrows(IllegalMove.class, () -> standardBoard.movePiece(new Position(3, -1), new Position(3, 0)));
  }

  // REQ: 6c
  @Test()
  public void cantMoveAwayFromHive() {
    assertThrows(IllegalMove.class, () -> standardBoard.movePiece(new Position(2, -1), new Position(3, -1)));
  }

  // REQ: 2c
  @Test()
  public void startWithEmtpyBoard() {
    BoardMock board = new BoardMock();
    assertEquals(0, board.getBoardMap().size());
  }

  // REQ: 4b
  @Test()
  public void canPlayOnEmptySquare() {
    assertDoesNotThrow(() -> standardBoard.playPiece(new Grasshopper(Player.WHITE), new Position(3, -2), false));
  }

  @Test()
  public void cantPlayOnOccupiedSquare() {
    assertThrows(IllegalMove.class,
        () -> standardBoard.playPiece(new Grasshopper(Player.WHITE), new Position(0, 0), true));
  }
}
