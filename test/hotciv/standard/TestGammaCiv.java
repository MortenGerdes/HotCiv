package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.Strategy.Factory.GammaCivFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/** Skeleton class for AlphaCiv test cases

 Updated Oct 2015 for using Hamcrest matchers

 This source code is from the book
 "Flexible, Reliable Software:
 Using Patterns and Agile Development"
 published 2010 by CRC Press.
 Author:
 Henrik B Christensen
 Department of Computer Science
 Aarhus University

 Please visit http://www.baerbak.com/ for further information.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
public class TestGammaCiv {
    private GameImpl game;

    /**
     * Fixture for gammaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new GammaCivFactory());
    }

    @Test
    public void shouldMakeSettlerBuildACity(){
        game.getUnits().put(new Position(1,1), new UnitIns(GameConstants.SETTLER, Player.RED, 1, 1,1));
        game.performUnitActionAt(new Position(1,1));
        assertThat(game.getCityAt(new Position(1,1)), is(notNullValue()));
    }

    @Test
    public void shouldFortifyArcher(){
        game.getUnits().put(new Position(5,5), new UnitIns(GameConstants.ARCHER, Player.RED));
        game.performUnitActionAt(new Position(5,5));
        assertThat(game.getUnitAt(new Position(5,5)).getDefensiveStrength(), is(game.getUnitAt(new Position(5,5)).getDefensiveStrength()*2));
        assertThat(game.getUnitAt(new Position(5, 5)).getMoveCount(), is(0));
    }

    @Test
    public void shouldFortifyArcherBack(){
        game.getUnits().put(new Position(5,5), new UnitIns(GameConstants.ARCHER, Player.RED, 1, 3,1));
        game.performUnitActionAt(new Position(5,5));
        game.performUnitActionAt(new Position(5,5));
        assertThat(game.getUnitAt(new Position(5,5)).getDefensiveStrength(), is(3));
        assertThat(game.getUnitAt(new Position(5,5)).getMoveCount(), is(1));
    }
}