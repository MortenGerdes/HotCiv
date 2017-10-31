package hotciv.standard.Adapter;

import hotciv.standard.Strategy.WorldGenerationStrategy.WorldGenerationStrategy;
import hotciv.standard.Strategy.WorldGenerationStrategy.WorldGenerator;
import thirdparty.ThirdPartyFractalGenerator;


/**
 * Created by Morten G on 31-10-2017.
 */
public class FractalAdapter implements WorldGenerationStrategy
{
    ThirdPartyFractalGenerator tfg = new ThirdPartyFractalGenerator();

    @Override
    public String[] worldDesign()
    {
        String line;
        String[] toReturn = new String[16];
        for ( int r = 0; r < 16; r++ )
        {
            line = "";
            for ( int c = 0; c < 16; c++ )
            {
                line = line + tfg.getLandscapeAt(r,c);
            }
            toReturn[r] = line;
        }
        return WorldGenerator.convertToOurConvention(toReturn);
    }
}
