package nl.hanze.hive;

import java.util.ArrayList;
import java.util.List;

public class Position {
  public final int q;
  public final int r;

  public Position(int q, int r) {
    this.q = q;
    this.r = r;
  }

  public enum Direction {
    UPLEFT(0, -1),
    UPRIGHT(1, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    DOWNLEFT(-1, 1),
    DOWNRIGHT(0, 1);

    public final int q;
    public final int r;

    Direction(int q, int r) {
      this.q = q;
      this.r = r;
    }
  }

  public Position getNeighbour(Direction direction) {
    return getNeighbour(direction, 1);
  }

  public Position getNeighbour(Direction direction, int distance) {
    return new Position(q + distance * direction.q, r + distance * direction.r);
  }

  public List<Position> getNeighbours() {
    List<Position> neighbours = new ArrayList<>();
    for (Direction direction : Direction.values()) {
      neighbours.add(getNeighbour(direction));
    }
    return neighbours;
  }

  public static int distanceBetween(Position a, Position b) {
    if (a.q == b.q) {
      return Math.abs(a.r - b.r);
    } else if (a.r == b.r) {
      return Math.abs(a.q - b.q);
    } else {
      int rDiff = b.r - a.r;
      int qDiff = b.q - a.q;
      int chosenAxiDifference;
      Position aPrime;
      if (Math.abs(qDiff) < Math.abs(rDiff)) {
        chosenAxiDifference = qDiff;
        aPrime = new Position(a.q + chosenAxiDifference, a.r - chosenAxiDifference);
      } else {
        chosenAxiDifference = rDiff;
        aPrime = new Position(a.q - chosenAxiDifference, a.r + chosenAxiDifference);
      }
      return Math.abs(chosenAxiDifference) + distanceBetween(aPrime, b);
    }
  }

  public int distanceTo(Position other) {
    return distanceBetween(this, other);
  }

  public static boolean isStraightLine(Position a, Position b) {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Position position = (Position) o;

    if (q != position.q)
      return false;
    return r == position.r;
  }

  @Override
  public int hashCode() {
    int result = q;
    result = 31 * result + r;
    return result;
  }

  @Override
  public String toString() {
    return "Position{" +
        "q=" + q +
        ", r=" + r +
        '}';
  }
}
