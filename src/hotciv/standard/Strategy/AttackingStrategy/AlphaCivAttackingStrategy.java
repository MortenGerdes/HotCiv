package hotciv.standard.Strategy.AttackingStrategy;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;
import hotciv.standard.Strategy.TestStubs.DieRollStrategy;
import hotciv.standard.UnitIns;

import java.util.HashMap;

/**
 * Created by csdev on 10/2/17.
 */
public class AlphaCivAttackingStrategy implements AttackingStrategy
{
    @Override
    public HashMap<Position, Unit> attackUnit(DieRollStrategy dieRollStrategy, Game game, HashMap<Position, Unit> unitHashMap, Position posToMoveFrom, Position posToMoveTo)
    {
        UnitIns unitToMove = (UnitIns) game.getUnitAt(posToMoveFrom);
        unitHashMap.remove(unitToMove);
        unitHashMap.put(posToMoveTo, unitToMove);
        ((GameImpl) game).increaseKillCount(unitToMove.getOwner());

        return unitHashMap;
    }
}
