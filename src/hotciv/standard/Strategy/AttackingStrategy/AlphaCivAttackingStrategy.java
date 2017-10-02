package hotciv.standard.Strategy.AttackingStrategy;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;

import java.util.HashMap;

/**
 * Created by csdev on 10/2/17.
 */
public class AlphaCivAttackingStrategy implements AttackingStrategy{
    @Override
    public HashMap<Position, Unit> attackUnit(HashMap<Position, Unit> unitMap, Position posToMoveTo, Unit unitToMove)
    {
        unitMap.remove(unitToMove);
        unitMap.put(posToMoveTo, unitToMove);

        return unitMap;
    }
}
