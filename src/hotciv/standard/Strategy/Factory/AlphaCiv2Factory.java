package hotciv.standard.Strategy.Factory;

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
import hotciv.standard.Strategy.WorldGenerationStrategy.AlphaCiv2WorldStrategy;
import hotciv.standard.Strategy.WorldGenerationStrategy.GammaCivWorldAndBelowStrategy;
import hotciv.standard.Strategy.WorldGenerationStrategy.WorldGenerationStrategy;

/**
 * Created by csdev on 10/31/17.
 */
public class AlphaCiv2Factory implements HotcivFactory {

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
        return new AlphaCiv2WorldStrategy();
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
