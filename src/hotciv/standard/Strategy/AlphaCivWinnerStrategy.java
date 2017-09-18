package hotciv.standard.Strategy;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

/**
 * Created by morten on 9/18/17.
 */
public class AlphaCivWinnerStrategy implements WinnerStrategy
{
    @Override
    public Player determineWinner(GameImpl game)
    {
        if(game.getAge() >= -3000)
        {
            return Player.RED;
        }
        return Player.RED;
    }
}
