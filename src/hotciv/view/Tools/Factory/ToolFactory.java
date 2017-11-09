package hotciv.view.Tools.Factory;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;

import java.awt.event.MouseEvent;

/**
 * Created by Morten G on 09-11-2017.
 */
public interface ToolFactory
{
    public void produceMouseDownEvent(MouseEvent e, int x, int y);
    public void produceMouseUpEvent(MouseEvent e, int x, int y);
    public void produceMouseDragEvent(MouseEvent e, int x, int y);
    public void produceMouseMoveEvent(MouseEvent e, int x, int y);
    public void buildTools(Game game, DrawingEditor editor);
}
