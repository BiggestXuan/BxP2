package biggestxuan.bxp2.items;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.blocks.BxPBlocks;
import biggestxuan.bxp2.integration.DraconicEvolution.BreakShieldItem;
import biggestxuan.bxp2.integration.DraconicEvolution.DamageItem;
import biggestxuan.bxp2.integration.DraconicEvolution.KillCrystalItem;
import biggestxuan.bxp2.integration.Mekanism.MekaSuit.BxPModules;
import biggestxuan.bxp2.integration.Mekanism.MekaSuit.ModuleItem.ChaosProtectItem;
import biggestxuan.bxp2.integration.Mekanism.MekaSuit.ModuleItem.SunProtectItem;
import biggestxuan.bxp2.integration.Thermal.UpgradeItem;
import de.teamlapen.vampirism.items.VampirismItemBloodFoodItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/4/1
 */

@SuppressWarnings("unused")
public class BxPItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BxP2.MODID);
    public static final RegistryObject<Item> CAIGENGZI = ITEMS.register("caigengzi",() -> new BxPItem.BxPFoodItem(2,0.2F));
    public static final RegistryObject<Item> BX_UNSTABLE_INGOT = ITEMS.register("bx_unstable_ingot",() -> new BxPItem(4));
    public static final RegistryObject<Item> BX_INGOT = ITEMS.register("bx_ingot", () -> new BXCycleBaseItem(75));
    public static final RegistryObject<Item> BX_ENCH_INGOT = ITEMS.register("bx_ench_ingot",() -> new BxPItem(Rarity.RARE){
        public int getBXValue(ItemStack stack) {
            return 900;
        }
    });
    public static final RegistryObject<Item> BX_SUPER_INGOT = ITEMS.register("bx_super_ingot",() -> new BxPItem(Rarity.EPIC){
        public int getBXValue(ItemStack stack) {
            return 30000;
        }
    });
    public static final RegistryObject<Item> SX_INGOT = ITEMS.register("sx_ingot",() -> new BxPItem(25));
    public static final RegistryObject<Item> BX_GOLD_INGOT = ITEMS.register("bx_gold_ingot",() -> new BxPItem(1){
        @Override
        public void appendHoverText(ItemStack p_41421_, @org.jetbrains.annotations.Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
            super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
            p_41423_.add(BxP2.tr("tooltip.bx_gold_ingot"));
        }
    });
    public static final RegistryObject<Item> SDBZ = ITEMS.register("sdbz",() -> new BxPItem(160));
    public static final RegistryObject<Item> ENCH_SDBZ = ITEMS.register("ench_sdbz",() -> new BxPItem(1500){
        @Override
        public boolean isFoil(ItemStack p_41453_) {
            return true;
        }
    });
    public static final RegistryObject<Item> OUMANG_INGOT = ITEMS.register("oumang_ingot",() -> new BxPItem(42));
    public static final RegistryObject<Item> OUHUANG_INGOT = ITEMS.register("ouhuang_ingot",() -> new BxPItem(400){
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
    });
    public static final RegistryObject<Item> OU_GOLD_INGOT = ITEMS.register("ou_gold_ingot",() -> new BxPItem(100));
    public static final RegistryObject<Item> CAI_SEED = ITEMS.register("cai_seed",() -> new BxPItem(5));
    public static final RegistryObject<Item> ENCH_CAI_SEED = ITEMS.register("ench_cai_seed",() -> new BxPItem(330){
        @Override
        public boolean isFoil(ItemStack p_41453_) {
            return true;
        }
    });
    public static final RegistryObject<Item> ENRICHED_BX = ITEMS.register("enriched_bx",BxPItem::new);
    public static final RegistryObject<Item> COPPER_CHLORIDE = ITEMS.register("copper_chloride", BxPItem::new);
    public static final RegistryObject<Item> CONDENSE_URANIUM = ITEMS.register("condense_uranium", BxPItem::new);
    public static final RegistryObject<Item> POTASSIUM = ITEMS.register("potassium", BxPItem::new);
    public static final RegistryObject<Item> DIFFICULTY_CHANGE = ITEMS.register("difficulty_change",DifficultyChangeItem::new);
    public static final RegistryObject<Item> SUN_PROTECT_MODULE = ITEMS.register("sun_protect_module", () -> new SunProtectItem(BxPModules.SUN_PROTECT));
    public static final RegistryObject<Item> CHAOS_PROTECT_MODULE = ITEMS.register("chaos_protect_module", () -> new ChaosProtectItem(BxPModules.CHAOS_PROTECT));
    public static final RegistryObject<Item> MONEY = ITEMS.register("money", MoneyItem::new);
    public static final RegistryObject<Item> MEANING_LESS = ITEMS.register("meaning_less", BxPItem::new);
    public static final RegistryObject<Item> DISCOUNT_CARD = ITEMS.register("discount_card", () -> new DiscountCardItem(0.7F));
    public static final RegistryObject<Item> SHIELD_SCROLL = ITEMS.register("chaos_shield_scroll", BreakShieldItem::new);
    public static final RegistryObject<Item> DRACONIC_HEALTH_SCROLL = ITEMS.register("chaos_health_scroll", DamageItem::new);
    public static final RegistryObject<Item> CRYSTAL_SCROLL = ITEMS.register("chaos_crystal_scroll", KillCrystalItem::new);
    public static final RegistryObject<Item> BX_UPGRADE = ITEMS.register("bx_upgrade",() -> new UpgradeItem(18f));
    public static final RegistryObject<Item> OUMANG_UPGRADE = ITEMS.register("oumang_upgrade",() -> new UpgradeItem(32f));
    public static final RegistryObject<Item> OU_GOLD_UPGRADE = ITEMS.register("ou_gold_upgrade",() -> new UpgradeItem(48f));
    public static final RegistryObject<Item> OU_HUANG_UPGRADE = ITEMS.register("ouhuang_upgrade",() -> new UpgradeItem(128f));
    public static final RegistryObject<Item> WYVERN_UPGRADE = ITEMS.register("wyvern_upgrade",() -> new UpgradeItem(192f));
    public static final RegistryObject<Item> ENCH_BX_UPGRADE = ITEMS.register("ench_bx_upgrade",() -> new UpgradeItem(256){
        @Override
        public boolean isFoil(ItemStack p_41453_) {
            return true;
        }
    });
    public static final RegistryObject<Item> DRACONIUM_UPGRADE = ITEMS.register("draconium_upgrade",() -> new UpgradeItem(384f));
    public static final RegistryObject<Item> CHAOTIC_UPGRADE = ITEMS.register("chaotic_upgrade",() -> new UpgradeItem(512f));
    public static final RegistryObject<Item> FINAL_UPGRADE = ITEMS.register("final_upgrade",() -> new UpgradeItem(1024f));
    public static final RegistryObject<Item> POLY_INGOT = ITEMS.register("poly_ingot",() -> new BxPItem(37){
        @Override
        public Rarity getRarity(ItemStack p_41461_) {
            return Rarity.UNCOMMON;
        }
    });
    public static final RegistryObject<Item> UmbraAmethystBrass = ITEMS.register("umbra_amethyst_brass_ingot",() -> new BxPItem(20));
    public static final RegistryObject<Item> STAINLESS_STEEL_INGOT = ITEMS.register("stainless_steel_ingot",() -> new BxPItem(9));
    public static final RegistryObject<Item> Seismite_INGOT = ITEMS.register("seismite_ingot",() -> new BxPItem(26));
    public static final RegistryObject<Item> Necrosilver_INGOT = ITEMS.register("necrosilver_ingot",() -> new BxPItem(7));
    public static final RegistryObject<Item> PLAGUE_METAL = ITEMS.register("plague_metal",BxPItem::new);
    public static final RegistryObject<Item> AUTO_BUILD = ITEMS.register("auto_build",AutoBuildItem::new);
    public static final RegistryObject<Item> WYVERN_INGOT = ITEMS.register("wyvern_ingot",BxPItem::new);
    public static final RegistryObject<Item> DRACONIUM_INGOT = ITEMS.register("draconium_ingot",BxPItem::new);
    public static final RegistryObject<Item> CHAOTIC_INGOT = ITEMS.register("chaotic_ingot",() -> new BxPItem(){
        @Override
        public boolean isFoil(ItemStack p_41453_) {
            return true;
        }
    });
    public static final RegistryObject<Item> OMITE_INGOT = ITEMS.register("omite_ingot",BxPItem::new);
    public static final RegistryObject<Item> RADIATION_INGOT = ITEMS.register("radiation_ingot",() -> new BxPItem(new Item.Properties().rarity(Rarity.UNCOMMON)){
        @Override
        public int getBXValue(ItemStack stack) {
            return 105;
        }
    });
    public static final RegistryObject<Item> mithrillium_ingot = ITEMS.register("mithrillium_ingot", WaitingItem.ThuamcraftItem::new);
    public static final RegistryObject<Item> adaminite_ingot = ITEMS.register("adaminite_ingot",WaitingItem.ThuamcraftItem::new);
    public static final RegistryObject<Item> mithminite_ingot = ITEMS.register("mithminite_ingot",WaitingItem.ThuamcraftItem::new);

    public static final RegistryObject<Item> BXSHOP = ITEMS.register("bx_shop", BxPItem.BXShopItem::new);
    public static final RegistryObject<Item> MEKA_CUBE = ITEMS.register("meka_cube",() -> new BxPItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> XP_GEM = ITEMS.register("xp_gem",BxPItem::new);
    public static final RegistryObject<Item> XP_LIMIT_GEM = ITEMS.register("xp_limit_gem",BxPItem::new);
    public static final RegistryObject<Item> BLOOD_SAC = ITEMS.register("blood_sac",() -> new VampirismItemBloodFoodItem(new FoodProperties.Builder().nutrition(6).saturationMod(0.2F).build(),new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).build()));
    public static final RegistryObject<Item> CREATIVE_ABILITY_SKULL = ITEMS.register("creative_ability_skull",BxPItem::new);
    public static final RegistryObject<Item> CREATIVE_UPGRADE_SKULL = ITEMS.register("creative_upgrade_skull",BxPItem::new);
    public static final RegistryObject<Item> TOUGHNESS_PLATE = ITEMS.register("toughness_plate",BxPItem::new);
    public static final RegistryObject<Item> KNOCKBACK_PLATE = ITEMS.register("knockback_plate",BxPItem::new);
    public static final RegistryObject<Item> EPIC_ESSENCE = ITEMS.register("epic_essence",() -> new BxPItem(){
        @Override
        public boolean isFoil(ItemStack p_41453_) {
            return true;
        }
    });
    public static final RegistryObject<Item> FINAL_ESSENCE = ITEMS.register("final_essence",() -> new BxPItem(){
        @Override
        public boolean isFoil(ItemStack p_41453_) {
            return true;
        }
    });
    public static final RegistryObject<Item> UNSTABLE_BX_BLOCK = registryBlock("unstable_bx_block",BxPBlocks.UNSTABLE_BX_BLOCK);
    public static final RegistryObject<Item> BX_BLOCK = registryBlock("bx_block",BxPBlocks.BX_BLOCK);
    public static final RegistryObject<Item> OUMANG_BLOCK = registryBlock("oumang_block",BxPBlocks.OUMANG_BLOCK);
    public static final RegistryObject<Item> OU_GOLD_BLOCK = registryBlock("ou_gold_block",BxPBlocks.OU_GOLD_BLOCK);
    public static final RegistryObject<Item> SX_BLOCK = registryBlock("sx_block",BxPBlocks.SX_BLOCK);
    public static final RegistryObject<Item> POLY_BLOCK = registryBlock("poly_block",BxPBlocks.POLY_BLOCK);
    public static final RegistryObject<Item> ENCH_BX_BLOCK = registryBlock("ench_bx_block",BxPBlocks.ENCH_BX_BLOCK);
    public static final RegistryObject<Item> YUNXI = registryPlayerModelBlock("yunxi", BxPBlocks.YUNXI);
    public static final RegistryObject<Item> BIGGEST_XUAN = registryPlayerModelBlock("biggest_xuan", BxPBlocks.BIGGEST_XUAN);
    public static final RegistryObject<Item> DCTOR_0415 = registryPlayerModelBlock("dctor_0415", BxPBlocks.DCTOR_0415);
    public static final RegistryObject<Item> TULYE = registryPlayerModelBlock("tulye", BxPBlocks.TULYE);
    public static final RegistryObject<Item> JAOXAONO = registryPlayerModelBlock("jaoxaono", BxPBlocks.JAOXAONO);
    public static final RegistryObject<Item> LAMB_KISARA = registryPlayerModelBlock("lamb_kisara", BxPBlocks.LAMB_KISARA);
    public static final RegistryObject<Item> SDXHOP = registryPlayerModelBlock("sdxhop", BxPBlocks.SDXHOP);
    public static final RegistryObject<Item> MCAIGENGZI = registryPlayerModelBlock("mcaigengzi", BxPBlocks.CAIGENGZI);
    public static final RegistryObject<Item> YULUO_1 = registryPlayerModelBlock("yuluo_1", BxPBlocks.YULUO_1);
    public static final RegistryObject<Item> ABUNANA = registryPlayerModelBlock("abunana", BxPBlocks.ABUNANA);
    public static final RegistryObject<Item> KLPZM = registryPlayerModelBlock("klpzm", BxPBlocks.KLPZM);
   // public static final RegistryObject<Item> CLSHERLOCK = registryPlayerModelBlock("clsherlock", BxPBlocks.CLSHERLOCK);

    private static RegistryObject<Item> registryBlock(String name, RegistryObject<Block> block){
        return ITEMS.register(name,() -> new BlockItem(block.get(),new Item.Properties()));
    }

    private static RegistryObject<Item> registryPlayerModelBlock(String name, RegistryObject<Block> block){
        return ITEMS.register(name,() -> new BlockItem(block.get(),new Item.Properties()){
            @Override
            public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
                p_41423_.add(BxP2.tr("bxp2.message.yuda").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_AQUA)));
            }
        });
    }

    private static RegistryObject<Item> registryBlock(String name, Block block){
        return ITEMS.register(name,() -> new BlockItem(block,new Item.Properties()));
    }
}
