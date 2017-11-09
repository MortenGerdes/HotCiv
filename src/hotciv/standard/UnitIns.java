package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created by morten on 9/2/17.
 */
public class UnitIns implements Unit
{
    private String type;
    private Player owner;
    private int maxMoveCount;
    private int curMoveCount;
    private int defensiveStrength;
    private int attackingStrength;
    private boolean isFortified = false;
    private boolean isShip = false;

    public UnitIns(String type, Player owner)
    {
        this(type, owner, GameConstants.DEFAULT_MOVECOUNT.get(type), 0, 0);
    }

    public UnitIns(String type, Player owner, int moveCount)
    {
        this(type, owner, moveCount, 0, 0);
    }


    public UnitIns(String type, Player owner, int moveCount, int defensiveStrength, int attackingStrength)
    {

        this.type = type;
        this.owner = owner;
        this.maxMoveCount = moveCount;
        this.defensiveStrength = defensiveStrength;
        this.attackingStrength = attackingStrength;

        if(type.equals(GameConstants.GALLEY))
        {
            isShip = true;
        }
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
        return isFortified ? 0 : maxMoveCount;
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

    public boolean isFortified()
    {
        return isFortified;
    }

    public boolean isShip()
    {
        return isShip;
    }

    public void setShip(boolean ship)
    {
        isShip = ship;
    }

    public void setMoveCount(int moveCount)
    {
        this.maxMoveCount = moveCount;
    }

    public void setDefensiveStrength(int def)
    {
        defensiveStrength = def;
    }

    public void setCurMoveCount(int moveCount)
    {
        this.curMoveCount = moveCount;
    }

    public void setFortified()
    {
        if (getTypeString() != GameConstants.ARCHER)
        {
            return;
        }
        isFortified = (isFortified == true) ? false : true;
    }

    public void resetMoveCount()
    {
        curMoveCount = maxMoveCount;
    }

    public int remaningMoveCount()
    {
        return curMoveCount;
    }
}
