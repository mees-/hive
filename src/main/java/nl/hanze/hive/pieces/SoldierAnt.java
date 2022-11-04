package nl.hanze.hive.pieces;

import nl.hanze.hive.Board;
import nl.hanze.hive.Hive;
import nl.hanze.hive.Position;

public class SoldierAnt extends Piece  {

    protected SoldierAnt(Hive.Player player) {
        super(Hive.Tile.SOLDIER_ANT, player);
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        return false;
    }
}
