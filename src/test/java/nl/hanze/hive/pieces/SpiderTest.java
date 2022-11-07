package nl.hanze.hive.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.*;
import nl.hanze.hive.mocks.BoardMock;

public class SpiderTest {
  @Test()
  public void cantRepeatSteps() {
    Spider spider = new Spider(Hive.Player.BLACK);
    BoardMock board = new BoardMock();

    board.putPiece(new QueenBee(Hive.Player.WHITE), new Position(0, 0));
    board.putPiece(new Beetle(Hive.Player.WHITE), new Position(0, 1));
    board.putPiece(new Grasshopper(Hive.Player.BLACK), new Position(1, 1));
    board.putPiece(new SoldierAnt(Hive.Player.BLACK), new Position(2, 0));
    board.putPiece(new Beetle(Hive.Player.BLACK), new Position(2, -1));

    Position from = new Position(1, -1);
    Position to = new Position(1, 0);

    assertFalse(spider.isValidMove(board, from, to));
  }
}
