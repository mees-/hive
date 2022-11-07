package nl.hanze.hive;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Path {
  public final Position from;
  public final Position to;
  public final List<Step> steps;
  public final int distance;

  public Path(List<Position> positions) throws IllegalArgumentException {
    this.from = positions.get(0);
    this.to = positions.get(positions.size() - 1);
    this.steps = new ArrayList<>();
    Iterator<Position> iterator = positions.iterator();
    Position previous = iterator.next();
    while (iterator.hasNext()) {
      Position current = iterator.next();
      steps.add(new Step(previous, current));
      previous = current;
    }
    this.distance = steps.size();
  }

  public class Step {
    public final Position from;
    public final Position to;

    public Step(Position from, Position to) throws IllegalArgumentException {
      if (from.distanceTo(to) != 1) {
        throw new IllegalArgumentException("Positions are not neighbours");
      }
      this.from = from;
      this.to = to;
    }

    public List<Position> getCommonNeighbours() {
      List<Position> commonNeighbours = new ArrayList<>();
      List<Position> fromNeighbours = from.getNeighbours();
      for (Position neighbour : to.getNeighbours()) {
        if (fromNeighbours.contains(neighbour)) {
          commonNeighbours.add(neighbour);
        }
      }
      return commonNeighbours;
    }
  }
}
