package hotciv.view.Tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;
import minidraw.standard.handlers.DragTracker;

import java.awt.event.MouseEvent;

/**
 * Created by Morten G on 08-11-2017.
 */
public class UnitMoveTool extends NullTool
{
    private Game game;
    private DrawingEditor editor;
    private Figure draggedFigure = null;
    private Tool fChild;
    private Tool cachedNullTool;
    private Position fromPos = null;

    public UnitMoveTool(Game game, DrawingEditor editor)
    {
        this.game = game;
        this.editor = editor;
        this.fChild = this.cachedNullTool = new NullTool();
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y)
    {
        editor.drawing().lock();
        if(game.getUnitAt(GfxConstants.getPositionFromXY(x, y)) != null)
        {
            draggedFigure = editor.drawing().findFigure(e.getX(), e.getY());
            if(draggedFigure != null)
            {
                fromPos = GfxConstants.getPositionFromXY(e.getX(), e.getY());
                fChild = createDragTracker(draggedFigure);
            }
            else
            {
                if(!e.isShiftDown()) {
                    editor.drawing().clearSelection();
                }
            }
            fChild = createDragTracker(draggedFigure);
        }
        fChild.mouseDown(e, x, y);
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        editor.drawing().unlock();
        fChild.mouseUp(e, x, y);
        if(draggedFigure == null)
        {
            return;
        }
        game.moveUnit(fromPos, GfxConstants.getPositionFromXY(x, y));
        fChild = cachedNullTool;
        draggedFigure = null;
        fromPos = null;
    }

    public void mouseDrag(MouseEvent e, int x, int y) {
        this.fChild.mouseDrag(e, x, y);
    }

    public void mouseMove(MouseEvent e, int x, int y) {
        this.fChild.mouseMove(e, x, y);
    }


    private Tool createDragTracker(Figure f) {
        return new DragTracker(editor, f);
    }
}
