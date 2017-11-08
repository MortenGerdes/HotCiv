package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.Strategy.AgeingStrategy.AgeingStrategy;
import hotciv.standard.Strategy.AttackingStrategy.AttackingStrategy;
import hotciv.standard.Strategy.Factory.HotcivFactory;
import hotciv.standard.Strategy.TestStubs.DieRollStrategy;
import hotciv.standard.Strategy.UnitPerformStrategy.UnitActionStrategy;
import hotciv.standard.Strategy.WinningStrategy.WinnerStrategy;
import hotciv.standard.Strategy.WorldGenerationStrategy.WorldGenerationStrategy;
import hotciv.standard.Strategy.WorldGenerationStrategy.WorldGenerator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Skeleton implementation of HotCiv.
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p>
 * Please visit http://www.baerbak.com/ for further information.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * This is a change
 * This an important hotfix for release so the customer wont yell at us.
 * This is an even more imporant hotfix.
 */

public class GameImpl implements Game
{
    private int currentRoundNumber = 1;
    private int currentGameAge = -4000; //Initial starting age.
    private Player gameWinner = null;
    private Player playerInTurn = Player.RED;
    private AgeingStrategy ageingStrategy;
    private AttackingStrategy attackingStrategy;
    private WorldGenerationStrategy worldGenerationStrategy;
    private WinnerStrategy winnerStrategy;
    private UnitActionStrategy unitActionStrategy;
    private DieRollStrategy dieRollStrategy;

    private HashMap<Position, Unit> units = new HashMap<>();
    private HashMap<Position, City> cities = new HashMap<>();
    private HashMap<Position, Tile> tiles = new HashMap<>();
    private ArrayList<GameObserver> listeners = new ArrayList<>();

    public HashMap<Player, Integer> killCount = new HashMap<>();

    /**
     * Game initial code goes here.
     */
    public GameImpl(HotcivFactory factory)
    {
        //Assigning strategy classes
        this.dieRollStrategy = factory.produceDieRollStrategy();
        this.ageingStrategy = factory.produceAgeingStrategy();
        this.attackingStrategy = factory.produceAttackingStrategy();
        this.worldGenerationStrategy = factory.produceWorldGenerationStrategy();
        this.winnerStrategy = factory.produceWinnerStrategy();
        this.unitActionStrategy = factory.produceUnitActionStrategy();

        tiles = new WorldGenerator().generateWorld(worldGenerationStrategy.worldDesign());
        units.put(new Position(2, 0), new UnitIns(GameConstants.ARCHER, Player.RED));
        units.put(new Position(4, 3), new UnitIns(GameConstants.SETTLER, Player.RED));
        units.put(new Position(3, 2), new UnitIns(GameConstants.LEGION, Player.BLUE));
    }

    public Tile getTileAt(Position p)
    {
        return tiles.get(p);
    }

    public Unit getUnitAt(Position p)
    {
        return units.get(p);
    }

    public City getCityAt(Position p)
    {
        return cities.get(p);
    }

    public Player getPlayerInTurn()
    {
        return playerInTurn;
    }

    public Player getWinner()
    {
        return gameWinner;
    }

    public int getAge()
    {
        return currentGameAge;
    }

    public boolean moveUnit(Position from, Position to)
    {
        boolean isSelectedUnitNull = getUnitAt(from) == null;
        boolean isItTheRightPlayerInTurn;
        boolean isAShipMovingToLand;

        if (isSelectedUnitNull) // Is the unit null, bailout
        {
            // No Unit on position "from"
            return false;
        }

        isItTheRightPlayerInTurn = getUnitAt(from).getOwner() == getPlayerInTurn();
        if (!isItTheRightPlayerInTurn) // Is it "not" the right player in turn
        {
            // Not this player's turn
            return false;
        }

        Unit unitToMove = getUnitAt(from);
        isAShipMovingToLand = ((UnitIns) unitToMove).isShip() && !getTileAt(to).getTypeString().equals(GameConstants.OCEANS);

        if(!isMoveCountEnough(from, to, unitToMove))
        {
            return false;
        }

        if (isAShipMovingToLand)
        {
            // A ship that's trying to move on land
            return false;
        }

        if (getUnitAt(to) != null)
        {
            if (getUnitAt(from).getOwner() == getUnitAt(to).getOwner())
            {
                return false;
            } else
            {
                // Init attack sequence and update the unit map with who won.
                onWorldChangedEvent(from);
                units = attackingStrategy.attackUnit(dieRollStrategy, this, (HashMap<Position, Unit>) getUnits().clone(), from, to);
                onWorldChangedEvent(to);
                return true;
            }
        }
        onWorldChangedEvent(from);
        moveUnitInMap(to, unitToMove);
        onWorldChangedEvent(to);
        return true;
    }

