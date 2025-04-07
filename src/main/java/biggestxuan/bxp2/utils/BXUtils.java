package biggestxuan.bxp2.utils;

import biggestxuan.bxp2.api.items.IBXItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * @Author Biggest_Xuan
 * 2025/4/2
 */

public final class BXUtils {
    public static int getPlayerBXValue(Player p,int max){
        int value = 0;
        if(p == null) return value;
        var items = p.getInventory().items;
        for (ItemStack item : items) {
            if (value >= max) {
                return max;
            }
            if (item.getItem() instanceof IBXItem bx) {
                value += bx.getBXValue(item) * item.getCount();
            }
        }
        return value;
    }
}
