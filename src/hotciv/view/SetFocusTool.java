package hotciv.view;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/**
 * Created by Morten G on 08-11-2017.
 */
public class SetFocusTool extends NullTool
{
    Game game;
    DrawingEditor editor;

    public SetFocusTool(Game game, DrawingEditor editor)
    {
       this.game = game;
       this.editor = editor;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y)
    {
        if(game.getTileAt(GfxConstants.getPositionFromXY(x, y)) != null)
        {
            game.setTileFocus(GfxConstants.getPositionFromXY(x, y));
            editor.showStatus("Focus set on position: " + GfxConstants.getPositionFromXY(x, y));
        }
    }
}
