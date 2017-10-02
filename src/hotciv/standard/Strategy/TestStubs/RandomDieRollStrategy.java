package hotciv.standard.Strategy.TestStubs;

import java.util.Random;

/**
 * Created by Morten G on 02-10-2017.
 */
public class RandomDieRollStrategy implements DieRollStrategy
{
    Random ran = new Random();

    @Override
    public int outcome1()
    {
        return ran.nextInt();
    }

    @Override
    public int outcome2()
    {
        return ran.nextInt();
    }
}
