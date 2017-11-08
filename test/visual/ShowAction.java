package visual;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;
import stub.StubGame2;

/**
 * Template code for exercise FRS 36.43.
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
public class ShowAction
{

    public static void main(String[] args)
    {
        Game game = new StubGame2();

        DrawingEditor editor =
                new MiniDrawApplication("Shift-Click unit to invoke its action",
                        new HotCivFactory4(game));
        editor.open();
        editor.showStatus("Shift-Click on unit to see Game's performAction method being called.");

        // Replace the setting of the tool with your ActionTool implementation.
        editor.setTool(new NullTool());
    }
}
