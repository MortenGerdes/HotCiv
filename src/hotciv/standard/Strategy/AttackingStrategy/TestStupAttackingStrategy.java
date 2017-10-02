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

        int attackerNumber = game.getUnitAt(posToMoveFrom).getAttackingStrength();
        int defenderNumber = unitHashMap.get(posToMoveTo).getDefensiveStrength();
        int randomValue = 3;

        return unitHashMap;
    }

    private int calculateStats(int baseStat)
    {
        /*
        int finalStat = baseStat;
        for(Position adjPos: Utility.get8Neighborhood());
        */
        return 1;
    }
}
