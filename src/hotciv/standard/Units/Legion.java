package hotciv.standard.Units;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created by csdev on 9/2/17.
 */
public class Legion implements Unit {

    private Player owner;

    public Legion(Player owner)
    {
        this.owner = owner;
    }

    @Override
    public String getTypeString()
    {
        return GameConstants.LEGION;
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
        return 2;
    }

    @Override
    public int getAttackingStrength()
    {
        return 4;
    }


}
