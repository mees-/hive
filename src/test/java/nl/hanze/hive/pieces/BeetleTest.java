package nl.hanze.hive.pieces;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.hanze.hive.Position;
import nl.hanze.hive.mocks.BoardMock;
import nl.hanze.hive.Hive.Player;

public class BeetleTest {

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
}
