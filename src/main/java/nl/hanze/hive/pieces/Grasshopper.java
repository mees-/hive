package nl.hanze.hive.pieces;

import nl.hanze.hive.Board;
import nl.hanze.hive.Hive;
import nl.hanze.hive.Position;

public class Grasshopper extends Piece {

    public Grasshopper(Hive.Player player) {
        super(Hive.Tile.GRASSHOPPER, player);
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        return true;
    }
}
