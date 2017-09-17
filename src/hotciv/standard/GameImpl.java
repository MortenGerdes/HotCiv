package hotciv.standard;

import hotciv.framework.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
  private Player winner = null;
  private Player playerInTurn = Player.RED;

  private HashMap<Position, Unit> units = new HashMap<>();
  private HashMap<Position, City> cities = new HashMap<>();
  private HashMap<Position, Tile> tiles = new HashMap<>();


  /**
   * Game initial code goes here.
   */
  public GameImpl()
  {
    for(int i = 0; i < 16; i++) // Populate the world
    {
      for(int j = 0; j < 16; j++)
      {
        tiles.put(new Position(i,j), new TileIns(GameConstants.PLAINS));
      }
    }
      units.put(new Position(2, 0), new UnitIns(GameConstants.ARCHER, Player.RED));
      units.put(new Position(4, 3), new UnitIns(GameConstants.SETTLER, Player.RED));
      units.put(new Position(3, 2), new UnitIns(GameConstants.LEGION, Player.BLUE));
  }

  public Tile getTileAt(Position p ) { return tiles.get(p); }
  public Unit getUnitAt( Position p ) { return units.get(p); }
  public City getCityAt( Position p ) { return cities.get(p); }
  public Player getPlayerInTurn() { return playerInTurn; }
  public Player getWinner() { return winner; }
  public int getAge() { return age; }

  public boolean moveUnit(Position from, Position to )
  {
    if(getUnitAt(from) == null)
    {
      // No Unit on position "from"
      return false;
    }
    if(getUnitAt(from).getOwner() != getPlayerInTurn())
    {
      // Not this player's turn
      return false;
    }

    Unit unitToMove = getUnitAt(from);

    if(getUnitAt(to) != null){
        if(getUnitAt(from).getOwner() == getUnitAt(to).getOwner()){
            return false;
        }
    }

    units.remove(from);
    units.put(to, unitToMove);
    return true;
  }

  public void endOfTurn()
  {
    age += ageIncrease;
    playerInTurn = (playerInTurn == Player.RED) ? Player.BLUE : Player.RED;

    for(Position position: cities.keySet())
    {
      CityIns theBetterCity = (CityIns)cities.get(position);
      if(theBetterCity.getProduction() != null && !theBetterCity.getProduction().isEmpty())
      {
        if(theBetterCity.getProcessPercentage() >= 100)
        {
          units.put(getFirstAvailableCitySpawn(position), new UnitIns(theBetterCity.getProduction(), theBetterCity.getOwner()));
        }
      }
      theBetterCity.onEndTurn();
    }

    if(age == -3000)
    {
      winner = Player.RED;
    }
  }

  public Position getFirstAvailableCitySpawn(Position pos)
  {
      if(getCityAt(pos) == null) // No city at provided position
      {
          return null;
      }
      List<Position> arrayPos = new ArrayList<>();
      arrayPos.add(pos);
      arrayPos.add(new Position(pos.getRow()+1, pos.getColumn()));
      arrayPos.add(new Position(pos.getRow()+1, pos.getColumn()+1));
      arrayPos.add(new Position(pos.getRow(), pos.getColumn()+1));
      arrayPos.add(new Position(pos.getRow()-1, pos.getColumn()+1));
      arrayPos.add(new Position(pos.getRow()-1, pos.getColumn()));
      arrayPos.add(new Position(pos.getRow()-1, pos.getColumn()-1));
      arrayPos.add(new Position(pos.getRow(), pos.getColumn()-1));
      arrayPos.add(new Position(pos.getRow()+1, pos.getColumn()-1));
      // I'm so sorry for the eye burn
      for (Position thePos : arrayPos)
      {
         arrayPos.removeIf(e -> getUnitAt(thePos) != null);
      }

      if(arrayPos.isEmpty())
      {
          return null;
      }
      return arrayPos.get(0);
  }

  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) { throw new UnsupportedOperationException();}

    public HashMap<Position, Unit> getUnits()
    {
        return units;
    }

    public HashMap<Position, City> getCities()
    {
        return cities;
    }

    public HashMap<Position, Tile> getTiles()
    {
        return tiles;
    }

    public void addCity(Position pos, City theCity)
    {
        cities.put(pos, theCity);
    }

}
