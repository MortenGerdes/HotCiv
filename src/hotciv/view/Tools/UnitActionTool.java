package hotciv.view.Tools;

import hotciv.framework.Game;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/**
 * Created by Morten G on 09-11-2017.
 */
public class UnitActionTool extends NullTool
{
    private Game game;
    private DrawingEditor editor;

    public UnitActionTool(Game game, DrawingEditor editor)
    {
        this.game = game;
        this.editor = editor;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y)
    {
        if(!e.isShiftDown())
        {
            return;
        }
        if(editor.drawing().findFigure(x, y) == null)
        {
            return;
        }
        game.performUnitActionAt(GfxConstants.getPositionFromXY(x, y));
    }
}
