package hotciv.standard;
import com.sun.istack.internal.NotNull;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.Strategy.AgeingStrategy.AlphaCivAgeingStrategy;
import hotciv.standard.Strategy.AttackingStrategy.AlphaCivAttackingStrategy;
import hotciv.standard.Strategy.AttackingStrategy.EpsilonCivAttackingStrategy;
import hotciv.standard.Strategy.TestStubs.FixedDieRollStrategy;
import hotciv.standard.Strategy.UnitPerformStrategy.BetaCivAndBelowUnitActionStrategy;
import hotciv.standard.Strategy.WinningStrategy.AlphaCivWinnerStrategy;
import hotciv.standard.Strategy.WinningStrategy.EpsilonCivWinnerStrategy;
import hotciv.standard.Strategy.WinningStrategy.ZetaCivWinnerStrategy;
import hotciv.standard.Strategy.WorldGenerationStrategy.GammaCivWorldAndBelowStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertNotNull;
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
        game = new GameImpl(new FixedDieRollStrategy(), new AlphaCivAgeingStrategy(), new AlphaCivAttackingStrategy(), new GammaCivWorldAndBelowStrategy(), new ZetaCivWinnerStrategy(), new BetaCivAndBelowUnitActionStrategy());
    }

    @Test
    public void RedShouldWinAfterThreeKillCounterAfterRound20(){

        game.killCount.put(Player.RED, 3);
        assertThat(game.getWinner(), is(nullValue()));
        for(int i = 0; i < 20; i++){
            game.endOfTurn();
        }

        assertThat(game.getCurrentRoundNumber(), is(21));
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