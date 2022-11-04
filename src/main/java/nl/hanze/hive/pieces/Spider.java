package nl.hanze.hive.pieces;

import nl.hanze.hive.Board;
import nl.hanze.hive.Hive;
import nl.hanze.hive.Position;

public class Spider extends Piece {

    public Spider(Hive.Player player) {
        super(Hive.Tile.SPIDER, player);
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        return false;
    }
}
