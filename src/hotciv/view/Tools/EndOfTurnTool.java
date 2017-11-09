package hotciv.view.Tools;

import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.GroupFigure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/**
 * Created by csdev on 11/9/17.
 */
public class EndOfTurnTool extends NullTool {

    private Game game;
    private DrawingEditor editor;

    public EndOfTurnTool(Game game, DrawingEditor editor)
    {
        this.game = game;
        this.editor = editor;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y)
    {
        if(x >= GfxConstants.TURN_SHIELD_X && x <= GfxConstants.TURN_SHIELD_X + 26 && y >= GfxConstants.TURN_SHIELD_Y && y <= GfxConstants.TURN_SHIELD_Y + 38)
        {
            game.endOfTurn();
        }
    }
}
