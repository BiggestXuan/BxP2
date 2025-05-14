package biggestxuan.bxp2.mixin;

import biggestxuan.bxp2.api.accessor.IMekBuildAccessor;
import com.jerry.mekanism_extras.common.command.ExtraBuilders;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @Author Biggest_Xuan
 * 2025/5/13
 */

@Mixin(value = ExtraBuilders.NaquadahReactorBuilder.class,remap = false)
public abstract class NaquadahReactorBuilderMixin implements IMekBuildAccessor {
    @Shadow protected abstract void build(Level world, BlockPos start, boolean empty);

    @Override
    public void publicBuild(Level world, BlockPos start, boolean empty) {
        build(world,start,empty);
    }
}
