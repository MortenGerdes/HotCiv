package hotciv.standard.Strategy.UnitPerformStrategy;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.CityIns;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitIns;

/**
 * Created by morte_000 on 18-09-2017.
 */
public class GammaCivUnitActionStrategy implements UnitActionStrategy
{
    @Override
    public void performAction(GameImpl game, UnitIns unit, Position pos)
    {
        if (unit.getTypeString() == GameConstants.SETTLER && unit.remainingMoveCount() > 0)
        {
            game.getCities().put(pos, new CityIns(unit.getOwner()));
            game.getUnits().remove(pos);
            return;
        }

        if (unit.getTypeString() == GameConstants.ARCHER)
        {
            if (unit.isFortified())
            {
                unit.setFortified();
                unit.setDefensiveStrength(unit.getDefensiveStrength() / 2);
                unit.setMoveCount(1);
            } else if(!unit.isFortified() && unit.remainingMoveCount() > 0)
            {
                unit.setFortified();
                unit.setDefensiveStrength(unit.getDefensiveStrength() * 2);
                unit.setMoveCount(0);
            }
            return;
        }
    }
}
