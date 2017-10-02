package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.Strategy.AgeingStrategy.BetaCivAgeingStrategy;
import hotciv.standard.Strategy.AttackingStrategy.AlphaCivAttackingStrategy;
import hotciv.standard.Strategy.TestStubs.FixedDieRollStrategy;
import hotciv.standard.Strategy.UnitPerformStrategy.BetaCivAndBelowUnitActionStrategy;
import hotciv.standard.Strategy.WinningStrategy.AlphaCivWinnerStrategy;
import hotciv.standard.Strategy.WorldGenerationStrategy.DeltaCivWorldStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by morte_000 on 18-09-2017.
 */
public class TestDeltaCiv
{
    private GameImpl game;

    /**
     * Fixture for deltaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new FixedDieRollStrategy(), new BetaCivAgeingStrategy(), new AlphaCivAttackingStrategy(), new DeltaCivWorldStrategy(), new AlphaCivWinnerStrategy(), new BetaCivAndBelowUnitActionStrategy());
    }

    @Test
    public void shouldBeAnOceanTileAtPosition00()
    {
        assertThat(game.getTileAt(new Position(0,0)).getTypeString(), is(GameConstants.OCEANS));
    }
}
