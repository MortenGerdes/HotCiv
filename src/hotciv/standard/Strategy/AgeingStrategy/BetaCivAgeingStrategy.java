package hotciv.standard.Strategy.AgeingStrategy;

import hotciv.standard.GameImpl;
import hotciv.standard.Strategy.WinningStrategy.BetaCivWinnerStrategy;

/**
 * Created by morten on 9/18/17.
 */
public class BetaCivAgeingStrategy implements AgeingStrategy
{

    @Override
    public int increaseAge(int currentAge)
    {
        if(currentAge < -100){
            return currentAge + 100;
        }

        if (currentAge == -200) {
            return -100;
        }

        if(currentAge == -100){
            return -1;
        }

        if(currentAge == -1){
            return 1;
        }

        if(currentAge == 1){
            return 50;
        }

        if(currentAge >= 50){
            return currentAge + 50;
        }

        if(currentAge >= 1750 && currentAge <= 1900){
            return currentAge + 25;
        }

        if(currentAge >= 1900 && currentAge >= 1970){
            return currentAge + 5;
        }

        if(currentAge >= 1900){
            return currentAge + 1;
        }

        else return 0;
    }
}
