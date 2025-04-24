package biggestxuan.bxp2.integration.Mekanism.MekaSuit.ModuleItem;

import mekanism.api.providers.IModuleDataProvider;
import mekanism.common.item.ItemModule;
import net.minecraft.world.item.Rarity;

/**
 * @Author Biggest_Xuan
 * 2025/4/19
 */
public class SunProtectItem extends ItemModule {
    private IModuleDataProvider<?> data;

    public SunProtectItem(IModuleDataProvider<?> moduleData) {
        super(moduleData, new Properties().stacksTo(1).rarity(Rarity.RARE));
        this.data = moduleData;
    }
}
