package hotciv.standard.Units;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created by morten on 9/2/17.
 */
public class Archer implements Unit
{
    private Player owner;

    public Archer(Player owner)
    {
        this.owner = owner;
    }

    @Override
    public String getTypeString()
    {
        return GameConstants.ARCHER;
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
        return 0;
    }

    @Override
    public int getAttackingStrength()
    {
        return 0;
    }
}
