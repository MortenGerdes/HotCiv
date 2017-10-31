package hotciv.Execute;
import hotciv.*;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.Strategy.Factory.AlphaCiv2Factory;
import hotciv.standard.Strategy.Factory.AlphaCivFactory;

/**
 * Created by csdev on 10/31/17.
 */
public class DemoFractalTest {

    public static void main(String args[]){
        Game game = new GameImpl(new AlphaCiv2Factory());
        String line;
        for(int i = 0; i < 16; i++){
            line = "";
            for(int j = 0; j < 16; j++){
                line = line + game.getTileAt(new Position(i,j)).getTypeString().charAt(0);
            }
            System.out.println(line);
        }
    }

}
