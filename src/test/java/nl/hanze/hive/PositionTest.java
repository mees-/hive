package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PositionTest {
  @Test()
  public void distanceNeighbours() {
    Position a = new Position(1, 2);
    Position b = new Position(2, 1);

    assertEquals(1, Position.distanceBetween(a, b));
  }

  @Test()
  public void distanceStraightLine() {
    Position a = new Position(0, 0);
    Position b = new Position(0, -3);

    assertEquals(3, Position.distanceBetween(a, b));
  }

  @Test()
  public void distanceWithCornerToRight() {
    Position a = new Position(-1, 0);
    Position b = new Position(3, -2);

    assertEquals(4, Position.distanceBetween(a, b));
  }

  @Test()
  public void distanceWithCornerToLeft() {
    Position a = new Position(0, 0);
    Position b = new Position(1, -3);

    assertEquals(3, Position.distanceBetween(a, b));
  }

  @Test()
  public void validStraightLine() {
    Position a = new Position(0, 0);
    Position b = new Position(0, -3);

    assertTrue(Position.isStraightLine(a, b));
  }
}
