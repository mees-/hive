package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Path.Step;

public class PathTest {
  @Test()
  public void adjacentPositions() {
    ArrayList<Position> adjacentPositions = new ArrayList<Position>();
    adjacentPositions.add(new Position(0, -1));
    adjacentPositions.add(new Position(1, -1));
    adjacentPositions.add(new Position(1, 0));
    adjacentPositions.add(new Position(0, 1));
    adjacentPositions.add(new Position(-1, 1));

    new Path(adjacentPositions);
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

  @Test()
  public void matchingPathStartEnd() {
    ArrayList<Position> adjacentPositions = new ArrayList<Position>();
    adjacentPositions.add(new Position(0, -1));
    adjacentPositions.add(new Position(1, -1));
    adjacentPositions.add(new Position(1, 0));
    adjacentPositions.add(new Position(0, 1));
    adjacentPositions.add(new Position(-1, 1));

    Path path = new Path(adjacentPositions);

    assertEquals(path.from, new Position(0, -1));
    assertEquals(path.to, new Position(-1, 1));

    assertEquals(path.from, path.steps.get(0).from);
    assertEquals(path.to, path.steps.get(path.steps.size() - 1).to);
    assertEquals(path.steps.size(), 4);
  }

  @Test()
  public void matchingStepStartEnd() {
    ArrayList<Position> adjacentPositions = new ArrayList<Position>();
    adjacentPositions.add(new Position(0, -1));
    adjacentPositions.add(new Position(1, -1));
    adjacentPositions.add(new Position(1, 0));
    adjacentPositions.add(new Position(0, 1));
    adjacentPositions.add(new Position(-1, 1));

    Path path = new Path(adjacentPositions);
    Iterator<Step> iter = path.steps.iterator();
    Step previous = iter.next();
    while (iter.hasNext()) {
      Step current = iter.next();
      assertEquals(previous.to, current.from);
      previous = current;
    }
  }

}
