package nl.hanze.hive.pieces;

import nl.hanze.hive.Board;
import nl.hanze.hive.Position;
import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public abstract class Piece {
  public final Tile tile;
  public final Player player;

  public Piece(Tile tile, Player player) {
    this.player = player;
    this.tile = tile;
  }

  public abstract boolean isValidMove(Board board, Position from, Position to);
}
