package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.Strategy.AgeingStrategy.BetaCivAgeingStrategy;
import hotciv.standard.Strategy.UnitPerformStrategy.BetaCivAndBelowUnitActionStrategy;
import hotciv.standard.Strategy.WinningStrategy.BetaCivWinnerStrategy;
import hotciv.standard.Strategy.WorldGenerationStrategy.GammaCivWorldAndBelowStrategy;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

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
public class TestBetaCiv {
    private GameImpl game;

    /**
     * Fixture for alphaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new BetaCivAgeingStrategy(), new GammaCivWorldAndBelowStrategy(), new BetaCivWinnerStrategy(), new BetaCivAndBelowUnitActionStrategy());
    }

    @Test
    public void shouldBeBluesTurnAfterRed()
    {
        assertThat(game.getPlayerInTurn(), is(Player.RED));
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    }

    @Test
    public void shouldDeclareRedAsWinnerWhenHavingAllCities(){
        game.endOfTurn();
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldBe100YearsPerRoundBetween4000BCAnd100BC(){
        assertThat(game.getAge(), is(-4000));
        game.endOfTurn();
        assertThat(game.getAge(), is(-3900));
    }

    @Test
    public void shouldAgeCorrectlyNearAge0()
    {
        game.setAge(-100);
        assertThat(game.getAge(), is(-100));
        game.endOfTurn();
        assertThat(game.getAge(), is(-1));
        game.endOfTurn();
        assertThat(game.getAge(), is(1));
        game.endOfTurn();
        assertThat(game.getAge(), is(50));
    }

    @Test
    public void shouldPass50YearsBetweenYear50To1750()
    {
        game.setAge(50);
        assertThat(game.getAge(), is(50));
        game.endOfTurn();
        assertThat(game.getAge(), is(100));
        for(int i = 0; i < 10; i++)
        {
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(600));
    }

    @Test
    public void shouldPass25YearsBetweenYear1750To1900()
    {
        game.setAge(1750);
        assertThat(game.getAge(), is(1750));
        game.endOfTurn();
        assertThat(game.getAge(), is(1775));
        game.endOfTurn();
        assertThat(game.getAge(), is(1800));
    }

    @Test
    public void shouldPass5YearsBetweenYear1900To1970()
    {
        game.setAge(1900);
        assertThat(game.getAge(), is(1900));
        game.endOfTurn();
        assertThat(game.getAge(), is(1905));
        game.endOfTurn();
        assertThat(game.getAge(), is(1910));
    }

    @Test
    public void shouldPass1YearAfterYear1970()
    {
        game.setAge(1970);
        assertThat(game.getAge(), is(1970));
        game.endOfTurn();
        assertThat(game.getAge(), is(1971));
        game.endOfTurn();
        assertThat(game.getAge(), is(1972));
    }


}