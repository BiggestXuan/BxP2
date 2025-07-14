package biggestxuan.bxp2.damage;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Biggest_Xuan
 * 2025/4/8
 */
public class BxPDamage{
    private static final Map<ResourceKey<DamageType>, DamageSource> SOURCES = new HashMap<>();

    public static DamageSource TrueDamage(Level level) {
        return getSource(level, BxPDamageType.TRUE.getKey());
    }

    private static DamageSource getSource(Level level, ResourceKey<DamageType> type) {
        return new DamageSource(
                level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(BxPDamageType.TRUE.getKey())
        );
    }
}