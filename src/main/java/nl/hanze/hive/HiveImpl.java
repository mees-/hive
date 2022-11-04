package nl.hanze.hive;

import nl.hanze.hive.pieces.*;

public class HiveImpl implements Hive {

  private PlayerInfo whitePlayer = new PlayerInfo(Player.WHITE);
  private PlayerInfo blackPlayer = new PlayerInfo(Player.BLACK);
  private PlayerInfo currentPlayer = whitePlayer;

  private Board board = new Board();

  public HiveImpl() {
    // do nothing
  }

  @Override
  public void play(Tile tile, int q, int r) throws IllegalMove {
    Piece piece;
    switch (tile) {
      case QUEEN_BEE:
        piece = new QueenBee(currentPlayer.color);
        break;
      case SPIDER:
        piece = new Spider(currentPlayer.color);
        break;
      case BEETLE:
        piece = new Beetle(currentPlayer.color);
        break;
      case GRASSHOPPER:
        piece = new Grasshopper(currentPlayer.color);
        break;
      case SOLDIER_ANT:
        piece = new SoldierAnt(currentPlayer.color);
        break;
      default:
        throw new IllegalArgumentException("Unknown tile type");
    }
    Position position = new Position(q, r);

    boolean firstMoveException = currentPlayer.getMovesTaken() == 0;
    board.playPiece(piece, position, firstMoveException);
    currentPlayer.inventory.removePiece(tile);
    switchPlayer();
  }

  @Override
  public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
    Position from = new Position(fromQ, fromR);
    Position to = new Position(toQ, toR);
    board.movePiece(from, to);
  }

  @Override
  public void pass() throws IllegalMove {
    switchPlayer();
    return;

  }

  private void switchPlayer() throws IllegalMove {
    currentPlayer.takeTurn();
    if (currentPlayer == whitePlayer) {
      currentPlayer = blackPlayer;
    } else {
      currentPlayer = whitePlayer;
    }
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
