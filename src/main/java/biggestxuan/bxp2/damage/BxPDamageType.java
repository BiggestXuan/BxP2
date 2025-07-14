package biggestxuan.bxp2.damage;

import biggestxuan.bxp2.BxP2;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * @Author Biggest_Xuan
 * 2025/7/8
 */
public class BxPDamageType {
    public static final DeferredRegister<DamageType> DAMAGE_TYPES = DeferredRegister.create(Registries.DAMAGE_TYPE, BxP2.MODID);
    public static final RegistryObject<DamageType> TRUE = DAMAGE_TYPES.register("true", () -> new DamageType("true", 0.1F));

}
