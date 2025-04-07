package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.utils.Utils;
import com.lowdragmc.mbd2.utils.EnergyFormattingUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * @Author Biggest_Xuan
 * 2025/4/6
 */

@Mixin(value = EnergyFormattingUtil.class,remap = false)
public abstract class EnergyFormattingUtilMixin {
    /**
     * @author Biggest_Xuan
     * @reason No reason
     */
    @Overwrite
    public static String formatExtended(long number) {
        return Utils.thousandSign(number);
    }

    /**
     * @author Biggest_Xuan
     * @reason No reason
     */
    @Overwrite
    public static String formatCompact(long number){
        return Utils.KMT(String.valueOf(number));
    }
}
