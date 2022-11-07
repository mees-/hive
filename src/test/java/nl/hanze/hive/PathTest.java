package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class PathTest {
  @Test()
  public void adjacentPositions() {
    ArrayList<Position> adjacentPositions = new ArrayList<Position>();
    adjacentPositions.add(new Position(0, -1));
    adjacentPositions.add(new Position(1, -1));
    adjacentPositions.add(new Position(1, 0));
    adjacentPositions.add(new Position(0, 1));
    adjacentPositions.add(new Position(-1, 1));

    Path path = new Path(adjacentPositions);

    assertEquals(path.from, new Position(0, -1));
    assertEquals(path.to, new Position(-1, 1));
    assertEquals(path.steps.size(), 4);
  }

  @Test()
  public void nonAdjacentPositions() {
    ArrayList<Position> adjacentPositions = new ArrayList<Position>();
    adjacentPositions.add(new Position(0, -1));
    adjacentPositions.add(new Position(1, -1));
    adjacentPositions.add(new Position(1, 0));
    adjacentPositions.add(new Position(0, 1));
    adjacentPositions.add(new Position(-1, 3));

    assertThrows(IllegalArgumentException.class, () -> {
      new Path(adjacentPositions);
    });
  }
}
