package nl.hanze.hive.pieces;

import java.util.List;

import nl.hanze.hive.Board;
import nl.hanze.hive.Hive;
import nl.hanze.hive.Path;
import nl.hanze.hive.Position;

public class Beetle extends Piece {

    public Beetle(Hive.Player player) {
        super(Hive.Tile.BEETLE, player);
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        try {
            Path path = new Path(List.of(from, to));
            return board.validatePath(path);
        } catch (IllegalArgumentException e) {
            return false;
        }

    }
}
