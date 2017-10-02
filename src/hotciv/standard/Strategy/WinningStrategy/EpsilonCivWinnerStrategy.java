package hotciv.standard.Strategy.WinningStrategy;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

/**
 * Created by csdev on 10/2/17.
 */
public class EpsilonCivWinnerStrategy implements WinnerStrategy{
    @Override
    public Player determineWinner(GameImpl game) {

        for(Player player : game.getKillCount().keySet())
        {
            if (game.getKillCount().get(player) > 2)
            {
                return player;
            }
        }
        return null;
    }
}
