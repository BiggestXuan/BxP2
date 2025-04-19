package biggestxuan.bxp2.items;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.blocks.BxPBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
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
    public static final RegistryObject<Item> BX_UNSTABLE_INGOT = ITEMS.register("bx_unstable_ingot",() -> new BxPItem(1));
    public static final RegistryObject<Item> BX_INGOT = ITEMS.register("bx_ingot",() -> new BxPItem(Rarity.UNCOMMON){
        public int getBXValue(ItemStack stack) {
            return 25;
        }
    });
    public static final RegistryObject<Item> BX_ENCH_INGOT = ITEMS.register("bx_ench_ingot",() -> new BxPItem(Rarity.RARE){
        public int getBXValue(ItemStack stack) {
            return 450;
        }
    });
    public static final RegistryObject<Item> BX_SUPER_INGOT = ITEMS.register("bx_super_ingot",() -> new BxPItem(Rarity.EPIC){
        public int getBXValue(ItemStack stack) {
            return 5000;
        }
    });
    public static final RegistryObject<Item> BX_GOLD_INGOT = ITEMS.register("bx_gold_ingot",() -> new BxPItem(8));
    public static final RegistryObject<Item> DIFFICULTY_CHANGE = ITEMS.register("difficulty_change",DifficultyChangeItem::new);
    public static final RegistryObject<Item> ENRICHED_BX = ITEMS.register("enriched_bx",BxPItem::new);
    public static final RegistryObject<Item> COPPER_CHLORIDE = ITEMS.register("copper_chloride", BxPItem::new);
    public static final RegistryObject<Item> CONDENSE_URANIUM = ITEMS.register("condense_uranium", BxPItem::new);
    public static final RegistryObject<Item> POTASSIUM = ITEMS.register("potassium", BxPItem::new);

    public static final RegistryObject<Item> YUNXI = registryPlayerModelBlock("yunxi", BxPBlocks.YUNXI);
    public static final RegistryObject<Item> BIGGEST_XUAN = registryPlayerModelBlock("biggest_xuan", BxPBlocks.BIGGEST_XUAN);
    public static final RegistryObject<Item> DCTOR_0415 = registryPlayerModelBlock("dctor_0415", BxPBlocks.DCTOR_0415);
    public static final RegistryObject<Item> TULYE = registryPlayerModelBlock("tulye", BxPBlocks.TULYE);
    public static final RegistryObject<Item> JAOXAONO = registryPlayerModelBlock("jaoxaono", BxPBlocks.JAOXAONO);
    public static final RegistryObject<Item> LAMB_KISARA = registryPlayerModelBlock("lamb_kisara", BxPBlocks.LAMB_KISARA);
    public static final RegistryObject<Item> SDXHOP = registryPlayerModelBlock("sdxhop", BxPBlocks.SDXHOP);
    public static final RegistryObject<Item> MCAIGENGZI = registryPlayerModelBlock("mcaigengzi", BxPBlocks.CAIGENGZI);

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