    /**
     * This is a method that handles every activity upon end turn.
     */
    public void endOfTurn()
    {
        increaseRound();
        increaseAge();
        switchTurnsBetweenPlayers();
        checkIfUnitConquerCity();
        spawnUnitIfCityCan();
        determineWinner();
        onTurnEndsEvent(playerInTurn, getAge());
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance)
    {
    }

    public void changeProductionInCityAt(Position p, String unitType)
    {
    }

    public void performUnitActionAt(Position p)
    {
        if (getUnitAt(p).getOwner() != playerInTurn) return;

        unitActionStrategy.performAction(this, (UnitIns) getUnitAt(p), p);
        onWorldChangedEvent(p);
    }

    @Override
    public void addObserver(GameObserver observer)
    {
        listeners.add(observer);
    }

    @Override
    public void setTileFocus(Position position)
    {
        onTileFocusChangeEvent(position);
    }

    public void increaseKillCount(Player player)
    {
        if (!killCount.containsKey(player))
        {
            killCount.put(player, new Integer(0));
        }
        killCount.put(player, killCount.get(player) + 1);
    }

    public void resetKillCount()
    {
        killCount.clear();
    }

    private void onWorldChangedEvent(Position pos)
    {
        for (GameObserver obs : listeners)
        {
            obs.worldChangedAt(pos);
        }
    }

    private void onTurnEndsEvent(Player nextPlayer, int age)
    {
        for (GameObserver obs : listeners)
        {
            obs.turnEnds(nextPlayer, age);
        }
    }

    private void onTileFocusChangeEvent(Position pos)
    {
        for (GameObserver obs : listeners)
        {
            obs.tileFocusChangedAt(pos);
        }
    }

    private void checkIfUnitConquerCity()
    {
        for (Position posOfCity : getCities().keySet())
        {
            if (getUnitAt(posOfCity) == null)
            {
                continue;
            }
            if (getUnitAt(posOfCity).getOwner() == getCityAt(posOfCity).getOwner())
            {
                continue;
            }
            getCities().put(posOfCity, new CityIns(getUnitAt(posOfCity).getOwner()));
            onWorldChangedEvent(posOfCity);
        }
    }

    private void moveUnitInMap(Position posToMoveTo, Unit unitToMove)
    {
        units.remove(unitToMove);
        units.put(posToMoveTo, unitToMove);
    }

    private void increaseRound()
    {
        currentRoundNumber++;
    }

    private void increaseAge()
    {
        currentGameAge = ageingStrategy.increaseAge(currentGameAge);
    }

    private void switchTurnsBetweenPlayers()
    {
        playerInTurn = (playerInTurn == Player.RED) ? Player.BLUE : Player.RED;
    }

    private void spawnUnitIfCityCan()
    {
        boolean isUnitAShipButNoAdjacentWater;
        UnitIns unitToSpawn;

        for (Position position : cities.keySet())
        {
            CityIns castedCity = (CityIns) cities.get(position);
            castedCity.onEndTurn();
            if (castedCity.getProduction() == null)
            {
                continue;
            }
            if (castedCity.getProduction().isEmpty())
            {
                continue;
            }
            if (castedCity.getProcessPercentage() < 100)
            {
                continue;
            }

            unitToSpawn = new UnitIns(castedCity.getProduction(), castedCity.getOwner());
            isUnitAShipButNoAdjacentWater = unitToSpawn.isShip() && !isCityNextToSpecificTile(position, GameConstants.OCEANS);
            if (isUnitAShipButNoAdjacentWater)
            {
                continue;
            }
            // System.out.println("Spawning unit " + unitToSpawn.getTypeString() + " to " + getFirstAvailbleSpawnAroundCity(position, unitToSpawn.isShip()).toString() + " on tile type " + getTileAt(getFirstAvailbleSpawnAroundCity(position, unitToSpawn.isShip())).getTypeString());
            units.put(getFirstAvailbleSpawnAroundCity(position, unitToSpawn.isShip()), unitToSpawn);
            castedCity.setProduction(null);
            onWorldChangedEvent(position);
        }
    }

