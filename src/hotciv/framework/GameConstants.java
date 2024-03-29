package hotciv.framework;

import java.util.HashMap;

/**
 * Collection of constants used in HotCiv Game. Note that strings are
 * used instead of enumeration types to keep the set of valid
 * constants open to extensions by future HotCiv variants.  Enums can
 * only be changed by compile time modification.
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p>
 * Please visit http://www.baerbak.com/ for further information.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class GameConstants
{
    // The size of the world is set permanently to a 16x16 grid
    public static final int WORLDSIZE = 16;
    // Valid unit types
    public static final String ARCHER = "archer";
    public static final String LEGION = "legion";
    public static final String SETTLER = "settler";
    public static final String GALLEY = "galley";
    // Valid terrain types
    public static final String PLAINS = "plains";
    public static final String OCEANS = "ocean";
    public static final String FOREST = "forest";
    public static final String HILLS = "hills";
    public static final String MOUNTAINS = "mountain";
    public static final String CITY = "city";
    // Valid production balance types
    public static final String productionFocus = "hammer";
    public static final String foodFocus = "apple";

    public static final HashMap<String, Integer> PRICE_ON_UNIT;
    public static final HashMap<String, Integer> DEFAULT_MOVECOUNT;
    static
    {
        PRICE_ON_UNIT = new HashMap<>();
        DEFAULT_MOVECOUNT = new HashMap<>();
        PRICE_ON_UNIT.put(ARCHER, 10);
        PRICE_ON_UNIT.put(LEGION, 15);
        PRICE_ON_UNIT.put(SETTLER, 30);
        PRICE_ON_UNIT.put(GALLEY, 30);

        DEFAULT_MOVECOUNT.put(ARCHER, 1);
        DEFAULT_MOVECOUNT.put(LEGION, 1);
        DEFAULT_MOVECOUNT.put(SETTLER, 1);
        DEFAULT_MOVECOUNT.put(GALLEY, 2);
    }
}
