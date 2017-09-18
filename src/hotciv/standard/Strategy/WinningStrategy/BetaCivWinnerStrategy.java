package hotciv.standard.Strategy.WinningStrategy;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityIns;
import hotciv.standard.GameImpl;

/**
 * Created by morten on 9/18/17.
 */
public class BetaCivWinnerStrategy implements WinnerStrategy
{
    @Override
    public Player determineWinner(GameImpl game)
    {
        Player sameOwner = null;
        boolean hasWinner = true;

        for(Position position : game.getCities().keySet()){
            CityIns city = (CityIns)game.getCities().get(position);

            if(sameOwner == null){
                sameOwner = city.getOwner();
            }
            else if(sameOwner != city.getOwner()){
                hasWinner = false;
            }
        }

        if(hasWinner){
            return sameOwner;
        }
        else{
            return null;
        }
    }
}
