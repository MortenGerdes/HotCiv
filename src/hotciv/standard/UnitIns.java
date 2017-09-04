package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created by morten on 9/2/17.
 */
public class UnitIns implements Unit
{
    private String type;
    private Player owner;
    private int moveCount;
    private int defensiveStrength;
    private int attackingStrength;

    public UnitIns(String type, Player owner)
    {
        this(type, owner, 1, 0, 0);
    }

    public UnitIns(String type, Player owner, int moveCount)
    {
        this(type, owner, moveCount, 0, 0);
    }


    public UnitIns(String type, Player owner, int moveCount, int defensiveStrength, int attackingStrength)
    {
        this.moveCount = moveCount;
        this.defensiveStrength = defensiveStrength;
        this.attackingStrength = attackingStrength;
        this.type = type;
        this.owner = owner;
    }

    @Override
    public String getTypeString()
    {
        return type;
    }

    @Override
    public Player getOwner()
    {
        return owner;
    }

    @Override
    public int getMoveCount()
    {
        return moveCount;
    }

    @Override
    public int getDefensiveStrength()
    {
        return defensiveStrength;
    }

    @Override
    public int getAttackingStrength()
    {
        return attackingStrength;
    }
}
