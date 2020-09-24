package io.azet.pokemon.common;

import android.graphics.Color;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    private static Map<String, Integer> colorByType = new HashMap<String, Integer>() {{
        put("normal", Color.rgb(168, 168, 120));
        put("fighting", Color.rgb(193, 48,40));
        put("flying", Color.rgb(157, 142, 195));
        put("poison", Color.rgb(151, 68, 147));
        put("ground", Color.rgb(225, 193, 104));
        put("rock", Color.rgb(184, 160, 56));
        put("bug", Color.rgb(169, 185, 32));
        put("ghost", Color.rgb(111, 88, 153));
        put("steel", Color.rgb(183, 184, 208));
        put("fire", Color.rgb(240, 128, 49));
        put("water", Color.rgb(108, 137, 197));
        put("grass", Color.rgb(124, 187, 83));
        put("electric", Color.rgb(249, 209, 50));
        put("psychic", Color.rgb(235, 87, 135));
        put("ice", Color.rgb(154, 211, 121));
        put("dragon", Color.rgb(83, 75, 154));
        put("dark", Color.rgb(113, 89, 73));
        put("fairy", Color.rgb(239, 154, 173));
        put("???", Color.rgb(104, 160, 144));
    }};

    public static int getBackgroundColorByType(String type) {
        return colorByType.get(type) != null ? colorByType.get(type) : Color.rgb(255, 255, 0);
    }

    // https://stackoverflow.com/questions/3942878/how-to-decide-font-color-in-white-or-black-depending-on-background-color
    public static int getFontColorByBackground(int bgColor) {
        return (((0.299 * Color.red(bgColor)) + ((0.587 * Color.green(bgColor)) + (0.114 * Color.blue(bgColor))))) > 186 ? 0xFF000000 : 0xFFFFFFFF;
    }


}
