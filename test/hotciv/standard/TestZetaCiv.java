package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.Strategy.Factory.ZetaCivFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;


/**
 * Created by csdev on 10/2/17.
 */
public class TestZetaCiv {

    private GameImpl game;

    /**
     * Fixture for deltaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new ZetaCivFactory());
    }

    @Test
    public void RedShouldWinAfterThreeKillCounterAfterRound20(){

        assertThat(game.getWinner(), is(nullValue()));
        for(int i = 0; i < 20; i++){
            game.endOfTurn();
        }
        assertThat(game.getCurrentRoundNumber(), is(21));
        game.killCount.put(Player.RED, 3);
        game.endOfTurn();
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldDeclareRedAsWinnerWhenHavingAllCities(){
        game.getCities().put(new Position(1, 1), new CityIns(Player.RED));
        game.getCities().put(new Position(4, 1), new CityIns(Player.RED));
        game.endOfTurn();
        assertThat(game.getWinner(), is(Player.RED));
    }

}