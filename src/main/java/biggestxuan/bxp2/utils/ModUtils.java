package biggestxuan.bxp2.utils;

import net.minecraftforge.fml.ModList;

/**
 * @Author Biggest_Xuan
 * 2025/4/4
 */
public final class ModUtils {
    public static String getAllMods(){
        StringBuilder builder = new StringBuilder();
        for(var mod : ModList.get().getMods()){
            builder.append(mod.getDisplayName());
            builder.append(",");
        }
        return builder.toString();
    }
}
