package hotciv.standard.Strategy;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityIns;
import hotciv.standard.GameImpl;

/**
 * Created by morten on 9/18/17.
 */
public class BetaCivWinnerStrategy implements WinnerStrategy
{
    private GameImpl game;


    public BetaCivWinnerStrategy(GameImpl game)
    {
        this.game = game;
    }

    @Override
    public Player determineWinner()
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

        if(hasWinner == true){
            return sameOwner;
        }
        else{
            return null;
        }
    }
}

