package hotciv.view;

import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import minidraw.framework.DrawingEditor;
import minidraw.standard.GroupFigure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/**
 * Created by csdev on 11/9/17.
 */
public class EndOfTurnTool extends NullTool {

    Game game;
    DrawingEditor editor;

    public EndOfTurnTool(Game game, DrawingEditor editor)
    {
        this.game = game;
        this.editor = editor;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y)
    {
        if(x >= GfxConstants.TURN_SHIELD_X && x <= 580 && y >= GfxConstants.TURN_SHIELD_Y && y <= 100)
        {
            game.endOfTurn();
        }
    }
}
