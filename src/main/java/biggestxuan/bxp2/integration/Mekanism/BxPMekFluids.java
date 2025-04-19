package biggestxuan.bxp2.integration.Mekanism;

import biggestxuan.bxp2.BxP2;
import mekanism.common.registration.impl.FluidDeferredRegister;
import mekanism.common.registration.impl.FluidRegistryObject;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.fluids.ForgeFlowingFluid;

/**
 * @Author Biggest_Xuan
 * 2025/4/17
 */
public class BxPMekFluids {
    public static final FluidDeferredRegister FLUID = new FluidDeferredRegister(BxP2.MODID);

    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> NWS = FLUID.registerLiquidChemical(BxPChemicalConstants.NWS);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> UNS = FLUID.registerLiquidChemical(BxPChemicalConstants.UNS);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> CONDENSE_UNS = FLUID.registerLiquidChemical(BxPChemicalConstants.CONDENSE_UNS);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> APH = FLUID.registerLiquidChemical(BxPChemicalConstants.APH);
    public static final FluidRegistryObject<FluidDeferredRegister.MekanismFluidType, ForgeFlowingFluid.Source, ForgeFlowingFluid.Flowing, LiquidBlock, BucketItem> POTASSIUM_FLUORIDE = FLUID.registerLiquidChemical(BxPChemicalConstants.POTASSIUM_FLUORIDE);
}
