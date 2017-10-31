package hotciv.standard.Strategy.Factory;

import hotciv.standard.Adapter.FractalAdapter;
import hotciv.standard.Strategy.AgeingStrategy.AgeingStrategy;
import hotciv.standard.Strategy.AgeingStrategy.AlphaCivAgeingStrategy;
import hotciv.standard.Strategy.AttackingStrategy.AlphaCivAttackingStrategy;
import hotciv.standard.Strategy.AttackingStrategy.AttackingStrategy;
import hotciv.standard.Strategy.TestStubs.DieRollStrategy;
import hotciv.standard.Strategy.TestStubs.FixedDieRollStrategy;
import hotciv.standard.Strategy.UnitPerformStrategy.BetaCivAndBelowUnitActionStrategy;
import hotciv.standard.Strategy.UnitPerformStrategy.UnitActionStrategy;
import hotciv.standard.Strategy.WinningStrategy.AlphaCivWinnerStrategy;
import hotciv.standard.Strategy.WinningStrategy.WinnerStrategy;
import hotciv.standard.Strategy.WorldGenerationStrategy.DeltaCivWorldStrategy;
import hotciv.standard.Strategy.WorldGenerationStrategy.WorldGenerationStrategy;

/**
 * Created by Morten G on 02-10-2017.
 */
public class DeltaCivFactory implements HotcivFactory
{
    @Override
    public AgeingStrategy produceAgeingStrategy()
    {
        return new AlphaCivAgeingStrategy();
    }

    @Override
    public AttackingStrategy produceAttackingStrategy()
    {
        return new AlphaCivAttackingStrategy();
    }

    @Override
    public UnitActionStrategy produceUnitActionStrategy()
    {
        return new BetaCivAndBelowUnitActionStrategy();
    }

    @Override
    public WorldGenerationStrategy produceWorldGenerationStrategy()
    {
        return new DeltaCivWorldStrategy();
    }

    @Override
    public WinnerStrategy produceWinnerStrategy()
    {
        return new AlphaCivWinnerStrategy();
    }

    @Override
    public DieRollStrategy produceDieRollStrategy()
    {
        return new FixedDieRollStrategy();
    }
}
