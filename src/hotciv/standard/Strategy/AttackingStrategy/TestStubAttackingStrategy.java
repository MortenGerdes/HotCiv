package hotciv.standard.Strategy.AttackingStrategy;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.Strategy.TestStubs.DieRollStrategy;

import java.util.HashMap;

/**
 * Created by Morten G on 02-10-2017.
 */
public class TestStubAttackingStrategy implements AttackingStrategy
{

    @Override
    public HashMap<Position, Unit> attackUnit(DieRollStrategy dieRollStrategy, Game game, HashMap<Position, Unit> unitHashMap, Position posToMoveFrom, Position posToMoveTo)
    {
        return null;
    }
}
