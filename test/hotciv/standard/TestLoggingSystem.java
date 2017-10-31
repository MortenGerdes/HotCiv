package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.Strategy.Factory.AlphaCivFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Morten G on 31-10-2017.
 */
public class TestLoggingSystem
{
    private Game game;
    private GameImpl gameImpl;
    private GameLogDecorator decorator;

    @Before
    public void setUp()
    {
        gameImpl = new GameImpl(new AlphaCivFactory());
        decorator = new GameLogDecorator(gameImpl);
        game = gameImpl;
        enableLogging(true); // Set this to false to remove loggin.
    }

    @Test
    public void shouldBeBluesTurnAfterRed()
    {
        assertThat(game.getPlayerInTurn(), is(Player.RED));
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    }

    @Test
    public void shouldNotMoveMoreThanTwoTiles()
    {
        GameImpl enhancedGame;
        if(game instanceof GameLogDecorator)
        {
            enhancedGame = ((GameImpl)((GameLogDecorator)game).unpackDecoratee());
        }
        else
        {
            enhancedGame = ((GameImpl)game);
        }

        enhancedGame.getUnits().put(new Position(3,3), new UnitIns(GameConstants.ARCHER, Player.RED, 1));
        enhancedGame.getUnits().put(new Position(5,5), new UnitIns(GameConstants.ARCHER, Player.RED, 1));
        enhancedGame.getUnits().put(new Position(8,8), new UnitIns(GameConstants.ARCHER, Player.RED, 1));
        enhancedGame.getUnits().put(new Position(10,10), new UnitIns(GameConstants.ARCHER, Player.RED, 1));

        assertThat(game.moveUnit(new Position(3, 3), new Position(3, 5)), is(false));
        assertThat(game.moveUnit(new Position(5, 5), new Position(2, 5)), is(false));
        //game.endOfTurn(); // Why the fuck does this make it return true?!?... TODO
        assertThat(game.moveUnit(new Position(8, 8), new Position(2, 2)), is(false));

        assertThat(game.moveUnit(new Position(10, 10), new Position(9, 11)), is(true));
    }

    private void enableLogging(boolean condition)
    {
        if(condition)
        {
            game = decorator;
        }
        else
        {
            game = gameImpl;
        }
    }
}
