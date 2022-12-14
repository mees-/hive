package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import nl.hanze.hive.Hive.IllegalMove;
import nl.hanze.hive.Path.Step;
import nl.hanze.hive.pieces.Piece;

public class Board {
  protected HashMap<Position, Stack<Piece>> boardMap = new HashMap<Position, Stack<Piece>>();

  private boolean whiteHasPlayed = false;
  private boolean blackHasPlayed = false;

  public Board() {
    // do nothing
  }

  /**
   * Get the top piece at a position but don't remove it from the board
   * 
   * @param position
   * @return The piece at the position or null if there is no piece
   */
  public Piece getPiece(Position position) {
    if (boardMap.containsKey(position)) {
      return boardMap.get(position).peek();
    } else {
      return null;
    }
  }

  /**
   * Take a piece and remove it from the board.
   * 
   * @param position
   * @return The taken piece
   */
  public Piece takePiece(Position position) {
    if (boardMap.containsKey(position)) {
      Piece piece = boardMap.get(position).pop();
      if (boardMap.get(position).isEmpty()) {
        boardMap.remove(position);
      }
      return piece;
    } else {
      return null;
    }
  }

  protected void putPiece(Piece piece, Position position) {
    if (piece.player == Hive.Player.WHITE) {
      whiteHasPlayed = true;
    } else {
      blackHasPlayed = true;
    }
    if (boardMap.containsKey(position)) {
      boardMap.get(position).push(piece);
    } else {
      Stack<Piece> stack = new Stack<Piece>();
      stack.push(piece);
      boardMap.put(position, stack);
    }
  }

  public Position findQueenBee(Hive.Player player) {
    for (Position position : boardMap.keySet()) {
      for (Piece piece : boardMap.get(position)) {
        if (piece.player == player && piece.tile == Hive.Tile.QUEEN_BEE) {
          return position;
        }
      }
    }
    return null;
  }

  public boolean hasNeighbours(Position position, Position ignorePosition) {
    for (Position neighbour : position.getNeighbours()) {
      if (!neighbour.equals(ignorePosition) && hasPiece(neighbour)) {
        return true;
      }
    }
    return false;
  }

  public void playPiece(Piece piece, Position position) throws IllegalMove {
    if (canPlayPiece(piece, position)) {
      putPiece(piece, position);
      if (piece.player == Hive.Player.WHITE) {
        whiteHasPlayed = true;
      } else {
        blackHasPlayed = true;
      }
    } else {
      throw new IllegalMove();
    }
  }

  public boolean canPlayPiece(Piece piece, Position position) {
    if (boardMap.size() == 0) {
      return true;
    } else if (hasPiece(position)) {
      return false;
    } else {
      boolean neighbourFound = false;
      for (Position neighbour : position.getNeighbours()) {
        if (hasPiece(neighbour)) {
          neighbourFound = true;
          if ((whiteHasPlayed && blackHasPlayed) && getPiece(neighbour).player != piece.player) {
            return false;
          }
        }
      }
      return neighbourFound;
    }
  }

  public void movePiece(Position from, Position to) throws IllegalMove {
    Piece piece = getPiece(from);
    if (piece == null) {
      throw new IllegalMove("There is no piece at the given position");
    }
    if (piece.isValidMove(this, from, to)) {
      piece = takePiece(from);
      putPiece(piece, to);
    } else {
      putPiece(piece, from);
      throw new IllegalMove("The piece can't move to the given position");
    }
  }

  public boolean isSingleHive() {
    if (boardMap.size() == 0) {
      return true;
    } else {
      Position firstPosition = boardMap.keySet().iterator().next();
      List<Position> connected = getAllConnectedPositions(firstPosition);
      return connected.size() == boardMap.size();
    }
  }

  private List<Position> getAllConnectedPositions(Position position) {
    List<Position> connectedPositions = new ArrayList<Position>(List.of(position));
    return getAllConnectedPositions(position, connectedPositions);
  }

  private List<Position> getAllConnectedPositions(Position position, List<Position> connectedPositions) {
    for (Position neighbour : position.getNeighbours()) {
      if (hasPiece(neighbour) && !connectedPositions.contains(neighbour)) {
        connectedPositions.add(neighbour);
        getAllConnectedPositions(neighbour, connectedPositions);
      }
    }
    return connectedPositions;
  }

  public boolean canStep(Step step) {
    List<Position> commonNeighbours = step.getCommonNeighbours();
    int hn1 = pieceCount(commonNeighbours.get(0));
    int hn2 = pieceCount(commonNeighbours.get(1));

    int ha = pieceCount(step.from);
    int hb = pieceCount(step.to);
    if (hn1 == 0 && hn2 == 0 && hb == 0 && ha <= 1) { /*
                                                       * the hb and ha parts are for the beetle which can move onto
                                                       * other pieces.
                                                       */

      return false; // REQ 6c
    }

    if (Math.min(hn1, hn2) > Math.max(ha - 1, hb)) {
      return false; // REQ 6b
    }
    return true;
  }

  public boolean validatePath(Path path) throws IllegalArgumentException {
    if (!hasPiece(path.from)) {
      throw new IllegalArgumentException("There is no piece at the given starting position");
    }
    for (Step step : path.steps) {
      if (!canStep(step)) {
        Piece piece = takePiece(step.from);
        putPiece(piece, path.from);
        return false;
      }
      Piece piece = takePiece(step.from);

      if (!isSingleHive()) { // check that with the piece removed, the hive is still connected
        putPiece(piece, path.from);
        return false;
      }
      putPiece(piece, step.to);
    }
    // Return piece to original position since this method can be used to test
    // paths even if they are not executed
    Piece piece = takePiece(path.to);
    putPiece(piece, path.from);
    return true;
  }

  public boolean hasPiece(Position position) {
    return pieceCount(position) > 0;
  }

  public int pieceCount(Position position) {
    return boardMap.containsKey(position) ? boardMap.get(position).size() : 0;
  }

  public Set<Position> getAllNonEmptyPositions() {
    return boardMap.keySet();
  }
}
