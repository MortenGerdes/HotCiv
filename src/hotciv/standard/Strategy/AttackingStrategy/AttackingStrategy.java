package hotciv.standard.Strategy.AttackingStrategy;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;
import javafx.geometry.Pos;

import java.util.HashMap;

/**
 * Created by csdev on 10/2/17.
 */
public interface AttackingStrategy
{
    public HashMap<Position, Unit> attackUnit(Game game, HashMap<Position, Unit> unitHashMap, Position posToMoveFrom, Position posToMoveTo);
}
