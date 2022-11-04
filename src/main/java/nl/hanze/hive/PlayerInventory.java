package nl.hanze.hive;

import java.util.HashMap;

import nl.hanze.hive.Hive.IllegalMove;

public class PlayerInventory {
    // Elke speler heeft aan het begin van het spel de beschikking over één
    // bijenkoningin, twee spinnen, twee kevers, drie soldatenmieren en drie
    // sprinkhanen in zijn eigen kleur.

    HashMap<Hive.Tile, Integer> PlayerInventories = new HashMap<Hive.Tile, Integer>();

    public PlayerInventory() {
        PlayerInventories.put(Hive.Tile.QUEEN_BEE, 1);
        PlayerInventories.put(Hive.Tile.SPIDER, 2);
        PlayerInventories.put(Hive.Tile.BEETLE, 2);
        PlayerInventories.put(Hive.Tile.SOLDIER_ANT, 3);
        PlayerInventories.put(Hive.Tile.GRASSHOPPER, 3);
    }

    public void removePiece(Hive.Tile tile) throws IllegalMove {
        if (!hasPiece(tile)) {
            throw new IllegalMove("Player does not have this piece");
        }
        PlayerInventories.put(tile, PlayerInventories.get(tile) - 1);
    }

    public boolean hasPiece(Hive.Tile tile) {
        return PlayerInventories.get(tile) > 0;
    }
}
