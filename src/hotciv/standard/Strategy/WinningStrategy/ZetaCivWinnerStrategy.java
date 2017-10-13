package hotciv.standard.Strategy.WinningStrategy;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

/**
 * Created by Morten G on 02-10-2017.
 */
public class ZetaCivWinnerStrategy implements WinnerStrategy
{
    @Override
    public Player determineWinner(GameImpl game)
    {
        if (game.getCurrentRoundNumber() > 20)
        {
            if (game.getCurrentRoundNumber() == 21)
            {
                game.resetKillCount();
            }
            return new EpsilonCivWinnerStrategy().determineWinner(game);
        } else
        {
            return new BetaCivWinnerStrategy().determineWinner(game);
        }
    }
}
