package visual;

import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.standard.GameImpl;
import hotciv.standard.Strategy.Factory.SemiCivFactory;
import hotciv.view.Tools.CompTool;
import hotciv.view.Tools.Factory.CompToolFactory;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

/**
 * Created by Morten G on 09-11-2017.
 */
public class ShowSemiCiv
{
    public static void main(String[] args)
    {
        Game game = new GameImpl(new SemiCivFactory());
        DrawingEditor editor =
                new MiniDrawApplication("Click and/or drag any item to see all game actions",
                        new HotCivFactory4(game));
        editor.open();

        // Replace the setting of the tool with your CompositionTool implementation.
        editor.setTool(new CompTool(game, editor, new CompToolFactory()));
    }
}
