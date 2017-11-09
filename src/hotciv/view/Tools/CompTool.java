package hotciv.view.Tools;

import hotciv.framework.Game;
import hotciv.view.Tools.Factory.ToolFactory;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/**
 * Created by Morten G on 09-11-2017.
 */
public class CompTool extends NullTool
{
    private Game game;
    private DrawingEditor editor;
    private ToolFactory factory;

    public CompTool(Game game, DrawingEditor editor, ToolFactory factory)
    {
        this.game = game;
        this.editor = editor;
        this.factory = factory;

        factory.buildTools(game, editor);
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y)
    {
        factory.produceMouseDownEvent(e, x, y);
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y)
    {
        factory.produceMouseDragEvent(e, x, y);
    }

    @Override
    public void mouseMove(MouseEvent e, int x, int y)
    {
        factory.produceMouseMoveEvent(e, x, y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y)
    {
        factory.produceMouseUpEvent(e, x, y);
    }
}
