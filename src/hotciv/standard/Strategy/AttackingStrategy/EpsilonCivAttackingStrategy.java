package hotciv.standard.Strategy.AttackingStrategy;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;
import hotciv.standard.Strategy.TestStubs.DieRollStrategy;
import hotciv.standard.Utility;

import java.util.HashMap;

/**
 * Created by csdev on 10/2/17.
 */
public class EpsilonCivAttackingStrategy implements AttackingStrategy
{

    private Game game;

    @Override
    public HashMap<Position, Unit> attackUnit(DieRollStrategy dieRoll, Game game, HashMap<Position, Unit> unitHashMap, Position posToMoveFrom, Position posToMoveTo)
    {
        this.game = game;

        int attackerNumber = calculateStats(game.getUnitAt(posToMoveFrom).getAttackingStrength(), posToMoveFrom);
        int defenderNumber = calculateStats(game.getUnitAt(posToMoveTo).getDefensiveStrength(), posToMoveTo);

        if (attackerNumber * dieRoll.outcome1() > defenderNumber * dieRoll.outcome2())
        {
            GameImpl gameImp = (GameImpl) game;
            Unit attackingUnit = game.getUnitAt(posToMoveFrom);
            gameImp.increaseKillCount(attackingUnit.getOwner());
            unitHashMap.remove(attackingUnit);
            unitHashMap.put(posToMoveTo, attackingUnit);
        } else
        {
            unitHashMap.remove(game.getUnitAt(posToMoveFrom));
        }

        return unitHashMap;
    }

    private int calculateStats(int baseStat, Position center)
    {
        int finalStat = baseStat;
        for (Position adjPos : Utility.get8Neighborhood(center))
        {
            if (game.getUnitAt(adjPos) != null)
            {
                if (game.getUnitAt(adjPos).getOwner() == game.getUnitAt(center).getOwner())
                {
                    finalStat++;
                }
            }
        }
        return finalStat * Utility.getTerrainFactor(game, center);
    }
}
