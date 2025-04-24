package biggestxuan.bxp2.integration.Mekanism;

import biggestxuan.bxp2.integration.Mekanism.MekaSuit.BxPModules;
import mekanism.api.gear.IModule;
import mekanism.common.item.gear.ItemMekaSuitArmor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * @Author Biggest_Xuan
 * 2025/4/20
 */

public final class MekUtils {

    public static int getPlayerChaosProtectAmount(Player player){
        if(player == null || player.isDeadOrDying()){
            return 0;
        }
        int c = 0;
        for(ItemStack stack : player.getInventory().armor){
            if(stack.getItem() instanceof ItemMekaSuitArmor armor){
                IModule<?> module = armor.getModule(stack, BxPModules.CHAOS_PROTECT);
                if(module != null && module.getInstalledCount() >= 1){
                    c ++;
                }
            }
        }
        return c;
    }
}
