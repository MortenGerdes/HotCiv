package hotciv.standard.Strategy.UnitPerformStrategy;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitIns;
import hotciv.standard.Utility;

/**
 * Created by Morten G on 10/13/17.
 */
public class ThetaCivUnitActionStrategy implements UnitActionStrategy
{
    @Override
    public void performAction(GameImpl game, UnitIns unit, Position pos)
    {
        if (unit.getTypeString() == GameConstants.GALLEY)
        {
            for(Position localPos: Utility.get8Neighborhood(pos))
            {
                if(Utility.isWalkableLandTerrain(game.getTileAt(localPos).getTypeString()))
                {
                    game.getUnits().remove(pos);
                    game.getUnits().put(localPos, new UnitIns(GameConstants.SETTLER, unit.getOwner(), 0));
                    return;
                }
            }
        }
    }
}
