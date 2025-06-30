package biggestxuan.bxp2.integration.MysticalAgriculture;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.blocks.BxPBlocks;
import biggestxuan.bxp2.items.BxPItems;
import com.blakebr0.mysticalagradditions.init.ModBlocks;
import com.blakebr0.mysticalagradditions.init.ModCropTiers;
import com.blakebr0.mysticalagriculture.api.IMysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.MysticalAgriculturePlugin;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import com.blakebr0.mysticalagriculture.api.lib.PluginConfig;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

/**
 * @Author Biggest_Xuan
 * 2025/6/22
 */

@MysticalAgriculturePlugin
public class BxPMysticalAgriculturePlugin implements IMysticalAgriculturePlugin {
    public static final CropTier CROP_TIER_7;
    public static final CropTier CROP_TIER_8;
    public static final CropTier CROP_TIER_9;

    public static final Crop UNSTABLE_BX;
    public static final Crop BX;
    public static final Crop SX;
    public static final Crop POLY;
    public static final Crop ENCH_BX;
    public static final Crop OUMANG;
    public static final Crop OU_GOLD;

    static {
        CROP_TIER_7 = new CropTier(BxP2.RL("7"),7,0x007a7c, ChatFormatting.AQUA);
        CROP_TIER_8 = new CropTier(BxP2.RL("8"),8,0xa000a0, ChatFormatting.DARK_PURPLE);
        CROP_TIER_9 = new CropTier(BxP2.RL("9"),9,0xa00000, ChatFormatting.DARK_RED);

        UNSTABLE_BX = crop("unstable_bx",CropTier.TWO,0x6ee0cd,"bxp2:bx_unstable_ingot", BxPBlocks.UNSTABLE_BX_BLOCK);
        BX = crop("bx",CropTier.FIVE,0x82d22b,"bxp2:bx_ingot",BxPBlocks.BX_BLOCK);
        SX = crop("sx",CropTier.THREE,0xc1e6f5,"bxp2:sx_ingot",BxPBlocks.SX_BLOCK);
        POLY = crop("poly",CropTier.FOUR,0xf3db5d,"bxp2:poly_ingot",BxPBlocks.POLY_BLOCK);
        ENCH_BX = crop("ench_bx",CROP_TIER_8,0xd2fb33,"bxp2:bx_ench_ingot",BxPBlocks.ENCH_BX_BLOCK);
        OUMANG = crop("oumang", ModCropTiers.SIX,0xd3de6b,"bxp2:oumang_ingot",BxPBlocks.OUMANG_BLOCK);
        OU_GOLD = crop("ou_gold",CROP_TIER_7,0x9f13da,"bxp2:ou_gold_ingot",BxPBlocks.OU_GOLD_BLOCK);
    }

    @Override
    public void onRegisterCrops(ICropRegistry registry) {
        registry.register(UNSTABLE_BX);
        registry.register(BX);
        registry.register(SX);
        registry.register(POLY);
        registry.register(ENCH_BX);
        registry.register(OUMANG);
        registry.register(OU_GOLD);
    }

    @Override
    public void configure(PluginConfig config) {
        //config.disableDynamicSeedCraftingRecipes();
        //config.disableDynamicSeedInfusionRecipes();
        config.disableDynamicSeedReprocessingRecipes();
    }

    @Override
    public void onPostRegisterCrops(ICropRegistry registry) {
        CROP_TIER_7.setEssence(BxPItems.EPIC_ESSENCE).setFarmland(ModBlocks.INSANIUM_FARMLAND).setSecondarySeedDrop(false).setFertilizable(false);
        CROP_TIER_8.setEssence(BxPItems.FINAL_ESSENCE).setFarmland(ModBlocks.INSANIUM_FARMLAND).setSecondarySeedDrop(false).setFertilizable(false);
    }

    private static Crop crop(String id, CropTier tier, int color, String name, Supplier<Block> block){
        return new Crop(BxP2.RL(id),tier, CropType.RESOURCE,color, LazyIngredient.item(name)).setCruxBlock(block);
    }
}
