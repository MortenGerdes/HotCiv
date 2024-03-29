package hotciv.standard.Strategy.WorldGenerationStrategy;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.TileIns;

import java.util.HashMap;

/**
 * Created by morte_000 on 18-09-2017.
 */
public class WorldGenerator
{
    public HashMap<Position, Tile> generateWorld(String[] worldData)
    {
        HashMap<Position, Tile> world = new HashMap<>();

        for (int r = GameConstants.WORLDSIZE - 1; r >= 0; r--)
        {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++)
            {
                char theChar = worldData[r].charAt(c);
                if (theChar == 'o')
                {
                    world.put(new Position(r, c), new TileIns(GameConstants.OCEANS));
                } else if (theChar == 'p')
                {
                    world.put(new Position(r, c), new TileIns(GameConstants.PLAINS));
                } else if (theChar == 'm')
                {
                    world.put(new Position(r, c), new TileIns(GameConstants.MOUNTAINS));
                } else if (theChar == 'h')
                {
                    world.put(new Position(r, c), new TileIns(GameConstants.HILLS));
                } else if (theChar == 'f')
                {
                    world.put(new Position(r, c), new TileIns(GameConstants.FOREST));
                } else
                {
                    throw new IllegalArgumentException("Illegal char: " + theChar);
                }
            }
        }
        return world;
    }

    public static String[] convertToOurConvention(String[] prevMap)
    {
        String[] toConvert = prevMap;
        String[] newMap = new String[GameConstants.WORLDSIZE];
        String line;
        for ( int r = 0; r < 16; r++ )
        {
            line = "";
            for ( int c = 0; c < 16; c++ )
            {
                char theChar = toConvert[r].charAt(c);
                if(theChar == 'M')
                {
                    line = line + "m";
                }
                else if(theChar == 'o')
                {
                    line = line + "p";
                }
                else if(theChar == '.')
                {
                    line = line + "o";
                }
                else
                {
                    line = line + toConvert[r].charAt(c);
                }
            }
            newMap[r] = line;
        }
        return newMap;
    }
}
