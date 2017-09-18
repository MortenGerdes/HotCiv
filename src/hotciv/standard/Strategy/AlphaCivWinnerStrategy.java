package hotciv.standard.Strategy;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

/**
 * Created by morten on 9/18/17.
 */
public class AlphaCivWinnerStrategy implements WinnerStrategy
{
    private GameImpl game;

    public AlphaCivWinnerStrategy(GameImpl game)
    {
        this.game = game;
    }

    @Override
    public Player determineWinner()
    {
        return null;
    }
}
