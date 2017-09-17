package hotciv.standard;

import hotciv.framework.Tile;

/**
 * Created by morten on 9/2/17.
 * Class to represent a TIle
 */
public class TileIns implements Tile
{
    private String type;

    public TileIns(String type)
    {
        this.type = type;
    }

    @Override
    public String getTypeString()
    {
        return type;
    }
}
