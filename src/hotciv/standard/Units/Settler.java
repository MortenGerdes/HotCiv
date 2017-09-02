package hotciv.standard.Units;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;

/**
 * Created by csdev on 9/2/17.
 */
public class Settler {

    private Player owner;

    public Settler(Player owner)
    {
        this.owner = owner;
    }

    @Override
    public String getTypeString()
    {
        return GameConstants.SETTLER;
    }

    @Override
    public Player getOwner()
    {
        return owner;
    }

    @Override
    public int getMoveCount()
    {
        return 1;
    }

    @Override
    public int getDefensiveStrength()
    {
        return 3;
    }

    @Override
    public int getAttackingStrength()
    {
        return 0;
    }
}
