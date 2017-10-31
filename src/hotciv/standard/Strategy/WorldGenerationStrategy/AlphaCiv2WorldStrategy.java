package hotciv.standard.Strategy.WorldGenerationStrategy;

import hotciv.standard.Adapter.FractalAdapter;

/**
 * Created by csdev on 10/31/17.
 */
public class AlphaCiv2WorldStrategy implements WorldGenerationStrategy {


    @Override
    public String[] worldDesign() {
        return new FractalAdapter().worldDesign();
    }
}
