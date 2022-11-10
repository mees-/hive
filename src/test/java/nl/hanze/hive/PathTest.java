package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

    assertDoesNotThrow(() -> new Path(adjacentPositions));
  }

  @Test()
  public void nonAdjacentPositions() {
    ArrayList<Position> adjacentPositions = new ArrayList<Position>();
    adjacentPositions.add(new Position(0, -1));
    adjacentPositions.add(new Position(0, 1));

    assertThrows(IllegalArgumentException.class, () -> new Path(adjacentPositions));
  }

  @Test()
  public void matchingPathStart() {
    ArrayList<Position> adjacentPositions = new ArrayList<Position>();
    adjacentPositions.add(new Position(0, -1));
    adjacentPositions.add(new Position(1, -1));

    Path path = new Path(adjacentPositions);

    assertEquals(path.from, new Position(0, -1));
  }

  @Test()
  public void matchingPathEnd() {
    ArrayList<Position> adjacentPositions = new ArrayList<Position>();
    adjacentPositions.add(new Position(-1, 0));
    adjacentPositions.add(new Position(-1, 1));

    Path path = new Path(adjacentPositions);

    assertEquals(path.to, new Position(-1, 1));
  }

  @Test()
  public void pathDistance() {
    ArrayList<Position> adjacentPositions = new ArrayList<Position>();
    adjacentPositions.add(new Position(0, -1));
    adjacentPositions.add(new Position(1, -1));
    adjacentPositions.add(new Position(1, 0));
    adjacentPositions.add(new Position(0, 1));
    adjacentPositions.add(new Position(-1, 1));

    Path path = new Path(adjacentPositions);

    assertEquals(path.steps.size(), 4);
  }

}
