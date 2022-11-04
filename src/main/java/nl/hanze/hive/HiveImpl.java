package nl.hanze.hive;

import nl.hanze.hive.pieces.*;

public class HiveImpl implements Hive {

  private Player currentPlayer = Player.WHITE;
  private Board board = new Board();

  private PlayerInventory whiteInventory = new PlayerInventory();
  private PlayerInventory blackInventory = new PlayerInventory();

  private PlayerInventory getCurrentInventory() {
    if (currentPlayer == Player.WHITE) {
      return whiteInventory;
    } else {
      return blackInventory;
    }
  }

  public HiveImpl() {
    // do nothing
  }

  @Override
  public void play(Tile tile, int q, int r) throws IllegalMove {
    getCurrentInventory().removePiece(tile);

    Piece piece;
    switch (tile) {
      case QUEEN_BEE:
        piece = new QueenBee(currentPlayer);
        break;
      case SPIDER:
        piece = new Spider(currentPlayer);
        break;
      case BEETLE:
        piece = new Beetle(currentPlayer);
        break;
      case GRASSHOPPER:
        piece = new Grasshopper(currentPlayer);
        break;
      case SOLDIER_ANT:
        piece = new SoldierAnt(currentPlayer);
        break;
      default:
        throw new IllegalArgumentException("Unknown tile type");
    }
    Position position = new Position(q, r);
    if (board.canPlayPiece(piece, position)) {
      board.putPiece(piece, position);
      switchPlayer();
    } else {
      throw new IllegalMove("Can't play piece here");
    }
  }

  @Override
  public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
    Position from = new Position(fromQ, fromR);
    Position to = new Position(toQ, toR);

  }

  @Override
  public void pass() throws IllegalMove {
    switchPlayer();
    return;

  }

  private void switchPlayer() {
    if (currentPlayer == Player.WHITE) {
      currentPlayer = Player.BLACK;
    } else {
      currentPlayer = Player.WHITE;
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
