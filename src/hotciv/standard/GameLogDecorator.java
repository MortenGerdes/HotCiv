package hotciv.standard;

import hotciv.framework.*;

/**
 * Created by Morten G on 30-10-2017.
 */
public class GameLogDecorator implements Game
{
    private Game game;

    public GameLogDecorator(Game game)
    {
        this.game = game;
    }

    @Override
    public Tile getTileAt(Position p)
    {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p)
    {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p)
    {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn()
    {
        return game.getPlayerInTurn();
    }

    @Override
    public Player getWinner()
    {
        return game.getWinner();
    }

    @Override
    public int getAge()
    {
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to)
    {
        Unit theMovingUnit = getUnitAt(from);
        boolean result = game.moveUnit(from, to);
        if(result)
        {
            theMovingUnit = game.getUnitAt(to);
            System.out.println("Player " + getPlayerInTurn().toString() + " moved unittype " + theMovingUnit.getTypeString() + " from position "
                    + from.toString() + " to position " + to.toString());
        }
        else if(theMovingUnit != null)
        {
            System.out.println("Player " + getPlayerInTurn().toString() + " tried to move unittype " + theMovingUnit.getTypeString() + " from position "
                    + from.toString() + " to position " + to.toString() + ", but failed miserably");
        }

        return result;
    }

    @Override
    public void endOfTurn()
    {
        System.out.println("Player " + getPlayerInTurn().toString() + " ends turn");
        game.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance)
    {
        game.changeWorkForceFocusInCityAt(p, balance);
        if(getCityAt(p) != null)
        {
            City theCity = getCityAt(p);
            System.out.println("Player " + getPlayerInTurn().toString() + " changed work focus in city at " + p.toString() + ". Balance = " + balance);
        }
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType)
    {
        game.changeProductionInCityAt(p, unitType);
        if(getCityAt(p) != null)
        {
            City theCity = getCityAt(p);
            System.out.println("Player " + getPlayerInTurn().toString() + " changed production in city at " + p.toString() + ". UnitType = " + unitType);
        }
    }

    @Override
    public void performUnitActionAt(Position p)
    {
        game.performUnitActionAt(p);
        if(getUnitAt(p) != null)
        {
            System.out.println("Player " + getPlayerInTurn().toString() + " performed unit action on unittype " + getUnitAt(p).getTypeString() + " on position = " + p.toString());
            return;
        }
        System.out.println("Player " + getPlayerInTurn().toString() + " tried performing action on invalid unit at position = " + p.toString());
    }

    public Game unpackDecoratee()
    {
        return game;
    }
}
