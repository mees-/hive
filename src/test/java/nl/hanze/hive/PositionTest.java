package nl.hanze.hive;

import org.junit.jupiter.api.Test;

public class PositionTest {
  @Test()
  public void distance() {
    Position a = new Position(0, 0);
    Position b = new Position(0, -3);

    assert Position.distance(a, b) == 3;

    Position c = new Position(0, -1);
    Position d = new Position(3, -2);

    assert Position.distance(c, d) == 3;

    Position e = new Position(-3, 2);
    Position f = new Position(3, -3);

    assert Position.distance(e, f) == 6;
  }
}
