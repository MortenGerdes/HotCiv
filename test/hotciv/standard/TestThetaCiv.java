package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.Strategy.Factory.ThetaCivFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
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

        for(int i = 0; i < 6; i++){
            city.onEndTurn();
        }

        game.getCities().put(miniIsland, city);

        assertThat(city.getResources(), is(36));
        city.setProduction(GameConstants.GALLEY);
        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getTileAt(miniIsland).getTypeString(), is(GameConstants.PLAINS));
        assertThat(game.getUnitAt(new Position(1,3)), is(notNullValue()));
    }

    @Test
    public void shouldNotSpawnGalleyOnLand(){

        Position islandCenter = new Position(6,7);
        CityIns city = new CityIns(Player.RED);

        for(int i = 0; i < 5; i++){
            city.onEndTurn();
        }

        game.getCities().put(islandCenter, city);
        city.setProduction(GameConstants.GALLEY);
        game.endOfTurn();

        assertThat(game.getUnitAt(new Position(7, 8)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(7, 9)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(6, 9)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(5, 9)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(5, 8)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(5, 7)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(6, 7)), is(nullValue()));
        assertThat(game.getUnitAt(new Position(7, 7)), is(nullValue()));

    }

    @Test
    public void shouldNotAllowGalleyToMoveOnLand(){
        Position miniIsland = new Position(1,2);
        UnitIns galley = new UnitIns(GameConstants.GALLEY, Player.RED);

        game.getUnits().put(new Position(0,2), galley);
        game.moveUnit(new Position(0,2), miniIsland);
        game.endOfTurn();

        assertThat(game.getUnitAt(new Position(0,2)).getTypeString(), is(GameConstants.GALLEY));
        assertThat(game.getUnitAt(miniIsland), is(nullValue()));
    }

    @Test
    public void shouldSpawnSettlerOnItsPerformAction()
    {
        assertThat(game.getTileAt(new Position(5, 6)).getTypeString(), is(GameConstants.PLAINS));
        game.getUnits().put(new Position(6, 5), new UnitIns(GameConstants.GALLEY, Player.RED));
        game.performUnitActionAt(new Position(6, 5));
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(5, 6)).getTypeString(), is(GameConstants.SETTLER));
    }

 /*   @Test
    public void shouldPrintOutWorldMap()
    {
        for(int i = 0; i < 16; i++)
        {
            for(int j = 0; j < 16; j++)
            {
                System.out.print(game.getTileAt(new Position(i, j)).getTypeString().charAt(0));
            }
            System.out.println();
        }
    }
*/


}
