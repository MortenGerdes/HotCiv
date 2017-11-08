package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.Strategy.Factory.AlphaCivFactory;
import hotciv.standard.Strategy.Factory.DeltaCivFactory;
import org.junit.Before;
import org.junit.Test;
import stub.GameObserverStub;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by csdev on 11/8/17.
 */
public class TestGameObserver{

    GameObserverStub gameObserver;
    GameImpl game;

    @Before
    public void setUp() {
        game = new GameImpl(new AlphaCivFactory());
        gameObserver = new GameObserverStub();
        game.addObserver(gameObserver);
    }

    @Test
    public void shouldCallWorldChangedAtUponMovingAUnit(){
        UnitIns unit = new UnitIns(GameConstants.ARCHER, Player.RED, 1,1,1);
        game.getUnits().put(new Position(2,2), unit);
        game.moveUnit(new Position(2,2), new Position(2,3));
        assertThat(gameObserver.position, is(new Position(2,3)));

    }

}
