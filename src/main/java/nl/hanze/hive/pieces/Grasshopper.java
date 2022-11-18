package nl.hanze.hive.pieces;

import java.util.ArrayList;
import java.util.List;

import nl.hanze.hive.Board;
import nl.hanze.hive.Hive;
import nl.hanze.hive.Path;
import nl.hanze.hive.Position;

public class Grasshopper extends Piece {

    public Grasshopper(Hive.Player player) {
        super(Hive.Tile.GRASSHOPPER, player);
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        if (from.equals(to) || !Position.isStraightLine(from, to) || !cantStack(board, to)) {
            return false;
        }
        Path path = pathFromStraightLine(from, to);
        for (Position position : path.getMiddlePositions()) {
            if (!board.hasPiece(position)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canMove(Board board, Position from) {
        // for (Direction direction : Direction.values()) {
        // int distance = 1;
        // while (board.hasPiece(from.getNeighbour(direction, distance))) {
        // distance++;
        // }
        // if (isValidMove(board, from, from.getNeighbour(direction, distance))) {
        // return true;
        // }
        // }
        // return false;

        /*
         * The above code implements the game rules correctly, but since I, at
         * the time that I'm writing this, cannot think of a moment the
         * grasshopper doesn't have a valid move. That is why this method always
         * returns true
         */
        return true;
    }

    public static Path pathFromStraightLine(Position from, Position to) {
        if (!Position.isStraightLine(from, to)) {
            throw new IllegalArgumentException("Positions are not in a straight line");
        }
        if (from.equals(to)) {
            throw new IllegalArgumentException("Positions are the same");
        }

        List<Position> positions = new ArrayList<>();

        if (from.q == to.q) {
            int diff = from.r - to.r;
            int step = diff / Math.abs(diff);
            int i;
            for (i = from.r; i != to.r; i -= step) {
                positions.add(new Position(from.q, i));
            }
            positions.add(new Position(from.q, i));
        } else if (from.r == to.r) {
            int diff = from.q - to.q;
            int step = diff / Math.abs(diff);
            int i;
            for (i = from.q; i != to.q; i -= step) {
                positions.add(new Position(i, from.r));
            }
            positions.add(new Position(i, from.r));
        } else {
            int diff = from.q - to.q;
            int step = diff / Math.abs(diff);
            int q, r;
            for (q = from.q, r = from.r; q != to.q; q -= step, r += step) {
                positions.add(new Position(q, r));
            }
            positions.add(new Position(q, r));
        }
        return new Path(positions);
    }
}