    private void determineWinner()
    {
        gameWinner = winnerStrategy.determineWinner(this);
    }

    private boolean isCityNextToSpecificTile(Position posOfCity, String tileType)
    {
        for (Position pos : Utility.get8Neighborhood(posOfCity))
        {
            if (getTileAt(pos).getTypeString().equals(tileType))
            {
                return true;
            }
        }
        return false;
    }

    private Position getFirstAvailbleSpawnAroundCity(Position position, boolean isShip)
    {
        Position posToCheck;
        if (getUnitAt(position) == null && !isShip)
        {
            return position;
        }
        posToCheck = new Position(position.getRow(), position.getColumn() + 1);
        if (getUnitAt(posToCheck) == null && Utility.isWalkableLandTerrain(getTileAt(posToCheck).getTypeString(), isShip))
        {
            return new Position(position.getRow(), position.getColumn() + 1);
        }
        posToCheck = new Position(position.getRow() + 1, position.getColumn() + 1);
        if (getUnitAt(posToCheck) == null && Utility.isWalkableLandTerrain(getTileAt(posToCheck).getTypeString(), isShip))
        {
            return new Position(position.getRow() + 1, position.getColumn() + 1);
        }
        posToCheck = new Position(position.getRow() + 1, position.getColumn());
        if (getUnitAt(posToCheck) == null && Utility.isWalkableLandTerrain(getTileAt(posToCheck).getTypeString(), isShip))
        {
            return new Position(position.getRow() + 1, position.getColumn());
        }
        posToCheck = new Position(position.getRow() + 1, position.getColumn() - 1);
        if (getUnitAt(posToCheck) == null && Utility.isWalkableLandTerrain(getTileAt(posToCheck).getTypeString(), isShip))
        {
            return new Position(position.getRow() + 1, position.getColumn() - 1);
        }
        posToCheck = new Position(position.getRow(), position.getColumn() - 1);
        if (getUnitAt(posToCheck) == null && Utility.isWalkableLandTerrain(getTileAt(posToCheck).getTypeString(), isShip))
        {
            return new Position(position.getRow(), position.getColumn() - 1);
        }
        posToCheck = new Position(position.getRow() - 1, position.getColumn() - 1);
        if (getUnitAt(posToCheck) == null && Utility.isWalkableLandTerrain(getTileAt(posToCheck).getTypeString(), isShip))
        {
            return new Position(position.getRow() - 1, position.getColumn() - 1);
        }
        posToCheck = new Position(position.getRow() - 1, position.getColumn());
        if (getUnitAt(posToCheck) == null && Utility.isWalkableLandTerrain(getTileAt(posToCheck).getTypeString(), isShip))
        {
            return new Position(position.getRow() - 1, position.getColumn());
        }
        posToCheck = new Position(position.getRow() - 1, position.getColumn() + 1);
        if (getUnitAt(posToCheck) == null && Utility.isWalkableLandTerrain(getTileAt(posToCheck).getTypeString(), isShip))
        {
            return new Position(position.getRow() - 1, position.getColumn() + 1);
        }
        return null;
    }

    private boolean isMoveCountEnough(Position toMoveFrom, Position toMoveTo, Unit unit)
    {
        int unitMoveCount = unit.getMoveCount();

        if(Math.abs(toMoveFrom.getColumn() - toMoveTo.getColumn()) > unitMoveCount)
        {
            return false;
        }

        if(Math.abs(toMoveFrom.getRow() - toMoveTo.getRow()) > unitMoveCount)
        {
            return false;
        }

        return true;
    }

    // Getters and Setters for testing purposes
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

    public HashMap<Player, Integer> getKillCount()
    {
        return killCount;
    }

    public int getCurrentRoundNumber()
    {
        return currentRoundNumber;
    }

    public void setAge(int age)
    {
        this.currentGameAge = age;
    }


}
