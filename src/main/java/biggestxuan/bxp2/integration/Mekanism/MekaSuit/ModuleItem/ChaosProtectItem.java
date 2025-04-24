package biggestxuan.bxp2.integration.Mekanism.MekaSuit.ModuleItem;

import mekanism.api.providers.IModuleDataProvider;
import mekanism.common.item.ItemModule;
import net.minecraft.world.item.Rarity;

/**
 * @Author Biggest_Xuan
 * 2025/4/20
 */
public class ChaosProtectItem extends ItemModule {
    public ChaosProtectItem(IModuleDataProvider<?> moduleData) {
        super(moduleData, new Properties().stacksTo(1).rarity(Rarity.RARE));
    }
}
