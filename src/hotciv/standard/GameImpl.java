package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

/** Skeleton implementation of HotCiv.
 
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

public class GameImpl implements Game {
  private int age = -4000; //Initial starting age.
  private int ageIncrease = 100; // The amount of years every round will increase with.
  private Player playerInTurn = Player.RED; //First to start
  private Player winner = null;
  private HashMap<Position, WorldEntityWrapper> world = new HashMap<>();

<<<<<<< Updated upstream
  public Tile getTileAt( Position p ) { return world.get(p).getTile(); }
  public Unit getUnitAt( Position p ) { return world.get(p).getUnit(); }
  public City getCityAt( Position p ) { return world.get(p).getCity(); }
=======

  public Tile getTileAt( Position p ) { return; }
  public Unit getUnitAt( Position p ) { return null; }
  public City getCityAt( Position p ) { return null; }
>>>>>>> Stashed changes
  public Player getPlayerInTurn() { return playerInTurn; }
  public Player getWinner() { return winner; }
  public int getAge() { return age; }
  public boolean moveUnit( Position from, Position to ) {
    if(world.get(from).getUnit() == null)
    {
      return false;
    }
    else if(world.get(to).getUnit() != null){
      return false;
    }
    else{
      world.get()
    }
  }
  public void endOfTurn()
  {
    age += ageIncrease;

    playerInTurn = (playerInTurn == Player.RED) ? Player.BLUE : Player.RED; // Refactored version of turn handling

    if(age == -3000)
    {
      winner = Player.RED;
    }
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) { throw new UnsupportedOperationException();}

  //Testing purposes
  public HashMap<Position, WorldEntityWrapper> getWorld()
  {
    return world;
  }

}
