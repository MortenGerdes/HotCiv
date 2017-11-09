package visual;

import hotciv.framework.Game;
import hotciv.view.Tools.CompTool;
import hotciv.view.Tools.Factory.CompToolFactory;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;
import stub.StubGame2;

/**
 * Template code for exercise FRS 36.44.
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
public class ShowComposition
{

    public static void main(String[] args)
    {
        Game game = new StubGame2();

        DrawingEditor editor =
                new MiniDrawApplication("Click and/or drag any item to see all game actions",
                        new HotCivFactory4(game));
        editor.open();
        editor.showStatus("Click and drag any item to see Game's proper response.");

        // Replace the setting of the tool with your CompositionTool implementation.
        editor.setTool(new CompTool(game, editor, new CompToolFactory()));
    }
}
