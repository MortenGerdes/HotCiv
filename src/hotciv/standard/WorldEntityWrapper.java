package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

/**
 * Created by morten on 9/2/17.
 */

/**
 * A wrapper that collects everything that can be on a position in the world.
 * If things later in the course get's added to the world, it will be easy to implement it
 * in this system
 */
public class WorldEntityWrapper
{
    private Tile tile;
    private Unit unit;
    private City city;

    public WorldEntityWrapper(Tile tile, Unit unit, City city)
    {
        this.tile = tile;
        this.unit = unit;
        this.city = city;
    }

    public Tile getTile()
    {
        return tile;
    }

    public void setTile(Tile tile)
    {
        this.tile = tile;
    }

    public Unit getUnit()
    {
        return unit;
    }

    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }

    public City getCity()
    {
        return city;
    }

    public void setCity(City city)
    {
        this.city = city;
    }
}
