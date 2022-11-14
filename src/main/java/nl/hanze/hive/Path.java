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

  public static class Step {
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

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((from == null) ? 0 : from.hashCode());
      result = prime * result + ((to == null) ? 0 : to.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      Step other = (Step) obj;
      if (from == null) {
        if (other.from != null) {
          return false;
        }
      } else if (!from.equals(other.from)) {
        return false;
      }
      if (to == null) {
        if (other.to != null) {
          return false;
        }
      } else if (!to.equals(other.to)) {
        return false;
      }
      return true;
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((from == null) ? 0 : from.hashCode());
    result = prime * result + ((to == null) ? 0 : to.hashCode());
    result = prime * result + ((steps == null) ? 0 : steps.hashCode());
    result = prime * result + distance;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Path other = (Path) obj;
    if (from == null) {
      if (other.from != null)
        return false;
    } else if (!from.equals(other.from))
      return false;
    if (to == null) {
      if (other.to != null)
        return false;
    } else if (!to.equals(other.to))
      return false;
    if (distance != other.distance)
      return false;
    if (steps == null) {
      if (other.steps != null)
        return false;
    } else {
      if (steps.size() != other.steps.size())
        return false;
      for (int i = 0; i < steps.size(); i++) {
        if (!steps.get(i).equals(other.steps.get(i)))
          return false;
      }
    }
    return true;
  }
}
