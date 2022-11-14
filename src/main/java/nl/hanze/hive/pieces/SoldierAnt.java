package nl.hanze.hive.pieces;

import java.util.ArrayList;
import java.util.List;

import nl.hanze.hive.Board;
import nl.hanze.hive.Hive;
import nl.hanze.hive.Path;
import nl.hanze.hive.Position;

public class SoldierAnt extends Piece {

    public SoldierAnt(Hive.Player player) {
        super(Hive.Tile.SOLDIER_ANT, player);
    }

    @Override
    public boolean isValidMove(Board board, Position from, Position to) {
        if (from.equals(to)) {
            return false;
        } else {
            List<Path> paths = getPossiblePaths(board, from, to, new ArrayList<>(List.of(from)));
            for (Path path : paths) {
                if (board.validatePath(path)) {
                    return true;
                }
            }
            return false;
        }
    }

    private List<Path> getPossiblePaths(Board board, Position currentStep, Position to, List<Position> stepsTaken) {
        ArrayList<Path> paths = new ArrayList<Path>();
        ArrayList<Position> steps = new ArrayList<Position>(stepsTaken);

        for (Position neighbour : currentStep.getNeighbours()) {
            if (!board.hasPiece(neighbour) && !steps.contains(neighbour)
                    && board.hasNeighbours(neighbour, stepsTaken.get(0))) {
                steps.add(neighbour);
                if (neighbour.equals(to)) {
                    paths.add(new Path(steps));
                } else {
                    paths.addAll(getPossiblePaths(board, neighbour, to, steps));
                }
                steps.remove(neighbour);
            }
        }
        return paths;

    }
}
