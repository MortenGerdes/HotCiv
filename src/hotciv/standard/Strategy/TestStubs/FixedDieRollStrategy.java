package hotciv.standard.Strategy.TestStubs;

/**
 * Created by Morten G on 02-10-2017.
 */
public class FixedDieRollStrategy implements DieRollStrategy
{
    @Override
    public int outcome1()
    {
        return 6;
    }

    @Override
    public int outcome2()
    {
        return 1;
    }
}
