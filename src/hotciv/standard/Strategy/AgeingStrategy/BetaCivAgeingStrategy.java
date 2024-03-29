package hotciv.standard.Strategy.AgeingStrategy;

/**
 * Created by morten on 9/18/17.
 */
public class BetaCivAgeingStrategy implements AgeingStrategy
{

    @Override
    public int increaseAge(int currentAge)
    {
        if (currentAge < -100)
        {
            return currentAge + 100;
        } else if (currentAge == -100)
        {
            return -1;
        } else if (currentAge == -1)
        {
            return 1;
        } else if (currentAge == 1)
        {
            return 50;
        } else if (currentAge >= 50 && currentAge < 1750)
        {
            return currentAge + 50;
        } else if (currentAge >= 1750 && currentAge < 1900)
        {
            return currentAge + 25;
        } else if (currentAge >= 1900 && currentAge < 1970)
        {
            return currentAge + 5;
        } else
        {
            return currentAge + 1;
        }
    }
}
