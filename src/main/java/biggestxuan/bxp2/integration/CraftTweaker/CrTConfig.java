package biggestxuan.bxp2.integration.CraftTweaker;

import biggestxuan.bxp2.Config;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import org.openzen.zencode.java.ZenCodeType;

/**
 * @Author Biggest_Xuan
 * 2025/5/6
 */

@ZenRegister
@ZenCodeType.Name("mods.bxp2.CrTConfig")
@SuppressWarnings("unused")
public class CrTConfig {
    @ZenCodeType.Method
    public static float getOuhuangChance(){
        return switch (Config.difficulty){
            case 1 -> (float) Config.EASY_OUHUANG_INGOT_CHANCE;
            case 2 -> (float) Config.NORMAL_OUHUANG_INGOT_CHANCE;
            default -> (float) Config.HARD_OUHUANG_INGOT_CHANCE;
        };
    }
}
