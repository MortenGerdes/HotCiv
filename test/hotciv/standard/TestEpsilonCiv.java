package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.Strategy.Factory.EpsilonCivFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by csdev on 10/2/17.
 */
public class TestEpsilonCiv {

    private GameImpl game;

    /**
     * Fixture for deltaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new EpsilonCivFactory());
    }
    @Test
    public void redPlayerShouldWinAfterThreeWonAttacks(){
        //Creates an archer for player Red
        game.getUnits().put(new Position(4,0), new UnitIns(GameConstants.ARCHER, Player.RED, 1, 3, 1));
        //Creates 3 archers for player blue
        game.getUnits().put(new Position(5,0), new UnitIns(GameConstants.ARCHER, Player.BLUE, 1, 3, 0));
        game.getUnits().put(new Position(6,0), new UnitIns(GameConstants.ARCHER, Player.BLUE, 1, 3, 0));
        game.getUnits().put(new Position(7,0), new UnitIns(GameConstants.ARCHER, Player.BLUE, 1, 3, 0));

        //Making sure that it is red players turn and that it attacks and wins against the blue archer
        assertThat(game.getPlayerInTurn(), is(Player.RED));
        game.moveUnit(new Position(4, 0), new Position(5,0));
        assertThat(game.getUnitAt(new Position(5,0)).getOwner(), is(Player.RED));
        game.endOfTurn();

        //making sure that turns switch properly
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.RED));

        assertThat(game.getPlayerInTurn(), is(Player.RED));
        game.moveUnit(new Position(5, 0), new Position(6,0));
        assertThat(game.getUnitAt(new Position(6,0)).getOwner(), is(Player.RED));
        game.endOfTurn();

        //switch turn
        game.endOfTurn();

        assertThat(game.getPlayerInTurn(), is(Player.RED));
        game.moveUnit(new Position(6, 0), new Position(7,0));
        assertThat(game.getUnitAt(new Position(7,0)).getOwner(), is(Player.RED));
        game.endOfTurn();

        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldHaveRedPlayerToWin(){
        game.getKillCount().put(Player.RED, 3);
        game.endOfTurn();
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void redUnitDefeatsBlueUnit()
    {
        game.getUnits().put(new Position(4,0), new UnitIns(GameConstants.ARCHER, Player.RED, 1, 3, 1));
        game.getUnits().put(new Position(5,0), new UnitIns(GameConstants.ARCHER, Player.BLUE, 1, 3, 1));

        game.moveUnit(new Position(4,0), new Position(5,0));
        assertThat(game.getUnitAt(new Position(5,0)).getOwner(), is(Player.RED));
    }

    @Test
    public void blueUnitDefendsAgainstRed()
    {
        game.getUnits().put(new Position(4,0), new UnitIns(GameConstants.ARCHER, Player.RED, 1, 3, 1));
        game.getUnits().put(new Position(5,0), new UnitIns(GameConstants.ARCHER, Player.BLUE, 1, 10, 1));
        game.moveUnit(new Position(4, 0), new Position(5, 0));
        assertThat(game.getUnitAt(new Position(5, 0)).getOwner(), is(Player.BLUE));
    }
}
