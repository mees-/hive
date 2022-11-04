package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PositionTest {
  @Test()
  public void distance() {
    Position a = new Position(0, 0);
    Position b = new Position(0, -3);

    assertEquals(3, Position.distanceBetween(a, b));

    Position c = new Position(0, -1);
    Position d = new Position(3, -2);

    assertEquals(3, Position.distanceBetween(c, d));

    Position e = new Position(-3, 2);
    Position f = new Position(3, -3);

    assertEquals(6, Position.distanceBetween(e, f));
  }
}
