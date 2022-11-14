package nl.hanze.hive.pieces;

import java.util.ArrayList;
import java.util.List;

import nl.hanze.hive.Board;
import nl.hanze.hive.Hive;
import nl.hanze.hive.Path;
import nl.hanze.hive.Position;

public class Spider extends Piece {

    public Spider(Hive.Player player) {
        super(Hive.Tile.SPIDER, player);
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        if (from.equals(to)) {
            return false;
        }
        List<Path> paths = getPossiblePaths(board, from, to);
        for (Path path : paths) {
            if (board.validatePath(path)) {
                return true;
            }
        }
        return false;
    }

    private List<Path> getPossiblePaths(Board board, Position from, Position to) {
        ArrayList<Path> paths = new ArrayList<Path>();
        if (!board.hasPiece(to)) {
            for (Position s1 : from.getNeighbours()) {
                if (!board.hasPiece(s1)) {
                    ArrayList<Position> steps = new ArrayList<Position>(List.of(from, s1));
                    for (Position s2 : s1.getNeighbours()) {
                        if (!board.hasPiece(s2) && !steps.contains(s2)) {
                            steps.add(s2);
                            for (Position s3 : s2.getNeighbours()) {
                                if (!board.hasPiece(s3) && s3.equals(to) && !steps.contains(s3)) {
                                    steps.add(s3);
                                    paths.add(new Path(steps));
                                    steps.remove(s3);
                                }
                            }
                            steps.remove(s2);
                        }
                    }
                }
            }
        }
        return paths;
    }

}
