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
        game.endOfTurn();
        assertThat(game.moveUnit(new Position(1,1), new Position(1,2)), is(false));
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(1,1)), is(notNullValue()));
        game.moveUnit(new Position(1, 1), new Position(1, 2));
        assertThat(game.getUnitAt(new Position(1,2)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(1, 2)).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void ShouldReturnFalseWhenTryingToMoveAUnitThatsNotThere()
    {
        assertThat(game, is(notNullValue()));
        assertThat(game.units, is(notNullValue()));

        assertThat(game.moveUnit(new Position(5,8), new Position(5,9)), is(false));
    }

    @Test
    public void ShouldHaveStartingUnitsForRedAndBlue()
    {
        assertThat(game.getUnitAt(new Position(2, 0)).getTypeString(), is(GameConstants.ARCHER));
        assertThat(game.getUnitAt(new Position(2, 0)).getOwner(), is(Player.RED));
        assertThat(game.getUnitAt(new Position(4, 3)).getTypeString(), is(GameConstants.SETTLER));
        assertThat(game.getUnitAt(new Position(4, 3)).getOwner(), is(Player.RED));
        assertThat(game.getUnitAt(new Position(3, 2)).getTypeString(), is(GameConstants.LEGION));
        assertThat(game.getUnitAt(new Position(3, 2)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void shouldHaveCityProducingSixResourcesPerRound(){
        game.cities.put(new Position(5,6), new CityIns(Player.BLUE));
        CityIns city = (CityIns) game.getCityAt(new Position(5,6));
        city.onEndTurn();
        assertThat(city.getResources(), is(6));
    }

    @Test
    public void shouldProduceUnitWhenEnoughRessources(){
        game.cities.put(new Position(5,6), new CityIns(Player.BLUE));
        CityIns city = (CityIns) game.getCityAt(new Position(5,6));
        city.onEndTurn();
        assertThat(city.setProduction(GameConstants.LEGION), is(false));
        for(int i = 0; i < 9; i++)
        {
            city.onEndTurn();
        }

        assertThat(city.setProduction(GameConstants.LEGION), is(true));
        assertThat(city.setProduction(GameConstants.ARCHER), is(true));
        assertThat(city.setProduction(GameConstants.SETTLER), is(true));
        assertThat(city.setProduction(GameConstants.LEGION), is(false));
    }

    @Test
    public void shouldCreateUnitWithCorrectMoveCount(){
        game.units.put(new Position(5,5), new UnitIns(GameConstants.ARCHER, Player.RED,3));
        assertThat(game.getUnitAt(new Position(5,5)).getMoveCount(), is(3));
    }

    @Test
    public void shouldCreateUnitWithAttackAndDefence(){
        game.units.put(new Position(5,5), new UnitIns(GameConstants.ARCHER, Player.RED,3, 3, 2));
        assertThat(game.getUnitAt(new Position(5,5)).getAttackingStrength(), is(2));
        assertThat(game.getUnitAt(new Position(5,5)).getDefensiveStrength(), is(3));
    }

    @Test
    public void shouldBePlainTileAtPosition00() {
        assertThat(game.getTileAt(new Position(0,0)).getTypeString(), is(GameConstants.PLAINS));
    }

    @Test
    public void shouldBeCorrectInfoAboutCity(){
        game.cities.put(new Position(5,6), new CityIns(Player.BLUE));
        CityIns city = (CityIns) game.getCityAt(new Position(5,6));

        assertThat(game.getCityAt(new Position(5,6)).getOwner(), is(Player.BLUE));
        assertThat(((CityIns) game.getCityAt(new Position(5,6))).getResources(), is(0));
        assertThat(game.getCityAt(new Position(5,6)).getSize(), is(1));
        assertThat(((CityIns) game.getCityAt(new Position(5,6))).getProcessPercentage(), is(100));
    }

    @Test
    public void shouldCreateAUnitWhenSetToProduce()
    {
        game.cities.put(new Position(5,6), new CityIns(Player.RED));
        CityIns city = (CityIns) game.getCityAt(new Position(5,6));

        assertThat(game.getCityAt(new Position(5,6)), is(notNullValue()));
        assertThat(game.getCityAt(new Position(5,6)).getOwner(), is(Player.RED));
        game.endOfTurn();
        game.endOfTurn(); // Har nu 12 resources og archer koster 10

        assertThat(city.setProduction(GameConstants.ARCHER), is(true)); // Nu er resources 2
        game.endOfTurn(); // Tilføjer 6 til resources: 6 + 2 = 8
        assertThat(game.units.get(new Position(5,6)).getTypeString(), is(GameConstants.ARCHER));
        assertThat(game.units.get(new Position(5,6)).getOwner(), is(Player.RED));
        assertThat(city.getResources(), is(8)); // Checker om resources er 8
    }

    @Test
    public void shouldAllowUnitsToAttack(){
        game.units.put(new Position(10, 2), new UnitIns(GameConstants.ARCHER, Player.RED));
        game.units.put(new Position(10, 3), new UnitIns(GameConstants.LEGION, Player.BLUE));
        game.moveUnit(new Position(10,2), new Position(10,3));
        assertThat(game.getUnitAt(new Position(10,3)).getTypeString(), is(GameConstants.ARCHER));
        assertThat(game.getUnitAt(new Position(10,3)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldNotAllowUnitFromSameTeamToAttack(){
        game.units.put(new Position(10, 2), new UnitIns(GameConstants.ARCHER, Player.RED));
        game.units.put(new Position(10, 3), new UnitIns(GameConstants.LEGION, Player.RED));
        game.moveUnit(new Position(10, 2), new Position(10, 3));
        assertThat(game.getUnitAt(new Position(10,3)).getTypeString(), is(GameConstants.LEGION));
        assertThat(game.getUnitAt(new Position(10,2)).getTypeString(), is(GameConstants.ARCHER));
    }
/*
    @Test
    public void shouldSpawnUnitsCorrectly() throws InterruptedException
    {
        game.cities.put(new Position(5,5), new CityIns(Player.RED));
        CityIns castedCity = (CityIns) game.getCityAt(new Position(5,5));

        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();


        castedCity.setProduction(GameConstants.ARCHER);
        game.endOfTurn();
        castedCity.setProduction(GameConstants.ARCHER);
        game.endOfTurn();

        assertThat(game.getUnitAt(new Position(5,5)).toString(), is(GameConstants.ARCHER));
      //  assertThat(game.getUnitAt(new Position(6,6)).toString(), is(GameConstants.ARCHER));
    }
    */


}