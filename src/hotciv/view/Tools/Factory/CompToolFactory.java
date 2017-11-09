package hotciv.view.Tools.Factory;

import hotciv.framework.Game;
import hotciv.view.Tools.EndOfTurnTool;
import hotciv.view.Tools.SetFocusTool;
import hotciv.view.Tools.UnitActionTool;
import hotciv.view.Tools.UnitMoveTool;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Morten G on 09-11-2017.
 */
public class CompToolFactory implements ToolFactory
{
    ArrayList<Tool> tools = new ArrayList<>();

    @Override
    public void produceMouseDownEvent(MouseEvent e, int x, int y)
    {
        for(Tool tool: tools)
        {
            tool.mouseDown(e, x ,y);
        }
    }

    @Override
    public void produceMouseUpEvent(MouseEvent e, int x, int y)
    {
        for(Tool tool: tools)
        {
            tool.mouseUp(e, x ,y);
        }
    }

    @Override
    public void produceMouseDragEvent(MouseEvent e, int x, int y)
    {
        for(Tool tool: tools)
        {
            tool.mouseDrag(e, x ,y);
        }
    }

    @Override
    public void produceMouseMoveEvent(MouseEvent e, int x, int y)
    {
        for(Tool tool: tools)
        {
            tool.mouseMove(e, x ,y);
        }
    }

    @Override
    public void buildTools(Game game, DrawingEditor editor)
    {
        tools.clear();
        tools.add(new EndOfTurnTool(game, editor));
        tools.add(new UnitActionTool(game, editor));
        tools.add(new UnitMoveTool(game, editor));
        tools.add(new SetFocusTool(game, editor));
    }
}
