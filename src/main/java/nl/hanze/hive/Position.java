package nl.hanze.hive;

public class Position {
  public final int q;
  public final int r;

  public Position(int q, int r) {
    this.q = q;
    this.r = r;
  }

  public static int distance(Position a, Position b) {
    if (a.q == b.q) {
      return Math.abs(a.r - b.r);
    } else if (a.r == b.r) {
      return Math.abs(a.q - b.q);
    } else {
      int rDiff = b.r - a.r;
      Position aPrime = new Position(a.q - rDiff, a.r + rDiff);
      return Math.abs(rDiff) + distance(aPrime, b);
    }
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
