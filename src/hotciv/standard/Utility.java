package hotciv.standard;

import hotciv.framework.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Collection of utility methods for speeding up
 * the implementation effort in the HotCiv Game
 * <#if type == "code">
 * <p>
 * <#include "/data/author.txt">
 * </#if>
 */
public class Utility
{

    /**
     * Helper to allow writing code ala 'for (Position p : get8Neighborhood(center))'
     *
     * @param center the position on which to center the 8 neighborhood
     * @return an iterable over the valid positions in the center's 8 neighborhood.
     */
    public static Iterable<Position> get8Neighborhood(Position center)
    {
        final Iterator<Position> iterator = get8NeighborhoodIterator(center);
        Iterable<Position> i = new Iterable<Position>()
        {
            @Override
            public Iterator<Position> iterator()
            {
                return iterator;
            }
        };
        return i;
    }

    /**
     * return an iterator over positions that are in the 8 neighborhood of a given
     * position. The 8 neighborhood is the (normally 8) positions adjacent to the
     * center position. The center position itself is not part of the iterator.
     * PostCondition: The iterator is always valid and will contain from 3 to 8
     * positions. It will only contain positions that are valid on the game world,
     * that is a neighborhood centered in (0,0) will contain (1,0), (1,1) and
     * (0,1) but not e.g. (-1,-1). The iterator always return the positions in a
     * sequence starting at the north west position and the rest given row-wise.
     *
     * @param center the position marking the center of the 8 neighborhood.
     * @return iterator over the valid positions in the center's 8 neighborhood.
     */
    public static Iterator<Position> get8NeighborhoodIterator(Position center)
    {
        ArrayList<Position> list = new ArrayList<Position>();
        int row = center.getRow();
        int col = center.getColumn();
        int r, c;
        for (int dr = -1; dr <= +1; dr++)
        {
            for (int dc = -1; dc <= +1; dc++)
            {
                r = row + dr;
                c = col + dc;
                // avoid positions outside the world
                if (r >= 0 && r < GameConstants.WORLDSIZE &&
                        c >= 0 && c < GameConstants.WORLDSIZE)
                {
                    // avoid center position
                    if (r != row || c != col)
                    {
                        list.add(new Position(r, c));
                    }
                }
            }
        }
        return list.iterator();
    }

    /**
     * get the terrain factor for the attack and defense strength according to the
     * GammaCiv specification
     *
     * @param game     the game the factor should be given for
     * @param position the position that the factor should be calculated for
     * @return the terrain factor
     */
    public static int getTerrainFactor(Game game, Position position)
    {
        // cities overrule underlying terrain
        if (game.getCityAt(position) != null)
        {
            return 3;
        }
        Tile t = game.getTileAt(position);
        if (t.getTypeString() == GameConstants.FOREST ||
                t.getTypeString() == GameConstants.HILLS)
        {
            return 2;
        }
        return 1;
    }

    /**
     * calculate the additional support a unit at position p owned by a given
     * player gets from friendly units on the given game.
     *
     * @param game     the game to calculate on
     * @param position the position of the unit whose support is wanted
     * @param player   the player owning the unit at position 'position'
     * @return the support for the unit, +1 for each friendly unit in the 8
     * neighborhood.
     */
    public static int getFriendlySupport(Game game, Position position,
                                         Player player)
    {
        Iterator<Position> neighborhood = get8NeighborhoodIterator(position);
        Position p;
        int support = 0;
        while (neighborhood.hasNext())
        {
            p = neighborhood.next();
            if (game.getUnitAt(p) != null &&
                    game.getUnitAt(p).getOwner() == player)
            {
                support++;
            }
        }
        return support;
    }

    public static boolean isWalkableLandTerrain(String tileType, boolean isOceanOnly)
    {
        if (!isOceanOnly)
        {
            if (tileType.equals(GameConstants.HILLS)
                    || tileType.equals(GameConstants.FOREST)
                    || tileType.equals(GameConstants.PLAINS))
                return true;
            else
                return false;
        }
        else
        {
            if (tileType.equals(GameConstants.OCEANS))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

}