package hotciv.standard;
import hotciv.framework.Game;
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
import static org.hamcrest.CoreMatchers.notNullValue;
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

    @Test
    public void shouldSpawnGalleyOnFirstAvailableOceanTile(){

        Position miniIsland = new Position(1,2);
        CityIns city = new CityIns(Player.RED);

        for(int i = 0; i < 5; i++){
            city.onEndTurn();
        }

        game.getCities().put(miniIsland, city);

        assertThat(city.getResources(), is(30));
        city.setProduction(GameConstants.GALLEY);
        game.endOfTurn();

        assertThat(game.getTileAt(miniIsland).getTypeString(), is(GameConstants.PLAINS));
        //assertThat(game.getUnitAt(new Position(0,2)), is(notNullValue()));
    }

    @Test
    public void shouldNotSpawnGalleyOnLand(){

        Position islandCenter = new Position(6,8);
        CityIns city = new CityIns(Player.RED);

        for(int i = 0; i < 5; i++){
            city.onEndTurn();
        }

        game.getCities().put(islandCenter, city);
        city.setProduction(GameConstants.GALLEY);
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(5, 8)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(5, 9)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(6, 9)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(7, 9)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(7, 8)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(7, 7)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(6, 7)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(5, 7)), is(nullValue()));

    }
    @Test
    public void shouldNotAllowGalleyToMoveOnLand(){
        Position miniIsland = new Position(1,2);

        game.getUnits().put(new Position(0,2), new UnitIns(GameConstants.GALLEY, Player.RED));
        game.moveUnit(new Position(0,2), miniIsland);
        game.endOfTurn();

        assertThat(game.getUnitAt(new Position(0,2)).getTypeString(), is(GameConstants.GALLEY));
        assertThat(game.getUnitAt(miniIsland).getTypeString(), is(GameConstants.GALLEY));

        System.out.println(game.getUnitAt(new Position(0,2)).getTypeString());
        System.out.println(game.getUnitAt(new Position(1,2)).getTypeString());
    }



}
