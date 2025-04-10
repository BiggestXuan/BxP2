package biggestxuan.bxp2.utils;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.Config;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

/**
 * @Author Biggest_Xuan
 * 2025/4/8
 */
public final class DifficultyUtils {
    public static int getDifficulty(){
        return Config.difficulty;
    }

    public static MutableComponent getDifficultyColor(int i){
        String name = "";
        int color = 0;
        switch (i){
            case 1 -> name = "bxp2.screen.difficultyeasy_0";
            case 2 -> name = "bxp2.screen.difficultynormal_0";
            case 3 -> name = "bxp2.screen.difficultyhard_0";
        }
        MutableComponent c = BxP2.tr(name);
        switch (i){
            case 1 -> color = 111111;
            case 2 -> color = 0xFF9933;
            case 3 -> color = 0xD52121;
        }
        c.setStyle(Style.EMPTY.withColor(color));
        return c;
    }

    public static MutableComponent getDifficultyColor(){
        return getDifficultyColor(getDifficulty());
    }
}
