package visual;

import hotciv.framework.Game;
import hotciv.view.MapView;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;
import minidraw.standard.StandardDrawing;
import stub.StubGame1;

import javax.swing.*;

/**
 * Show a basic world.
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Computer Science Department
 * Aarhus University
 * <p>
 * This source code is provided WITHOUT ANY WARRANTY either
 * expressed or implied. You may study, use, modify, and
 * distribute it for non-commercial purposes. For any
 * commercial use, see http://www.baerbak.com/
 */
public class ShowWorld
{

    public static void main(String[] args)
    {

        Game game = new StubGame1();

        DrawingEditor editor =
                new MiniDrawApplication("Paint the HotCiv world map...",
                        new HotCivFactory(game));
        editor.open();

        editor.setTool(new NullTool());

    }
}

class HotCivFactory implements Factory
{
    private Game game;

    public HotCivFactory(Game g)
    {
        game = g;
    }

    public DrawingView createDrawingView(DrawingEditor editor)
    {
        DrawingView view =
                new MapView(editor, game);
        return view;
    }

    public Drawing createDrawing(DrawingEditor editor)
    {
        return new StandardDrawing();
    }

    public JTextField createStatusField(DrawingEditor editor)
    {
        return null;
    }
}


