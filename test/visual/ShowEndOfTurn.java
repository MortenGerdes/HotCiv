package visual;

import hotciv.framework.Game;
import hotciv.view.Tools.EndOfTurnTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import stub.StubGame2;

/**
 * Template code for exercise FRS 36.42.
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
public class ShowEndOfTurn
{

    public static void main(String[] args)
    {
        Game game = new StubGame2();

        DrawingEditor editor =
                new MiniDrawApplication("Click top shield to end the turn",
                        new HotCivFactory4(game));
        editor.open();
        editor.showStatus("Click to shield to see Game's endOfTurn method being called.");

        // Replace the setting of the tool with your EndOfTurnTool implementation.
        editor.setTool(new EndOfTurnTool(game, editor));
    }
}
