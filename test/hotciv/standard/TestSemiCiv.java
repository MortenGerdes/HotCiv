package hotciv.standard;

import hotciv.framework.Player;
import hotciv.standard.Strategy.Factory.AlphaCivFactory;
import hotciv.standard.Strategy.Factory.SemiCivFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Morten G on 12-10-2017.
 */
public class TestSemiCiv
{
    private GameImpl game;

    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new SemiCivFactory());
    }

    // FRS p. 455 states that 'Red is the first player to take a turn'.
    @Test
    public void shouldBeRedAsStartingPlayer()
    {
        assertThat(game.getPlayerInTurn(), is(Player.RED));
    }
}
