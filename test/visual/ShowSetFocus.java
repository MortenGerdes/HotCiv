package visual;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.SelectionTool;
import stub.StubGame2;

/**
 * Template code for exercise FRS 36.40.
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
public class ShowSetFocus
{

    public static void main(String[] args)
    {
        Game game = new StubGame2();

        DrawingEditor editor =
                new MiniDrawApplication("Click any tile to set focus",
                        new HotCivFactory4(game));
        editor.open();
        editor.showStatus("Click a tile to see Game's setFocus method being called.");

        // Replace the setting of the tool with your SetFocusTool implementation.
        editor.setTool(new SelectionTool(editor));
    }
}
