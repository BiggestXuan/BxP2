package biggestxuan.bxp2.creativeTabs;

import biggestxuan.bxp2.BxP2;
import biggestxuan.bxp2.items.BxPItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * @Author Biggest_Xuan
 * 2025/4/1
 */
@SuppressWarnings("unused")
public class BxPCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BxP2.MODID);
    public static final RegistryObject<CreativeModeTab> DEFAULT_TAB = CREATIVE_TABS.register("bxp2_default",() -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(BxPItems.BX_ENCH_INGOT.get()))
            .title(BxP2.tr("itemGroup.bxp2"))
            .displayItems((flags,output)->{
                for(var item : BxPItems.ITEMS.getEntries()){
                    output.accept(item.get());
                }
            })
            .build());
}
