package hotciv.standard.Strategy;

import hotciv.framework.Player;
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
        return null;
    }
}
