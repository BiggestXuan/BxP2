package biggestxuan.bxp2.effects;

import biggestxuan.bxp2.BxP2;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @Author Biggest_Xuan
 * 2025/4/8
 */
@SuppressWarnings("unused")
public class BxPEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BxP2.MODID);

    public static final RegistryObject<MobEffect> Vulnerability = EFFECTS.register("vulnerability",() -> new BxPEffect(MobEffectCategory.HARMFUL,0x6A0DAD));
    public static final RegistryObject<MobEffect> Debilitated = EFFECTS.register("debilitated",() -> new BxPEffect(MobEffectCategory.HARMFUL,0xFF6347));
    public static final RegistryObject<MobEffect> HalfDamage = EFFECTS.register("half_damage",() -> new BxPEffect(MobEffectCategory.BENEFICIAL,0x98221B));
}
