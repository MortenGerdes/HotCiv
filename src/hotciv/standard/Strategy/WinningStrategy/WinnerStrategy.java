package hotciv.standard.Strategy.WinningStrategy;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

/**
 * Created by morten on 9/18/17.
 */
public interface WinnerStrategy
{
    public Player determineWinner(GameImpl game);
}
