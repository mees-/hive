package nl.hanze.hive;

public class HiveImpl implements Hive {

  public HiveImpl() {
    // do nothing
  }

  @Override
  public void play(Tile tile, int q, int r) throws IllegalMove {
    // TODO Auto-generated method stub

  }

  @Override
  public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
    // TODO Auto-generated method stub

  }

  @Override
  public void pass() throws IllegalMove {
    return;

  }

  @Override
  public boolean isWinner(Player player) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isDraw() {
    // TODO Auto-generated method stub
    return false;
  }

}
