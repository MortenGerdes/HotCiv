package hotciv.standard.Strategy.Factory;

import hotciv.standard.Strategy.AgeingStrategy.AgeingStrategy;
import hotciv.standard.Strategy.AgeingStrategy.AlphaCivAgeingStrategy;
import hotciv.standard.Strategy.AttackingStrategy.AttackingStrategy;
import hotciv.standard.Strategy.AttackingStrategy.EpsilonCivAttackingStrategy;
import hotciv.standard.Strategy.TestStubs.DieRollStrategy;
import hotciv.standard.Strategy.TestStubs.FixedDieRollStrategy;
import hotciv.standard.Strategy.UnitPerformStrategy.GammaCivUnitActionStrategy;
import hotciv.standard.Strategy.UnitPerformStrategy.UnitActionStrategy;
import hotciv.standard.Strategy.WinningStrategy.EpsilonCivWinnerStrategy;
import hotciv.standard.Strategy.WinningStrategy.WinnerStrategy;
import hotciv.standard.Strategy.WorldGenerationStrategy.GammaCivWorldAndBelowStrategy;
import hotciv.standard.Strategy.WorldGenerationStrategy.WorldGenerationStrategy;

/**
 * Created by Morten G on 02-10-2017.
 */
public class EpsilonCivFactory implements HotcivFactory
{
    @Override
    public AgeingStrategy produceAgeingStrategy()
    {
        return new AlphaCivAgeingStrategy();
    }

    @Override
    public AttackingStrategy produceAttackingStrategy()
    {
        return new EpsilonCivAttackingStrategy();
    }

    @Override
    public UnitActionStrategy produceUnitActionStrategy()
    {
        return new GammaCivUnitActionStrategy();
    }

    @Override
    public WorldGenerationStrategy produceWorldGenerationStrategy()
    {
        return new GammaCivWorldAndBelowStrategy();
    }

    @Override
    public WinnerStrategy produceWinnerStrategy()
    {
        return new EpsilonCivWinnerStrategy();
    }

    @Override
    public DieRollStrategy produceDieRollStrategy()
    {
        return new FixedDieRollStrategy();
    }
}
