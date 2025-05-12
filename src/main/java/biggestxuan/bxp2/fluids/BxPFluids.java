package biggestxuan.bxp2.fluids;

import biggestxuan.bxp2.BxP2;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.tconstruct.fluids.block.BurningLiquidBlock;

/**
 * @Author Biggest_Xuan
 * 2025/4/2
 */

@SuppressWarnings("unused")
public class BxPFluids {
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(BxP2.MODID);

    public static final FlowingFluidObject<ForgeFlowingFluid> MOLTEN_UNSTABLE_BX = register("molten_unstable_bx");
    public static final FlowingFluidObject<ForgeFlowingFluid> BX = register("molten_bx");
    public static final FlowingFluidObject<ForgeFlowingFluid> ENCH_BX = register("molten_ench_bx");
    public static final FlowingFluidObject<ForgeFlowingFluid> SUPER_BX = register("molten_super_bx");
    public static final FlowingFluidObject<ForgeFlowingFluid> DRACONIUM = register("molten_draconium");
    public static final FlowingFluidObject<ForgeFlowingFluid> AWAKENED_DRACONIUM = register("molten_awakened_draconium");
    public static final FlowingFluidObject<ForgeFlowingFluid> WYVERN_DRACONIUM = register("molten_wyvern_draconium");
    public static final FlowingFluidObject<ForgeFlowingFluid> CHAOTIC_DRACONIUM = register("molten_chaotic_draconium");
    public static final FlowingFluidObject<ForgeFlowingFluid> POLY = register("molten_poly");
    public static final FlowingFluidObject<ForgeFlowingFluid> UmbraAmethystBrass = register("molten_umbra_amethyst_brass");
    public static final FlowingFluidObject<ForgeFlowingFluid> STAINLESS_STEEL = register("molten_stainless_steel");
    public static final FlowingFluidObject<ForgeFlowingFluid> Seismite = register("molten_seismite");
    public static final FlowingFluidObject<ForgeFlowingFluid> Necrosilver= register("molten_necrosilver");
    public static final FlowingFluidObject<ForgeFlowingFluid> MANA_STEEL= register("molten_mana_steel");
    public static final FlowingFluidObject<ForgeFlowingFluid> TERRA_STEEL= register("molten_terra_steel");
    public static final FlowingFluidObject<ForgeFlowingFluid> ELEMENTIUM_STEEL= register("molten_elementium_steel");
    public static final FlowingFluidObject<ForgeFlowingFluid> PLAGUE_ESSENCE= register("plague_essence");
    public static final FlowingFluidObject<ForgeFlowingFluid> PLAGUE_METAL= register("molten_plague_metal");
    public static final FlowingFluidObject<ForgeFlowingFluid> OUHUANG= register("molten_ouhuang");
    public static final FlowingFluidObject<ForgeFlowingFluid> BLOOD_IRON = register("molten_blood_iron");
    public static final FlowingFluidObject<ForgeFlowingFluid> OMITE = register("molten_omite");

    private static FluidType.Properties hot(String name) {
        return FluidType.Properties.create().density(2000).viscosity(10000).temperature(1000).descriptionId(BxP2.makeDescriptionId("fluid", name)).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA).sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA).motionScale(0.0023333333333333335).canSwim(false).canDrown(false).pathType(BlockPathTypes.LAVA).adjacentPathType(null);
    }

    private static FlowingFluidObject<ForgeFlowingFluid> register(String name) {
        return FLUIDS.register(name).type(hot(name)).block(BurningLiquidBlock.createBurning(MapColor.COLOR_LIGHT_BLUE,3,10,2F)).bucket().flowing();
    }
}
