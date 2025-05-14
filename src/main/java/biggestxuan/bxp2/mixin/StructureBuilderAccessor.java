package biggestxuan.bxp2.mixin;

import mekanism.common.command.builders.StructureBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * @Author Biggest_Xuan
 * 2025/5/13
 */

@Mixin(value = StructureBuilder.class,remap = false)
public interface StructureBuilderAccessor {
    @Invoker("build")
    void build(Level world, BlockPos start, boolean empty);
}
