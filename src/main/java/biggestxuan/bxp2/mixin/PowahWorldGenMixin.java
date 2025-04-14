package biggestxuan.bxp2.mixin;

import dev.architectury.hooks.level.biome.BiomeProperties;
import dev.architectury.registry.level.biome.BiomeModifications;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import owmii.powah.world.gen.WorldGen;

import java.util.function.BiConsumer;

/**
 * @Author Biggest_Xuan
 * 2025/4/11
 */

@Mixin(value = WorldGen.class,remap = false)
public class PowahWorldGenMixin {
    @Redirect(method = "init",at = @At(value = "INVOKE", target = "Ldev/architectury/registry/level/biome/BiomeModifications;addProperties(Ljava/util/function/BiConsumer;)V"))
    private static void __redirect(BiConsumer<BiomeModifications.BiomeContext, BiomeProperties.Mutable> modifier){

    }
}
