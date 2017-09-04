package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

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
public class TestAlphaCiv {
  private GameImpl game; //Todo change to "Game" again?

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    // TODO: reenable the assert below to get started...
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldBeBlueTurnAfterRed()
  {
    assertThat(game, is(notNullValue()));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }

  @Test
  public void shouldBeRedTurnAfterBlue()
  {
    assertThat(game, is(notNullValue()));
    game.endOfTurn(); //End red turn
    game.endOfTurn(); //End blue turn
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldOnlyBeOneUnitPerTile()
  {
    assertThat(game, is(notNullValue()));

  }

  /** REMOVE ME. Not a test of HotCiv, just an example of what
      matchers the hamcrest library has... */
  @Test
  public void shouldDefinetelyBeRemoved() {
    // Matching null and not null values
    // 'is' require an exact match
    String s = null;
    assertThat(s, is(nullValue()));
    s = "Ok";
    assertThat(s, is(notNullValue()));
    assertThat(s, is("Ok"));

    // If you only validate substrings, use containsString
    assertThat("This is a dummy test", containsString("dummy"));

    // Match contents of Lists
    List<String> l = new ArrayList<String>();
    l.add("Bimse");
    l.add("Bumse");
    // Note - ordering is ignored when matching using hasItems
    assertThat(l, hasItems(new String[] {"Bumse","Bimse"}));

    // Matchers may be combined, like is-not
    assertThat(l.get(0), is(not("Bumse")));
  }

  @Test
  public void shouldStartInYear4000BC()
  {
      assertThat(game, is(notNullValue()));
      assertThat(game.getAge(), is(-4000));
  }

  @Test
  public void shouldBe3900BCAfterFirstRound()
  {
      assertThat(game, is(notNullValue()));
      assertThat(game.getAge(), is(-4000));
      game.endOfTurn();
      assertThat(game.getAge(), is(-3900));
  }

    @Test
    public void shouldIncreaseWith100YearsPerRound()
    {
        assertThat(game, is(notNullValue()));
        game.endOfTurn();
        assertThat(game.getAge(), is(-3900));
        game.endOfTurn();
        assertThat(game.getAge(), is(-3800));
    }

    @Test
    public void shouldBeRedThatWinsInYear3000BC()
    {
        for(int i = 0; i<10; i++)
        {
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(-3000));
        assertThat(game.getWinner(), is(Player.RED));
    }
    

    @Test
    public void shouldMoveAUnitToAnotherPosition()
    {
        assertThat(game, is(notNullValue()));
        assertThat(game.units, is(notNullValue()));

        game.units.put(new Position(1,1), new UnitIns(GameConstants.ARCHER, Player.RED));
        assertThat(game.getUnitAt(new Position(1,1)), is(notNullValue()));
        game.moveUnit(new Position(1, 1), new Position(1, 2));
        assertThat(game.getUnitAt(new Position(1,2)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(1, 2)).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldHaveRedStartingWithArcherAndSettler(){
        assertThat(game.getUnitAt(new Position(2, 0)).getTypeString(), is(GameConstants.ARCHER));
        assertThat(game.getUnitAt(new Position(4,3)).getTypeString(), is(GameConstants.SETTLER));
    }

    @Test
    public void shouldHaveBlueStartingWithLegion(){
        //game.getWorld().put(new Position(3,2), new WorldEntityWrapper(null, new UnitIns(GameConstants.LEGION, Player.RED), null));
        assertThat(game.getUnitAt(new Position(3, 2)).getTypeString(), is(GameConstants.LEGION));
    }
}
