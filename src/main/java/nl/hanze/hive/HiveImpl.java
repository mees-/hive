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
    if (currentPlayer.inventory.hasPiece(Tile.QUEEN_BEE)) {
      throw new IllegalMove("You can't move pieces before you play the queen bee");
    }
    Position from = new Position(fromQ, fromR);
    Position to = new Position(toQ, toR);

    Piece piece = board.getPiece(from);
    if (piece == null) {
      throw new IllegalMove("No piece at position " + from);
    } else if (piece.player != currentPlayer.color) {
      throw new IllegalMove("Piece at position " + from + " is not yours");
    } else {
      board.movePiece(from, to);
      switchPlayer();
    }
  }

  @Override
  public void pass() throws IllegalMove {
    switchPlayer();
    return;

  }

  private void switchPlayer() throws IllegalMove {
    currentPlayer.takeTurn();
    if (currentPlayer.equals(whitePlayer)) {
      currentPlayer = blackPlayer;
    } else {
      currentPlayer = whitePlayer;
    }
  }

  @Override
  public boolean isWinner(Player player) {
    Player otherPlayer = player == Player.WHITE ? Player.BLACK : Player.WHITE;
    Position queenBeePosition = board.findQueenBee(otherPlayer);
    for (Position neighbour : queenBeePosition.getNeighbours()) {
      if (!board.hasPiece(neighbour)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isDraw() {
    return isWinner(Player.WHITE) && isWinner(Player.BLACK);
  }

}
