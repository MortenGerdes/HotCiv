package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

/**
 * Created by morten on 9/2/17.
 */
public class CityIns implements City
{
    private Player owner;
    private int size;
    private int ressources;
    private int processPercentage;
    private String production;
    private String workForceFocus;

    public CityIns(Player owner) {
        this.owner = owner;
    }

    public void onEndTurn(){
        
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
}
