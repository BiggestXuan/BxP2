package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.integration.TConstruct.Leveling.ToolStackExpand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

/**
 * @Author Biggest_Xuan
 * 2025/5/28
 */

@Mixin(value = ToolStack.class,remap = false)
public abstract class ToolStackMixin implements ToolStackExpand {
    @Shadow protected abstract void setStats(StatsNBT stats);

    @Override
    public void setStatNBT(StatsNBT nbt) {
        setStats(nbt);
    }
}
