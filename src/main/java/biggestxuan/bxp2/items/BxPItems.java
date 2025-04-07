package biggestxuan.bxp2.items;

import biggestxuan.bxp2.BxP2;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
}
