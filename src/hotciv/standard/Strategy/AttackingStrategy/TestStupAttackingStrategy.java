package hotciv.standard.Strategy.AttackingStrategy;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;
import hotciv.standard.Utility;
import javafx.geometry.Pos;

import java.util.HashMap;

/**
 * Created by Morten G on 02-10-2017.
 */
public class TestStupAttackingStrategy implements AttackingStrategy
{
    private Game game;
    private HashMap<Position, Unit> unitHashMap;
    private Position posToMoveFrom;
    private Position posToMoveTo;

    @Override
    public HashMap<Position, Unit> attackUnit(Game game, HashMap<Position, Unit> unitHashMap, Position posToMoveFrom, Position posToMoveTo)
    {
        this.game = game;
        this.unitHashMap = unitHashMap;
        this.posToMoveFrom = posToMoveFrom;
        this.posToMoveTo = posToMoveTo;

        int attackerNumber = calculateStats(game.getUnitAt(posToMoveFrom).getAttackingStrength(), posToMoveFrom);
        int defenderNumber = calculateStats(game.getUnitAt(posToMoveTo).getDefensiveStrength(), posToMoveTo);
        int randomValue = 3;

        if(attackerNumber+randomValue > defenderNumber+randomValue)
        {
            Unit attackingUnit = game.getUnitAt(posToMoveFrom);
            unitHashMap.remove(attackingUnit);
            unitHashMap.put(posToMoveTo, attackingUnit);
        }
        else
        {
            unitHashMap.remove(game.getUnitAt(posToMoveFrom));
        }

        return unitHashMap;
    }

    private int calculateStats(int baseStat, Position center)
    {
        int finalStat = baseStat;
        for(Position adjPos: Utility.get8Neighborhood(center))
        {
            if(game.getUnitAt(adjPos) != null)
            {
                if(game.getUnitAt(adjPos).getOwner() == game.getUnitAt(center).getOwner())
                {
                    finalStat++;
                }
            }
        }
        return finalStat * Utility.getTerrainFactor(game, center);
    }
}
