package hotciv.standard.Strategy.Factory;

import hotciv.standard.Strategy.AgeingStrategy.AgeingStrategy;
import hotciv.standard.Strategy.AttackingStrategy.AttackingStrategy;
import hotciv.standard.Strategy.TestStubs.DieRollStrategy;
import hotciv.standard.Strategy.UnitPerformStrategy.UnitActionStrategy;
import hotciv.standard.Strategy.WinningStrategy.WinnerStrategy;
import hotciv.standard.Strategy.WorldGenerationStrategy.WorldGenerationStrategy;

/**
 * Created by Morten G on 02-10-2017.
 */
public interface HotcivFactory
{
    public AgeingStrategy produceAgeingStrategy();
    public AttackingStrategy produceAttackingStrategy();
    public UnitActionStrategy produceUnitActionStrategy();
    public WorldGenerationStrategy produceWorldGenerationStrategy();
    public WinnerStrategy produceWinnerStrategy();
    public DieRollStrategy produceDieRollStrategy();
}
