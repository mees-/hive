package nl.hanze.hive.pieces;

import java.util.ArrayList;
import java.util.List;

import nl.hanze.hive.Board;
import nl.hanze.hive.Hive;
import nl.hanze.hive.Path;
import nl.hanze.hive.Position;
import nl.hanze.hive.Path.Step;

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

    @Override
    public boolean canMove(Board board, Position from) {
        /*
         * Since the only thing stopping the soldier ant from moving is if it is
         * blocked, we can just check if it can move to any neighbour.
         */
        for (Position neighbour : from.getNeighbours()) {
            if (board.canStep(new Step(from, neighbour))) {
                return true;
            }
        }
        return false;
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
