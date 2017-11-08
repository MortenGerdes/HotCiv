package stub;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

/**
 * Created by csdev on 11/8/17.
 */
public class GameObserverStub implements GameObserver {

    public Position position;

    @Override
    public void worldChangedAt(Position pos) {
        position = pos;
        System.out.println(position.toString());
    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {

    }

    @Override
    public void tileFocusChangedAt(Position position) {

    }
}
