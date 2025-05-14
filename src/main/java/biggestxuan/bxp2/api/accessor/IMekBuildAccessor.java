package biggestxuan.bxp2.api.accessor;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * @Author Biggest_Xuan
 * 2025/5/13
 */
public interface IMekBuildAccessor {
    void publicBuild(Level world, BlockPos start, boolean empty);
}
