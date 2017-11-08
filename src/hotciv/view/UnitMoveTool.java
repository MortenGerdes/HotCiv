package hotciv.view;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

/**
 * Created by Morten G on 08-11-2017.
 */
public class UnitMoveTool extends NullTool
{
    private Game game;
    private DrawingEditor editor;

    public UnitMoveTool(Game game, DrawingEditor editor)
    {
        this.game = game;
        this.editor = editor;
    }
}
