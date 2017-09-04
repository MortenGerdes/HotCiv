package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

/**
 * Created by morten on 9/2/17.
 */
public class CityIns implements City
{
    private Player owner;
    private int size = 1;
    private int ressources = 0;
    private int processPercentage = 0;
    private String production;
    private String workForceFocus;

    public CityIns(Player owner) {
        this.owner = owner;
    }

    @Override
    public Player getOwner()
    {
        return owner;
    }

    @Override
    public int getSize()
    {
        return size;
    }

    @Override
    public String getProduction()
    {
        return production;
    }


    @Override
    public String getWorkforceFocus()
    {
        return workForceFocus;
    }

    public void onEndTurn()
    {
        ressources += 6;
        size = 1;
    }

    public int getRessources()
    {
        return ressources;
    }

    public int getProcessPercentage()
    {
        return processPercentage;
    }

    public boolean setProduction(String production)
    {
        if(ressources >= GameConstants.PRICE_ON_UNIT.get(GameConstants.ARCHER) && production.equals(GameConstants.ARCHER)){
            this.production = production;
            return true;
        }
        else
            return false;

    }
}
