package hotciv.standard;

import hotciv.framework.*;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/** Test the utility library's methods to calculate neighborhoods
 * and battle factors.
 */
public class TestUtility {
    private Iterator<Position> iter;
    private List<Position> neighborhood;
    private Position center;

    Game game;
    @Before public void setUp() {
        game = new GameStubForBattleTesting();
    }

    /** helper method to insert elements in an iterator into a list. */
    private List<Position> convertIteration2List(Iterator<Position> iter) {
        List<Position> list = new ArrayList<>();
        iter.forEachRemaining(list::add); // Doing it the Java8 way
        return list;
    }

    @Test
    public void shouldGive8PositionsForCenterAt8_8() {
        // Simple learning test, showing typical use of
        // for loop
        List<Position> list = new ArrayList<>();
        for (Position p : Utility.get8Neighborhood(new Position(8,8))) {
            list.add(p);
        }

        // Spot testing, not exhaustive
        assertThat(list.size(), is(8));
        assertThat(list, hasItems( new Position[] {
                new Position(7,7),
                new Position(7,8),
                new Position(9,9) }));
        assertThat(list, not(hasItem(new Position(8,8))));
        assertThat(list, not(hasItem(new Position(6,8))));
    }

    @Test public void shouldGive3PositionsForP0_0() {
        center = new Position(0,0);
        iter = Utility.get8NeighborhoodIterator(center);
        neighborhood = convertIteration2List( iter );

        assertThat(neighborhood.size(), is(3));
        assertThat(neighborhood, hasItems( new Position[] {
                new Position(0,1),
                new Position(1,1),
                new Position(0,1) }));
    }

    @Test public void shouldGive3PositionsForP15_15() {
        center = new Position(15,15);
        iter = Utility.get8NeighborhoodIterator(center);
        neighborhood = convertIteration2List( iter );

        assertThat(neighborhood.size(), is(3));
        assertThat(neighborhood, hasItems( new Position[] {
                new Position(14,15),
                new Position(14,14),
                new Position(15,14) }));
    }

    @Test public void shouldGiveCorrectTerrainFactors() {
        // plains have multiplier 1
        assertThat(Utility.getTerrainFactor(game, new Position(0,1)), is(1));
        // hills have multiplier 2
        assertThat(Utility.getTerrainFactor(game, new Position(1,0)), is(2));
        // forest have multiplier 2
        assertThat(Utility.getTerrainFactor(game, new Position(0,0)), is(2));
        // cities have multiplier 3
        assertThat(Utility.getTerrainFactor(game, new Position(1,1)), is(3));
    }

    @Test public void shouldGiveSum1ForBlueAtP5_5() {
        assertThat("Blue unit at (5,5) should get +1 support",
                Utility.getFriendlySupport( game, new Position(5,5), Player.BLUE), is(+1));
    }

    @Test public void shouldGiveSum0ForBlueAtP2_4() {
        assertThat("Blue unit at (2,4) should get +0 support",
                Utility.getFriendlySupport( game, new Position(2,4), Player.BLUE), is(+0));
    }
    @Test public void shouldGiveSum2ForRedAtP2_4() {
        assertThat("Red unit at (2,4) should get +2 support",
                Utility.getFriendlySupport( game, new Position(2,4), Player.RED), is(+2));
    }
    @Test public void shouldGiveSum3ForRedAtP2_2() {
        assertThat("Red unit at (2,2) should get +3 support",
                Utility.getFriendlySupport( game, new Position(2,2), Player.RED), is(+3));
    }
}

// ================================== TEST STUBS ===
class StubTile implements Tile {
    private String type;
    public StubTile(String type, int r, int c) { this.type = type; }
    public String getTypeString() { return type; }
}

class StubUnit implements Unit {
    private String type; private Player owner;
    public StubUnit(String type, Player owner) {
        this.type = type; this.owner = owner;
    }
    public String getTypeString() { return type; }
    public Player getOwner() { return owner; }
    public int getMoveCount() { return 0; }
    public int getDefensiveStrength() { return 0; }
    public int getAttackingStrength() { return 0; }
}


/** A test stub for testing the battle calculation methods in
 * Utility. The terrains are
 * 0,0 - forest;
 * 1,0 - hill;
 * 0,1 - plain;
 * 1,1 - city.
 *
 * Red has units on 2,3; 3,2; 3,3; blue one on 4,4
 */
class GameStubForBattleTesting implements Game {
    public Tile getTileAt(Position p) {
        if ( p.getRow() == 0 && p.getColumn() == 0 ) {
            return new StubTile(GameConstants.FOREST, 0, 0);
        }
        if ( p.getRow() == 1 && p.getColumn() == 0 ) {
            return new StubTile(GameConstants.HILLS, 1, 0);
        }
        return new StubTile(GameConstants.PLAINS, 0, 1);
    }
    public Unit getUnitAt(Position p) {
        if ( p.getRow() == 2 && p.getColumn() == 3 ||
                p.getRow() == 3 && p.getColumn() == 2 ||
                p.getRow() == 3 && p.getColumn() == 3 ) {
            return new StubUnit(GameConstants.ARCHER, Player.RED);
        }
        if ( p.getRow() == 4 && p.getColumn() == 4 ) {
            return new StubUnit(GameConstants.ARCHER, Player.BLUE);
        }
        return null;
    }
    public City getCityAt(Position p) {
        if ( p.getRow() == 1 && p.getColumn() == 1 ) {
            return new City() {
                public Player getOwner() { return Player.RED; }
                public int getSize() { return 1; }
                public String getProduction() {
                    return null;
                }
                public String getWorkforceFocus() {
                    return null;
                }
            };
        }
        return null;
    }

    // the rest is unused test stub methods...
    public void changeProductionInCityAt(Position p, String unitType) {}
    public void changeWorkForceFocusInCityAt(Position p, String balance) {}
    public void endOfTurn() {}
    public Player getPlayerInTurn() {return null;}
    public Player getWinner() {return null;}
    public int getAge() { return 0; }
    public boolean moveUnit(Position from, Position to) {return false;}
    public void performUnitActionAt( Position p ) {}
}

