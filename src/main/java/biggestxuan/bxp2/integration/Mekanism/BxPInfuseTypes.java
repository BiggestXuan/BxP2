package biggestxuan.bxp2.integration.Mekanism;

import biggestxuan.bxp2.BxP2;
import mekanism.api.chemical.infuse.InfuseType;
import mekanism.common.registration.impl.InfuseTypeDeferredRegister;
import mekanism.common.registration.impl.InfuseTypeRegistryObject;

/**
 * @Author Biggest_Xuan
 * 2025/4/16
 */
public class BxPInfuseTypes {
    public static final InfuseTypeDeferredRegister INFUSE_TYPES = new InfuseTypeDeferredRegister(BxP2.MODID);

    public static final InfuseTypeRegistryObject<InfuseType> BX = INFUSE_TYPES.register("bx",0x5AE4E6);
    public static final InfuseTypeRegistryObject<InfuseType> OBSIDIAN = INFUSE_TYPES.register("obsidian",0x6600CC);
}
