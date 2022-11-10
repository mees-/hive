package nl.hanze.hive.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.hanze.hive.Position;
import nl.hanze.hive.mocks.BoardMock;
import nl.hanze.hive.Hive.Player;

public class QueenBeeTest {
  private BoardMock standadBoard;
  private QueenBee queenBee;

  @BeforeEach()
  public void setUp() {
    standadBoard = new BoardMock();
    queenBee = new QueenBee(Player.WHITE);
    standadBoard.putPiece(queenBee, new Position(0, 0));
    standadBoard.putPiece(new QueenBee(Player.BLACK), new Position(0, 1));
    standadBoard.putPiece(new Beetle(Player.WHITE), new Position(-1, 1));
    standadBoard.putPiece(new Beetle(Player.BLACK), new Position(-1, 2));
  }

  // REQ: 8a
  @Test()
  public void allowSingleStepMove() {
    assertTrue(queenBee.isValidMove(standadBoard, new Position(0, 0), new Position(-1, 0)));
  }

  // REQ: 8a
  @Test()
  public void disallowMultiStepMove() {
    assertFalse(queenBee.isValidMove(standadBoard, new Position(0, 0), new Position(-2, 1)));
  }

  // REQ: 8b
  @Test()
  public void disallowStacking() {
    assertFalse(queenBee.isValidMove(standadBoard, new Position(0, 0), new Position(0, 1)));
  }
}
