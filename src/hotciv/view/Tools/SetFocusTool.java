package hotciv.view.Tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/**
 * Created by Morten G on 08-11-2017.
 */
public class SetFocusTool extends NullTool
{
    private Game game;
    private DrawingEditor editor;

    public SetFocusTool(Game game, DrawingEditor editor)
    {
       this.game = game;
       this.editor = editor;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y)
    {
        Position pos = GfxConstants.getPositionFromXY(x, y);
        if(game.getTileAt(pos) != null)
        {
            game.setTileFocus(GfxConstants.getPositionFromXY(x, y));
            editor.showStatus("Focus set on position: " + pos);

            if(game.getUnitAt(GfxConstants.getPositionFromXY(x, y)) != null){
                editor.showStatus("(" + game.getUnitAt(pos).getTypeString() + ", " +
                        game.getUnitAt(pos).getOwner() + ", " + game.getUnitAt(pos).getMoveCount() + ")");

            }

            if(game.getCityAt(pos) != null){
                editor.showStatus("(Owner: " + game.getCityAt(pos).getOwner() + ", size: " + game.getCityAt(pos).getSize() +
                ", production: " + game.getCityAt(pos).getProduction());
            }
        }
    }
}
