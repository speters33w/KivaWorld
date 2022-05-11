package kivaworld;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum FloorMapObject {
    EMPTY(" "),
    OBSTACLE("|-*"),
    POD("P"),
    DROP_ZONE("D");

    private static Map<Character, FloorMapObject> charToEnum = registerCharMappings();
    private final String validRepresentations;

    private FloorMapObject(String validCharRepresentations) {
        this.validRepresentations = validCharRepresentations;
    }

    public static Optional<FloorMapObject> fromChar(char symbol) {
        return Optional.ofNullable(charToEnum.get(symbol));
    }

    private static Map<Character, FloorMapObject> registerCharMappings() {
        Map<Character, FloorMapObject> mapping = new HashMap();
        FloorMapObject[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            FloorMapObject val = var1[var3];

            for(int i = 0; i < val.validRepresentations.length(); ++i) {
                mapping.put(val.validRepresentations.charAt(i), val);
            }
        }

        return mapping;
    }

    public char toChar() {
        return this.validRepresentations.charAt(0);
    }
}

