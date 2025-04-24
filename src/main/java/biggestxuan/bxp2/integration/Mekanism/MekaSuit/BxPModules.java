package biggestxuan.bxp2.integration.Mekanism.MekaSuit;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.integration.Mekanism.MekaSuit.ModuleInit.ChaosProtectUnit;
import biggestxuan.bxp2.integration.Mekanism.MekaSuit.ModuleInit.SunProtectUnit;
import biggestxuan.bxp2.items.BxPItems;
import mekanism.common.registration.impl.ModuleDeferredRegister;
import mekanism.common.registration.impl.ModuleRegistryObject;

/**
 * @Author Biggest_Xuan
 * 2025/4/19
 */
public class BxPModules {
    public static final ModuleDeferredRegister MODULES = new ModuleDeferredRegister(BxP2.MODID);

    public static final ModuleRegistryObject<SunProtectUnit> SUN_PROTECT = MODULES.register("sun_protect_module",
            SunProtectUnit::new, BxPItems.SUN_PROTECT_MODULE::get, buider -> buider.maxStackSize(1).noDisable()
    );
    public static final ModuleRegistryObject<ChaosProtectUnit> CHAOS_PROTECT = MODULES.register("chaos_protect_module",
            ChaosProtectUnit::new, BxPItems.CHAOS_PROTECT_MODULE::get, buider -> buider.maxStackSize(1).noDisable()
    );
}
