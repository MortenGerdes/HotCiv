package hotciv.standard.Strategy.WorldGenerationStrategy;

/**
 * Created by csdev on 10/13/17.
 */
public class TestStubWorldStrategy implements WorldGenerationStrategy
{

    @Override
    public String[] worldDesign()
    {
        return new String[]{
                "oooooooooooooooo",
                "oopooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooopppooooooo",
                "oooooopppooooooo",
                "oooooopppooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
        }; //(6,8) middle of big island
        //(1,2) mini island
    }
}
