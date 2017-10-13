package hotciv.standard;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.Strategy.AgeingStrategy.AlphaCivAgeingStrategy;
import hotciv.standard.Strategy.AttackingStrategy.EpsilonCivAttackingStrategy;
import hotciv.standard.Strategy.Factory.EpsilonCivFactory;
import hotciv.standard.Strategy.Factory.ThetaCivFactory;
import hotciv.standard.Strategy.Factory.ZetaCivFactory;
import hotciv.standard.Strategy.TestStubs.FixedDieRollStrategy;
import hotciv.standard.Strategy.UnitPerformStrategy.BetaCivAndBelowUnitActionStrategy;
import hotciv.standard.Strategy.WinningStrategy.EpsilonCivWinnerStrategy;
import hotciv.standard.Strategy.WorldGenerationStrategy.GammaCivWorldAndBelowStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by csdev on 10/13/17.
 */

public class TestThetaCiv {

    private GameImpl game;

    @Before
    public void setUp() {
        game = new GameImpl(new ThetaCivFactory());
    }
/*
    @Test
    public void shouldNotSpawnGalleyOnLand(){
        Position miniIsland = new Position(14,2);
        game.getUnits().put(miniIsland, new UnitIns(GameConstants.GALLEY, Player.RED));
        assertThat(game.getUnitAt(miniIsland), is(nullValue()));
    }
*/
    @Test
    public void shouldSpawnGalleyOnOcean(){
        Position onOcean = new Position(15,2);
        game.getUnits().put(onOcean, new UnitIns(GameConstants.GALLEY, Player.RED));
        assertThat(game.getUnitAt(onOcean).getTypeString(), is(GameConstants.GALLEY));
    }

    @Test
    public void shouldNotAllowGalleyToMoveOnLand(){}



}
