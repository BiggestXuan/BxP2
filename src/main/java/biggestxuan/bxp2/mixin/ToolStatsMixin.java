package biggestxuan.bxp2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

/**
 * @Author Biggest_Xuan
 * 2025/4/5
 */

@Mixin(ToolStats.class)
public class ToolStatsMixin {
    @ModifyConstant(method = "<clinit>", constant = {@Constant(floatValue = 2048.0F), @Constant(floatValue = 1024.0F), @Constant(floatValue = 30.0F), @Constant(floatValue = 20.0F), @Constant(floatValue = 2.1474836E9F)})
    private static float modifyMaxValue(float constant) {
        return Float.POSITIVE_INFINITY;
    }
}
