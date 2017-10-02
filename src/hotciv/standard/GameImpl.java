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

        killCount.put(Player.RED, 0);
        killCount.put(Player.BLUE, 0);
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
        if (getUnitAt(to) != null)
        {
            if (getUnitAt(from).getOwner() == getUnitAt(to).getOwner())
            {
                return false;
            }
            else
            {
                // Init attack sequence and update the unit map with who won.
                units = attackingStrategy.attackUnit(dieRollStrategy, this, (HashMap<Position, Unit>) getUnits().clone(), from, to);
                return true;
            }
        }

        moveUnitInMap(to, unitToMove);
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
    }

    private void checkIfUnitConquerCity()
    {
        for(Position posOfCity: getCities().keySet())
        {
            if(getUnitAt(posOfCity) == null)
            {
                continue;
            }
            if(getUnitAt(posOfCity).getOwner() == getCityAt(posOfCity).getOwner())
            {
                continue;
            }
            getCities().put(posOfCity, new CityIns(getUnitAt(posOfCity).getOwner()));
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
        for (Position position : cities.keySet())
        {
            CityIns castedCity = (CityIns) cities.get(position);
            if (castedCity.getProduction() != null && !castedCity.getProduction().isEmpty())
            { // Nested if-statements due to order of which the code should run.
                if (castedCity.getProcessPercentage() >= 100)
                {
                    units.put(getFirstAvailbleSpawnAroundCity(position), new UnitIns(castedCity.getProduction(), castedCity.getOwner()));
                }
            }
            castedCity.onEndTurn();
        }
    }

    private void determineWinner()
    {
        gameWinner = winnerStrategy.determineWinner(this);
    }

    private Position getFirstAvailbleSpawnAroundCity(Position position)
    {
        if (getUnitAt(position) == null)
        {
            return position;
        } else if (getUnitAt(new Position(position.getRow(), position.getColumn() + 1)) == null)
        {
            return new Position(position.getRow(), position.getColumn() + 1);
        } else if (getUnitAt(new Position(position.getRow() + 1, position.getColumn() + 1)) == null)
        {
            return new Position(position.getRow() + 1, position.getColumn() + 1);
        } else if (getUnitAt(new Position(position.getRow() + 1, position.getColumn())) == null)
        {
            return new Position(position.getRow() + 1, position.getColumn());
        } else if (getUnitAt(new Position(position.getRow() + 1, position.getColumn() - 1)) == null)
        {
            return new Position(position.getRow() + 1, position.getColumn() - 1);
        } else if (getUnitAt(new Position(position.getRow(), position.getColumn() - 1)) == null)
        {
            return new Position(position.getRow(), position.getColumn() - 1);
        } else if (getUnitAt(new Position(position.getRow() - 1, position.getColumn() - 1)) == null)
        {
            return new Position(position.getRow() - 1, position.getColumn() - 1);
        } else if (getUnitAt(new Position(position.getRow() - 1, position.getColumn())) == null)
        {
            return new Position(position.getRow() - 1, position.getColumn());
        } else if (getUnitAt(new Position(position.getRow() - 1, position.getColumn() + 1)) == null)
        {
            return new Position(position.getRow() - 1, position.getColumn() + 1);
        }
        return null;
    }

    // Getters for testing purposes
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

    public HashMap<Player, Integer> getKillCount(){return killCount; }

    public int getCurrentRoundNumber(){return currentRoundNumber;}

    public void setAge(int age)
    {
        this.currentGameAge = age;
    }
}
