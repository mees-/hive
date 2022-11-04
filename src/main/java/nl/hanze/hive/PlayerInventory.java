package nl.hanze.hive;

import nl.hanze.hive.pieces.*;

import java.util.HashMap;

public class PlayerInventory {
//Elke speler heeft aan het begin van het spel de beschikking over één
//bijenkoningin, twee spinnen, twee kevers, drie soldatenmieren en drie
//sprinkhanen in zijn eigen kleur.

    HashMap<Enum,Integer> PlayerInventories = new HashMap<Enum, Integer>();

    public void setPlayerInventories(HashMap<Enum, Integer> playerInventories) {
        PlayerInventories = playerInventories;
        playerInventories.put(Hive.Tile.QUEEN_BEE,1);
        playerInventories.put(Hive.Tile.SPIDER,2);
        playerInventories.put(Hive.Tile.BEETLE,2);
        playerInventories.put(Hive.Tile.SOLDIER_ANT,3);
        playerInventories.put(Hive.Tile.GRASSHOPPER,3);
    }
}
